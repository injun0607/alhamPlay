package kr.alham.playground.pattern.calculator

import kr.alham.playground.domain.card.Card
import kr.alham.playground.domain.common.TargetElementStatusMap
import org.springframework.stereotype.Component

interface CardValueCalculator {
    fun calculateCardValue(targetCard: Card, targetElementStatusMap: TargetElementStatusMap, value: Double): Double
}

@Component
class DefaultCardValueCalculator(): CardValueCalculator {
    override fun calculateCardValue(targetCard: Card, targetElementStatusMap: TargetElementStatusMap, value: Double): Double {
        var resultValue = value
        targetCard.cardAttribute.getAffectedStatus().entries.forEach{
            resultValue += targetElementStatusMap.get(it.key) * it.value
        }

        return resultValue
    }
}