package kr.alham.playground.domain.player

import kr.alham.playground.domain.common.TargetElementStatus
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PlayerTest{


    @Test
    fun `getMaxValueTest`(){
        val player = Player(
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

        assertEquals(100.0, player.getStatusMaxValue(TargetElementStatus.HP))
        assertEquals(50.0, player.getStatusMaxValue(TargetElementStatus.MP))
        assertEquals(1000.0, player.getStatusMaxValue(TargetElementStatus.ATK))
        assertEquals(1000.0, player.getStatusMaxValue(TargetElementStatus.DEF))
        assertEquals(1000.0, player.getStatusMaxValue(TargetElementStatus.STR))
        assertEquals(1000.0, player.getStatusMaxValue(TargetElementStatus.DEX))
        assertEquals(1000.0, player.getStatusMaxValue(TargetElementStatus.INT))
        assertEquals(1000.0, player.getStatusMaxValue(TargetElementStatus.LCK))
    }


}