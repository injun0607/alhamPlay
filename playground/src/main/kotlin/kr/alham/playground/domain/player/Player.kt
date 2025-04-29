package kr.alham.playground.domain.player

import kr.alham.playground.domain.common.TargetElement
import kr.alham.playground.domain.common.TargetElementStatus

class Player(
    val id: Long? = null,
    override val name: String = "",
    override val hp: Double = 10.0,
    override val mp: Double = 10.0,
    override val atk: Double = 10.0,
    override val def: Double = 10.0,
    override val str: Double = 1.0,
    override val dex: Double = 1.0,
    override val int: Double = 1.0,
    override val lck: Double = 1.0,
): TargetElement {

    override fun getStatus(): Map<TargetElementStatus, Double> {
        return mapOf(
            TargetElementStatus.HP to hp,
            TargetElementStatus.MP to mp,
            TargetElementStatus.ATK to atk,
            TargetElementStatus.DEF to def,
            TargetElementStatus.STR to str,
            TargetElementStatus.DEX to dex,
            TargetElementStatus.INT to int,
            TargetElementStatus.LCK to lck
        )

    }

    override fun getStatusValue(): Double {
        TODO("Not yet implemented")
    }
}
