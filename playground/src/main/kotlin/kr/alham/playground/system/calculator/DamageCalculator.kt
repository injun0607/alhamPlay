package kr.alham.playground.system.calculator

import kr.alham.playground.domain.common.TargetElementStatus
import kr.alham.playground.domain.common.TargetElementStatusMap
import org.springframework.stereotype.Component

interface DamageCalculator {
    fun calculateDamage(attackNum: Double, defenceNum: Double, defenderStatus: TargetElementStatusMap): Double
}

@Component
class DamageCalculatorDefault: DamageCalculator {
    override fun calculateDamage(attackNum: Double, defenceNum: Double, defenderStatus: TargetElementStatusMap): Double {
        var damage = attackNum
        val calculateDefenceNum = defenceNum + defenderStatus.get(TargetElementStatus.DEF)

        if(damage > calculateDefenceNum){
            damage = attackNum - calculateDefenceNum
        }else{
            damage = 0.0
        }
        return damage
    }
}
