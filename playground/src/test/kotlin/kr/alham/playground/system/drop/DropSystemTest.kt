package kr.alham.playground.system.drop

import kr.alham.playground.domain.item.ItemType
import kr.alham.playground.loader.MonsterDropTableLoader
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class DropSystemTest{

    @Test
    fun dropItemTest(){
        val monsterDropTableLoader = MonsterDropTableLoader()
        val dropSystem = DropSystem(monsterDropTableLoader)

        val dropTable = monsterDropTableLoader.loadMonsterDropItemTable(1L)

        val dropItemDTO = dropSystem.dropItem(1L)

        assertTrue(ItemType.entries.contains(dropItemDTO.itemType), "드롭 아이템이 비어있지 않아야 합니다.")


    }


}