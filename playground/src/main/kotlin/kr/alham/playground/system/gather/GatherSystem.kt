package kr.alham.playground.system.gather

import kr.alham.playground.common.utils.logger
import kr.alham.playground.common.utils.tileTypeMatcherWithItemRarity
import kr.alham.playground.domain.area.Area
import kr.alham.playground.domain.area.FieldArea
import kr.alham.playground.domain.area.Tile
import kr.alham.playground.domain.item.ItemRarity
import kr.alham.playground.dto.craft.MaterialDTO
import kr.alham.playground.loader.DropTableLoader
import org.springframework.stereotype.Component

@Component
class GatherSystem(
   private val dropTableLoader: DropTableLoader,
) {

    private val log = logger()
    fun fieldGather(area: FieldArea, x: Int, y: Int): MaterialDTO {
        //area와 tile을 받아서 리턴
        //tileType을 확인한다음 -> 해당 타입에 맞는 가중치 ->
        //가중치는 area별로 다르게
        //로딩해야할건 -> 드랍율 , 드랍아이템
//        dropTableLoader.getAreaTable(area.type)


        val dropTable = requireNotNull(dropTableLoader.loadDropTable(area.fieldType)){
            "Drop table not found for area: ${area.fieldType.name}"
        }

        val tile = area.getTile(x,y)
        val itemRarity = tileTypeMatcherWithItemRarity(tile.type)

        //정규화된 확률
        val dropRates = adjustAndNormalizeDropRates(itemRarity)
        //dropRates 에 있는 map을 기본으로 진행

        val pickedItemRarity = weightedRandomPick(dropRates)
        val dropItems = dropTable[pickedItemRarity]
            ?: throw IllegalArgumentException("No items found for rarity: $pickedItemRarity")

        return MaterialDTO(pickedItemRarity, dropItems.random())
    }


    private fun adjustAndNormalizeDropRates(itemRarity: ItemRarity): Map<ItemRarity, Double> {

        val baseRates= dropTableLoader.loadDropRate()

        // 1. 등급별 조정 (가중치 적용)
        val adjustedRates = baseRates.mapValues { (rarity, rate) ->
            if (rarity == itemRarity) rate * 1.2 else rate * 0.8
        }

        // 2. 총합 계산
        val total = adjustedRates.values.sum()

        // 3. 정규화
        return adjustedRates.mapValues { (_, adjusted) ->
            adjusted / total
        }
    }



    /**
     * 주어진 확률 맵에서 가중치에 따라 무작위로 아이템 등급을 선택.
     * 누적 확률이 부족할 경우 기본값을 반환.
     *
     * @param probabilities 아이템 등급과 해당 확률의 맵
     * @return 선택된 아이템 등급
     */
    private fun weightedRandomPick(
        probabilities: Map<ItemRarity, Double>,
    ): ItemRarity {
        val rand = Math.random()
        var cumulative = 0.0

        for ((rarity, prob) in probabilities) {
            cumulative += prob
            if (rand < cumulative) return rarity
        }

        // 오차로 인해 도달할 수 있음
        log.debug("누적합 부족으로 기본값 선택됨 (rand = $rand, cumulative = $cumulative)")
        return ItemRarity.COMMON
    }
}
