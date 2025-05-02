package kr.alham.playground.pattern.cardeffect

import kr.alham.playground.domain.battle.BattleState
import kr.alham.playground.domain.card.CardDTO
import kr.alham.playground.domain.enums.CardType
import org.springframework.stereotype.Component

@Component
class HealCardEffect: TargetBasedCardEffect() {
    override fun supportedType(): CardType = CardType.HEAL

    override fun applyEffectToSelf(cardDTO: CardDTO, battleState: BattleState) {
        val effectedStat = cardDTO.effectSelfStat
        val healNum = cardDTO.effectSelfNum



    }

    override fun applyEffectToOpponent(cardDTO: CardDTO, battleState: BattleState) {
        super.applyEffectToOpponent(cardDTO, battleState)
    }

    override fun applyEffectToMutual(cardDTO: CardDTO, battleState: BattleState) {
        super.applyEffectToMutual(cardDTO, battleState)
    }

}