package kr.alham.playground.domain.card

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CardTest{

    @Test
    fun `테스트1_effectSelfNum`(){
        val card = Card(
            effectSelfNum = 8
        )

        val num = 2;

        assertEquals(num + card.effectSelfNum, 10)
//        assertEquals(num - card.getEffectSelfNum(),10 )





    }


}