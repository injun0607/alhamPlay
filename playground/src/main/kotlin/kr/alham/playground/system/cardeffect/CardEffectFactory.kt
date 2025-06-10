package kr.alham.playground.system.cardeffect

import kr.alham.playground.domain.enums.CardType
import org.springframework.stereotype.Component

@Component
class CardEffectFactory(
    private val strategies: List<TargetBasedCardEffect>,
    private val defaultCardEffect: DefaultCardEffect,
) {

    private val defaultStrategy = defaultCardEffect

    private val strategyMap: Map<Set<CardType>, CardEffectStrategy> = strategies
        .filter{
            it.supportedType() != null
        }
        .associateBy { setOf(it.supportedType()!!.first, it.supportedType()!!.second) }

    fun get(key: Pair<CardType,CardType>): CardEffectStrategy {
        val strategyKey = setOf(key.first, key.second)
        return strategyMap[strategyKey] ?: defaultStrategy
    }


}