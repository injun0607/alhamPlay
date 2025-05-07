package kr.alham.playground.pattern.cardeffect

import kr.alham.playground.domain.battle.BattleState
import kr.alham.playground.domain.card.Card
import kr.alham.playground.domain.common.TargetElementStatusMap
import kr.alham.playground.domain.enums.CardType
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
    override fun applyEffectToSelf(card: Card, selfStatus: TargetElementStatusMap) {
        println("applyEffectToSelf")
    }

    override fun applyEffectToOpponent(card: Card, opponentStatus: TargetElementStatusMap) {
        val effectedStat = card.effectOpponentStat
        val damage = card.effectOpponentNum

        val afterEffectStat = opponentStatus.get(effectedStat) - damage
        opponentStatus.set(effectedStat, afterEffectStat)
    }

    override fun applyEffectToMutual(card: Card, selfStatus: TargetElementStatusMap, opponentStatus: TargetElementStatusMap) {
        println("applyEffectToMutual")
    }
}