package kr.alham.playground.domain.monster

import kr.alham.playground.domain.common.TargetElementStatus
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MonsterTest{


    @Test
    fun `getMaxValueTest`(){
        val monster = Monster(
            id = 1L,
            name = "Player1",
            hp = 100.0,
            mp = 50.0,
            atk = 20.0,
            def = 10.0,
            str = 25.0,
            dex = 30.0,
            int = 15.0,
            lck = 5.0
        )

        assertEquals(100.0, monster.getStatusMaxValue(TargetElementStatus.HP))
        assertEquals(50.0, monster.getStatusMaxValue(TargetElementStatus.MP))
        assertEquals(1000.0, monster.getStatusMaxValue(TargetElementStatus.ATK))
        assertEquals(1000.0, monster.getStatusMaxValue(TargetElementStatus.DEF))
        assertEquals(1000.0, monster.getStatusMaxValue(TargetElementStatus.STR))
        assertEquals(1000.0, monster.getStatusMaxValue(TargetElementStatus.DEX))
        assertEquals(1000.0, monster.getStatusMaxValue(TargetElementStatus.INT))
        assertEquals(1000.0, monster.getStatusMaxValue(TargetElementStatus.LCK))
    }


}