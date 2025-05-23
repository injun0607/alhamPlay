package kr.alham.playground.system.cardeffect

import kr.alham.playground.domain.common.TargetElementStatusMap
import kotlin.test.Test


class TargetBasedCardEffectTest{


    @Test
    fun `evasionRateTest`(){

        val attackNum: Double = 10.0
        val evasionNum: Double = 0.7

        val evaderStatus = TargetElementStatusMap()

        var cnt: Int = 0;

        for(i in 1..1000000){
            if(Math.random() < evasionNum){
                cnt++
            }
        }

        println(cnt)

    }


}