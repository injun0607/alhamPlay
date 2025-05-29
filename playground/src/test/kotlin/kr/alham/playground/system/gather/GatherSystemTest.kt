package kr.alham.playground.system.gather

import kr.alham.playground.common.utils.tileTypeMatcherWithItemRarity
import kr.alham.playground.domain.area.FieldArea
import kr.alham.playground.domain.area.FieldType
import kr.alham.playground.domain.item.ItemRarity
import kr.alham.playground.dto.craft.MaterialDTO
import kr.alham.playground.loader.DropTableLoader
import kr.alham.playground.system.area.AreaSystem
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GatherSystemTest{


    lateinit var dropTableLoader: DropTableLoader
    lateinit var gatherSystem: GatherSystem
    lateinit var filedArea: FieldArea
    lateinit var dropTable: Map<ItemRarity, List<String>>
    lateinit var dropRates: Map<ItemRarity, Double>

    @BeforeEach
    fun setup() {
        dropTableLoader = DropTableLoader()
        gatherSystem = GatherSystem(dropTableLoader)
        filedArea = AreaSystem().initFieldArea(FieldType.FOREST)
        dropTable = dropTableLoader.loadDropTable(filedArea.fieldType)
        dropRates = dropTableLoader.loadDropRate()
    }


    private fun adjustAndNormalizeDropRates(itemRarity: ItemRarity): Map<ItemRarity, Double> {
        val baseRates= dropRates

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

    @Test
    fun adjustAndNormalizeDropRatesTest(){
        //1.변화된 확률 확인
        val dropRates = dropTableLoader.loadDropRate()
        val changedRates = adjustAndNormalizeDropRates( ItemRarity.COMMON)
        println("===========changedRates============")
        for((rarity, rate) in changedRates){
            println("$rarity: $rate")
        }

        println("===========dropRates============")
        for((rarity, rate) in dropRates){
            println("$rarity: $rate")
        }

        //2.변화된 확률로 테스트

        val trials = 100_000_000
        val tolerance = 0.1 // ±10%

        val counts = mutableMapOf<ItemRarity,Int>().withDefault { 0 }

        repeat(trials){
            val result = gatherSystem.weightedRandomPick(changedRates)
            counts[result] = counts.getValue(result) + 1
        }

        for((rarity, count) in changedRates) {
            val actualCount = counts.getValue(rarity)
            val expectedCount = count * trials
            val lower = expectedCount * (1 - tolerance)
            val upper = expectedCount * (1 + tolerance)

            println("$rarity: actual=$actualCount, expected=~$expectedCount")
            assertTrue(
                actualCount in lower.toInt()..upper.toInt(),
                "❌ $rarity: $actualCount not in range [$lower, $upper]"
            )
        }

    }



    @Test
    fun weightedRandomPickTest() {

        val counts = mutableMapOf<ItemRarity,Int>().withDefault { 0 }


        val trials = 100_000_000
        val tolerance = 0.1 // ±10%

        repeat(trials){
            val result = gatherSystem.weightedRandomPick(dropRates)
            counts[result] = counts.getValue(result) + 1
        }

        for((rarity, count) in dropRates){
            val actualCount = counts.getValue(rarity)
            val expectedCount = count * trials
            val lower = expectedCount * (1 - tolerance)
            val upper = expectedCount * (1 + tolerance)

            println("$rarity: actual=$actualCount, expected=~$expectedCount")
            assertTrue(
                actualCount in lower.toInt()..upper.toInt(),
                "❌ $rarity: $actualCount not in range [$lower, $upper]"
            )
        }

    }


    private fun weightedRandomPick(
        probabilities: Map<ItemRarity, Double>,
    ): ItemRarity {
        // 누적 확률 계산
        val cumulativeProbabilities = mutableMapOf<ItemRarity, Double>()
        var cumulativeSum = 0.0

        for ((rarity, probability) in probabilities) {
            cumulativeSum += probability
            cumulativeProbabilities[rarity] = cumulativeSum
        }

        // 0과 1 사이의 무작위 수 생성
        val randomValue = Math.random()

        // 누적 확률에 따라 아이템 등급 선택
        for ((rarity, cumulativeProbability) in cumulativeProbabilities) {
            if (randomValue < cumulativeProbability) {
                return rarity
            }
        }

        // 기본값 반환 (이론적으로 도달하지 않아야 함)
        return ItemRarity.COMMON
    }

    private fun filedGather(area:FieldArea,x: Int, y: Int): MaterialDTO {

        val tile = area.getTile(x,y)
        val itemRarity = tileTypeMatcherWithItemRarity(tile.type)

        //정규화된 확률
        val dropRates = adjustAndNormalizeDropRates(itemRarity)
        //dropRates 에 있는 map을 기본으로 진행

        val pickedItemRarity = weightedRandomPick(dropRates)
        val dropItems = dropTable[pickedItemRarity]
            ?: throw IllegalArgumentException("No items found for rarity: $pickedItemRarity")

        return MaterialDTO(itemRarity,dropItems.random())
    }

    @Test
    fun `fieldGatherTest`(){

        //타일타입을 확인하고
        //해당 타일타입에 맞는 아이템이 잘나오는지 확인하고
        //이걸 1억번 실행했을때 편차에 맞는지 확인 하자

        val tile = filedArea.getTile(2,3)
        val itemRarity = tileTypeMatcherWithItemRarity(tile.type)
        val loadDropTable = dropTableLoader.loadDropTable(filedArea.fieldType)
        val changedDropRates = adjustAndNormalizeDropRates(itemRarity)

        val trials = 100_000_000
        val tolerance = 0.1 // ±10%

        val counts = mutableMapOf<ItemRarity,Int>().withDefault { 0 }

        println("진입")
        repeat(trials){
            val result = filedGather(filedArea, 2, 3)

            val rarity = loadDropTable.entries.firstOrNull { it.value.contains(result.name) }?.key
                ?: throw IllegalArgumentException("No item found for result: $result")
            counts[rarity] = counts.getValue(rarity) + 1
        }
        println("완료")



        //변화된 확률을 확인한다음 -> 해당 편차에 맞게 아이템이 나왔는지 확인하기
        for((rarity, count) in changedDropRates) {
            val actualCount = counts.getValue(rarity)
            val expectedCount = count * trials
            val lower = expectedCount * (1 - tolerance)
            val upper = expectedCount * (1 + tolerance)

            println("$rarity: actual=$actualCount, expected=~$expectedCount")
            assertTrue(
                actualCount in lower.toInt()..upper.toInt(),
                "❌ $rarity: $actualCount not in range [$lower, $upper]"
            )
        }

    }




}