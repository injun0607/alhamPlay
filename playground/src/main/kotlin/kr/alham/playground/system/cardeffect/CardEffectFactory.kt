package kr.alham.playground.system.cardeffect

import kr.alham.playground.domain.enums.CardType
import org.springframework.stereotype.Component

@Component
class CardEffectFactory(
    private val strategies: List<TargetBasedCardEffect>,
    private val defaultCardEffect: DefaultCardEffect
) {

    private val defaultStrategy = defaultCardEffect

    private val strategyMap: Map<Pair<CardType,CardType>, CardEffectStrategy> = strategies
        .filter{it.supportedType() != null}
        .associateBy { it.supportedType()!!}

    fun get(key: Pair<CardType,CardType>): CardEffectStrategy {
        return strategyMap[key] ?: defaultStrategy
    }

}