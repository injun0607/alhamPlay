package kr.alham.playground.pattern.cardeffect

import kr.alham.playground.domain.battle.BattleStatus
import kr.alham.playground.domain.card.Card
import kr.alham.playground.domain.common.TargetElementStatusMap
import kr.alham.playground.domain.enums.CardType
import org.springframework.stereotype.Component

@Component
class DefaultCardEffect: TargetBasedCardEffect() {

    override fun selfToSelf(targetOne: BattleStatus, targetTwo: BattleStatus){}
    override fun selfToOpponent(targetOne: BattleStatus, targetTwo: BattleStatus){}
    override fun selfToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){}
    override fun opponentToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){}
    override fun opponentToOpponent(targetOne: BattleStatus, targetTwo: BattleStatus){}
    override fun mutualToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){}
}