package kr.alham.playground.loader

import kr.alham.playground.domain.item.ItemRarity
import kr.alham.playground.domain.item.ItemType
import kr.alham.playground.system.drop.DropSystem
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MonsterDropTableLoaderTest{


    @Test
    fun monsterDropTableLoaderTest(){
        val monsterDropTableLoader = MonsterDropTableLoader()

        val dropTable = monsterDropTableLoader.loadMonsterDropItemTable(1L)

        assertEquals(false, dropTable.drops.isEmpty())
        assertEquals(2,dropTable.drops[ItemRarity.COMMON]?.size!!)
        assertEquals(2,dropTable.drops[ItemRarity.COMMON]?.get(ItemType.EQUIPMENT)?.size)
        assertEquals(3,dropTable.drops[ItemRarity.COMMON]?.get(ItemType.MATERIAL)?.size)
        assertNotEquals(2,dropTable.drops[ItemRarity.COMMON]?.get(ItemType.MATERIAL)?.size)
    }

}