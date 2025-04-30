package kr.alham.playground.pattern.cardeffect

import kr.alham.playground.domain.battle.BattleState
import kr.alham.playground.domain.card.Card
import kr.alham.playground.domain.enums.CardTarget
import kr.alham.playground.domain.enums.CardType
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

/**
 * 공격 카드 효과
 * 1.
 * 2.
 * 3.
 */
@Component
class AttackCardEffect() :TargetBasedCardEffect(){
    override fun supportedType(): CardType = CardType.ATTACK
    override fun applyEffectToSelf(card: Card, battleState: BattleState) {
        println("applyEffectToSelf")
    }

    override fun applyEffectToOpponent(card: Card, battleState: BattleState) {
        val effectedStat = card.effectOpponentStat
        val damage = card.effectOpponentNum

        val afterEffectStat = battleState.opponentTargetStatus.get(effectedStat) - damage
        battleState.opponentTargetStatus.set(effectedStat, afterEffectStat)
    }

    override fun applyEffectToMutual(card: Card, battleState: BattleState) {
        println("applyEffectToMutual")
    }
}