package kr.alham.playground.pattern.calculator

import org.springframework.stereotype.Component

interface EvasionRateCalculator {
    fun calculateEvasionSuccess(evasionNum: Double): Boolean
}

@Component
class EvasionRateCalculatorDefault: EvasionRateCalculator {
    override fun calculateEvasionSuccess(evasionNum: Double): Boolean {
        return Math.random() < evasionNum
    }
}