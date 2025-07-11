package kr.alham.playground.domain.enums

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


class CollectionLevelEnumsTest{


    @Test
    fun levelUpTest(){

        var levelOne = CollectionLevelEnums.LEVEL1
        val leveOneInputExp = 5

        var levelTen = CollectionLevelEnums.LEVEL10
        var levelTenInputExp = 50
        var levelTenInputExp2 = 30



        assertTrue(levelOne.checkLevelUpExp(leveOneInputExp))
        assertTrue(levelTen.checkLevelUpExp(levelTenInputExp))
        assertFalse(levelTen.checkLevelUpExp(levelTenInputExp2))

        assertEquals(CollectionLevelEnums.LEVEL2, levelOne.levelUp())
        assertEquals(CollectionLevelEnums.LEVEL11, levelTen.levelUp())



    }

}