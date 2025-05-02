package kr.alham.playground.dto.monster

import kr.alham.playground.domain.common.TargetElementStatus
import kr.alham.playground.domain.common.TargetElementStatusMap

class MonsterDTO(
    var id : Long? = null,
    var uuid: Long = 0L,
    var name: String = "",
    var description: String = "",
    var hp: Double = 10.0,
    var mp: Double = 10.0,
    var atk: Double = 10.0,
    var def: Double = 10.0,
    var str: Double = 1.0,
    var dex: Double = 1.0,
    var int: Double = 1.0,
    var lck: Double = 1.0,
){
    fun getStatus(): TargetElementStatusMap {
        return TargetElementStatusMap(
            mutableMapOf(
                TargetElementStatus.HP to hp,
                TargetElementStatus.MP to mp,
                TargetElementStatus.ATK to atk,
                TargetElementStatus.DEF to def,
                TargetElementStatus.STR to str,
                TargetElementStatus.DEX to dex,
                TargetElementStatus.INT to int,
                TargetElementStatus.LCK to lck
            )
        )
    }

    fun getStatusValue(targetElementStatus: TargetElementStatus): Double {
        return getStatus().get(targetElementStatus)
    }
}