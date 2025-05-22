package kr.alham.playground.domain.card

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CardTest{

    @Test
    fun `테스트1_effectSelfNum`(){
        val cardDTO = PlayerCard(
            effectSelfNum = 8.0
        )

        val num = 2;

        assertEquals(num + cardDTO.effectSelfNum, 10.0)
//        assertEquals(num - card.getEffectSelfNum(),10 )





    }


}