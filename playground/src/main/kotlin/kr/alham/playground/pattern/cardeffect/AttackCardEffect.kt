package kr.alham.playground.pattern.cardeffect

import kr.alham.playground.domain.battle.BattleState
import kr.alham.playground.domain.card.Card
import kr.alham.playground.domain.enums.CardTarget

class AttackCardEffect() :CardEffectStrategy{
    override fun applyEffect(card: Card, battleState: BattleState): BattleState {
        when(card.cardTarget){
            CardTarget.SELF -> {
                var selfStatus = battleState.selfTargetStatus.get(card.effectSelfStat)

            }
            CardTarget.OPPONENT -> {
                //상대방에게 영향
                var opponentStatus = battleState.opponentTargetStatus.get(card.effectOpponentStat)
                opponentStatus -= card.effectOpponentNum;
                battleState.opponentTargetStatus.set(card.effectOpponentStat, opponentStatus)
            }
            CardTarget.MUTUAL -> {
                //서로에게 영향
                var selfStatus = battleState.selfTargetStatus.get(card.effectSelfStat)
                var opponentStatus = battleState.opponentTargetStatus.get(card.effectOpponentStat)

                selfStatus -= card.effectSelfNum
                opponentStatus -= card.effectOpponentNum

                battleState.selfTargetStatus.set(card.effectSelfStat, selfStatus)
                battleState.opponentTargetStatus.set(card.effectOpponentStat, opponentStatus)
            }
        }

        return battleState

    }
}