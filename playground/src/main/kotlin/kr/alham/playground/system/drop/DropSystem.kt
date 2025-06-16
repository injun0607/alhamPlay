package kr.alham.playground.system.drop

import kr.alham.playground.domain.item.ItemRarity
import kr.alham.playground.domain.item.ItemType
import kr.alham.playground.dto.drop.MonsterDropItemDTO
import kr.alham.playground.loader.MonsterDropTableLoader
import org.springframework.stereotype.Component

@Component
class DropSystem(
    private val monsterDropTableLoader: MonsterDropTableLoader
) {
    fun dropItem(monsterId: Long): MonsterDropItemDTO {

        val monsterDropTable = monsterDropTableLoader.loadMonsterDropItemTable(monsterId)
        //rarity 결정
        val dropRate = monsterDropTableLoader.loadDropRate()
        val rarity = weightedRandomPick(dropRate)
        //equipment // material 결정
        val itemType = ItemType.entries.toTypedArray().random()
        //drop템 랜덤 결정
        val itemList = requireNotNull(monsterDropTable.drops[rarity]){
            "No items found for rarity: $rarity in monster drop table for monsterId: $monsterId"
        }

        val itemListWithType = requireNotNull(itemList[itemType]){
            "No items found for type: $itemType in monster drop table for monsterId: $monsterId"
        }


        return MonsterDropItemDTO(
            itemType = itemType,
            itemId = itemListWithType.random()
        )

    }


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
        return ItemRarity.COMMON
    }

}