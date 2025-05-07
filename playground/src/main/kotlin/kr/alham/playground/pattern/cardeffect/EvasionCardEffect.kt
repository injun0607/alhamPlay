package kr.alham.playground.pattern.cardeffect

import kr.alham.playground.domain.card.Card
import kr.alham.playground.domain.common.TargetElementStatusMap
import kr.alham.playground.domain.enums.CardType
import org.springframework.stereotype.Component

@Component
class EvasionCardEffect: TargetBasedCardEffect() {
    override fun supportedType(): CardType = CardType.EVASION 

    override fun applyEffectToSelf(card: Card, selfStatus: TargetElementStatusMap) {
        super.applyEffectToSelf(card, selfStatus)
    }

    override fun applyEffectToOpponent(card: Card, opponentStatus: TargetElementStatusMap) {
        super.applyEffectToOpponent(card, opponentStatus)
    }

    override fun applyEffectToMutual(
        card: Card,
        selfStatus: TargetElementStatusMap,
        opponentStatus: TargetElementStatusMap
    ) {
        super.applyEffectToMutual(card, selfStatus, opponentStatus)
    }
}