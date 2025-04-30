package kr.alham.playground.pattern.cardeffect

import kr.alham.playground.domain.enums.CardType
import org.springframework.stereotype.Component

@Component
class CardEffectFactory(
    private val strategies: List<TargetBasedCardEffect>,
) {

    private val strategyMap: Map<CardType, CardEffectStrategy> = strategies.associateBy { it.supportedType() }

    fun get(cardType: CardType): CardEffectStrategy {
        return strategyMap[cardType] ?: throw IllegalArgumentException("No strategy found for card type: $cardType")
    }

}