package kr.alham.playground.pattern.cardeffect

import kr.alham.playground.domain.battle.BattleState
import kr.alham.playground.domain.card.CardDTO
import kr.alham.playground.domain.enums.CardType
import org.springframework.stereotype.Component

@Component
class DefenceCardEffect: TargetBasedCardEffect() {
    override fun supportedType(): CardType = CardType.DEFENCE

    override fun applyEffectToSelf(cardDTO: CardDTO, battleState: BattleState) {
        super.applyEffectToSelf(cardDTO, battleState)
    }

    override fun applyEffectToOpponent(cardDTO: CardDTO, battleState: BattleState) {
        super.applyEffectToOpponent(cardDTO, battleState)
    }

    override fun applyEffectToMutual(cardDTO: CardDTO, battleState: BattleState) {
        super.applyEffectToMutual(cardDTO, battleState)
    }
}