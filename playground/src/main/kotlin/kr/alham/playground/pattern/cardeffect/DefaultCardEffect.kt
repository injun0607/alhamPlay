package kr.alham.playground.pattern.cardeffect

import kr.alham.playground.domain.battle.BattleStatus
import kr.alham.playground.domain.card.Card
import kr.alham.playground.domain.common.TargetElementStatusMap
import kr.alham.playground.domain.enums.CardType
import kr.alham.playground.pattern.calculator.CardValueCalculator
import kr.alham.playground.pattern.calculator.DefaultCardValueCalculator
import org.springframework.stereotype.Component

@Component
class DefaultCardEffect(
    cardValueCalculator: CardValueCalculator
): TargetBasedCardEffect(cardValueCalculator) {

    override fun selfToSelf(targetOne: BattleStatus, targetTwo: BattleStatus){}
    override fun selfToOpponent(targetOne: BattleStatus, targetTwo: BattleStatus){}
    override fun selfToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){}
    override fun opponentToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){}
    override fun opponentToOpponent(targetOne: BattleStatus, targetTwo: BattleStatus){}
    override fun mutualToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){}
}