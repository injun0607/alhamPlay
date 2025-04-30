package kr.alham.playground.pattern.cardeffect

import kr.alham.playground.domain.battle.BattleState
import kr.alham.playground.domain.card.Card
import kr.alham.playground.domain.common.TargetElement
import kr.alham.playground.domain.enums.CardTarget
import kr.alham.playground.domain.enums.CardType

interface CardEffectStrategy {
    fun supportedType(): CardType
    fun applyEffect(card: Card, battleState: BattleState): BattleState
}

abstract class TargetBasedCardEffect: CardEffectStrategy {
    override fun applyEffect(card: Card, battleState: BattleState): BattleState {
        when(card.cardTarget){
            CardTarget.SELF -> {
                applyEffectToSelf(card,battleState)
            }
            CardTarget.OPPONENT -> {
                applyEffectToOpponent(card,battleState)
            }
            CardTarget.MUTUAL -> {
                applyEffectToMutual(card,battleState)
            }
        }
        return battleState
    }

    protected open fun applyEffectToSelf(card: Card, battleState: BattleState){}
    protected open fun applyEffectToOpponent(card: Card, battleState: BattleState){}
    protected open fun applyEffectToMutual(card: Card, battleState: BattleState){}

}