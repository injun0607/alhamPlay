package kr.alham.playground.pattern.cardeffect

import kr.alham.playground.domain.battle.BattleState
import kr.alham.playground.domain.card.Card
import kr.alham.playground.domain.common.TargetElementStatusMap
import kr.alham.playground.domain.enums.CardTarget
import kr.alham.playground.domain.enums.CardType

interface CardEffectStrategy {
    fun supportedType(): CardType
    fun applyEffect(card: Card, selfStatus: TargetElementStatusMap, opponentStatus: TargetElementStatusMap)


}

abstract class TargetBasedCardEffect: CardEffectStrategy {
    override fun applyEffect(card: Card, selfStatus:TargetElementStatusMap, opponentStatus: TargetElementStatusMap){

        when(card.cardTarget){
            CardTarget.SELF -> {
                applyEffectToSelf(card,selfStatus)
            }
            CardTarget.OPPONENT -> {
                applyEffectToOpponent(card,opponentStatus)
            }
            CardTarget.MUTUAL -> {
                applyEffectToMutual(card,selfStatus, opponentStatus)
            }
        }
    }

    protected open fun applyEffectToSelf(card: Card, selfStatus: TargetElementStatusMap){}
    protected open fun applyEffectToOpponent(card: Card, opponentStatus: TargetElementStatusMap){}
    protected open fun applyEffectToMutual(card: Card, selfStatus: TargetElementStatusMap, opponentStatus: TargetElementStatusMap){}

}