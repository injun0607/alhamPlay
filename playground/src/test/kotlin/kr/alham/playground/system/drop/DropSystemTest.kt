package kr.alham.playground.system.drop

import kr.alham.playground.loader.MonsterDropTableLoader
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class DropSystemTest{

    @Test
    fun dropItemTest(){
        val monsterDropTableLoader = MonsterDropTableLoader()
        val dropSystem = DropSystem(monsterDropTableLoader)


        val dropTable = monsterDropTableLoader.loadMonsterDropItemTable(1L)

        val itemName = dropSystem.dropItem(1L)

        println(itemName)
        assertEquals(true, itemName.isNotEmpty())



    }


}