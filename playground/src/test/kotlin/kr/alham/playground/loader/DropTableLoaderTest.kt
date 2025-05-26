package kr.alham.playground.loader

import kr.alham.playground.domain.area.FieldType
import kr.alham.playground.domain.item.ItemRarity
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class DropTableLoaderTest{


    @Test
    fun loadDropTableTest() {
        val loader = DropTableLoader()
        val dropTable = loader.loadDropTable(FieldType.GLACIER)

        assertNotNull(dropTable)
        assertTrue(dropTable.isNotEmpty())

        val rareItemList = dropTable.get(ItemRarity.COMMON)
        assertEquals(rareItemList?.size ,6)
        assertTrue(rareItemList?.contains("IceShard") ?: true)
        assertTrue(rareItemList?.contains("FrozenBerry") ?: true)
        assertTrue(rareItemList?.contains("SnowFiber") ?: true)
        assertTrue(rareItemList?.contains("ChillRoot") ?: true)
        assertTrue(rareItemList?.contains("ColdScale") ?: true)
        assertTrue(rareItemList?.contains("IceMoss") ?: true)

        // 추가적인 테스트 케이스 작성 가능
        // 예: 특정 아이템 타입이 존재하는지 확인
        // assertTrue(dropTable.containsKey(ItemType.SOME_ITEM_TYPE))
    }

    @Test
    fun loadDropRateTest() {
        val loader = DropTableLoader()
        val dropRate = loader.loadDropRate()

        assertNotNull(dropRate)
        assertTrue(dropRate.isNotEmpty())

        // 드랍율이 존재하는지 확인
        assertTrue(dropRate.containsKey(ItemRarity.COMMON))
        assertTrue(dropRate.containsKey(ItemRarity.UNCOMMON))
        assertTrue(dropRate.containsKey(ItemRarity.RARE))
        assertTrue(dropRate.containsKey(ItemRarity.EPIC))
        assertTrue(dropRate.containsKey(ItemRarity.UNIQUE))
        assertTrue(dropRate.containsKey(ItemRarity.LEGENDARY))

        // 드랍율 값이 0 이상인지 확인

        dropRate[ItemRarity.COMMON]?.let { assertEquals(it, 0.6)}
        dropRate[ItemRarity.UNCOMMON]?.let { assertEquals(it, 0.15) }
        dropRate[ItemRarity.RARE]?.let { assertEquals(it, 0.15) }
        dropRate[ItemRarity.EPIC]?.let { assertEquals(it, 0.08) }
        dropRate[ItemRarity.UNIQUE]?.let { assertEquals(it, 0.015) }
        dropRate[ItemRarity.LEGENDARY]?.let { assertEquals(it, 0.005) }
    }




}