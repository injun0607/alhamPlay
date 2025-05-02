package kr.alham.playground.pattern.cardeffect

import kr.alham.playground.domain.battle.BattleState
import kr.alham.playground.dto.card.CardDTO
import kr.alham.playground.domain.enums.CardTarget
import kr.alham.playground.domain.enums.CardType

interface CardEffectStrategy {
    fun supportedType(): CardType
    fun applyEffect(cardDTO: CardDTO, battleState: BattleState): BattleState

}

abstract class TargetBasedCardEffect: CardEffectStrategy {
    override fun applyEffect(cardDTO: CardDTO, battleState: BattleState): BattleState {
        when(cardDTO.cardTarget){
            CardTarget.SELF -> {
                applyEffectToSelf(cardDTO,battleState)
            }
            CardTarget.OPPONENT -> {
                applyEffectToOpponent(cardDTO,battleState)
            }
            CardTarget.MUTUAL -> {
                applyEffectToMutual(cardDTO,battleState)
            }
        }
        return battleState
    }

    protected open fun applyEffectToSelf(cardDTO: CardDTO, battleState: BattleState){}
    protected open fun applyEffectToOpponent(cardDTO: CardDTO, battleState: BattleState){}
    protected open fun applyEffectToMutual(cardDTO: CardDTO, battleState: BattleState){}

}