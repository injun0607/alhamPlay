package kr.alham.playground.pattern.cardeffect

import kr.alham.playground.domain.battle.BattleState
import kr.alham.playground.domain.card.Card
import kr.alham.playground.domain.enums.CardType
import org.springframework.stereotype.Component

@Component
class DefenceCardEffect: TargetBasedCardEffect() {
    override fun supportedType(): CardType = CardType.DEFENCE

    override fun applyEffectToSelf(card: Card, battleState: BattleState) {
        super.applyEffectToSelf(card, battleState)
    }

    override fun applyEffectToOpponent(card: Card, battleState: BattleState) {
        super.applyEffectToOpponent(card, battleState)
    }

    override fun applyEffectToMutual(card: Card, battleState: BattleState) {
        super.applyEffectToMutual(card, battleState)
    }
}