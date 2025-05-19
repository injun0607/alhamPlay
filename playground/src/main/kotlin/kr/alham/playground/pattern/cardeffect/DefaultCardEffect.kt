package kr.alham.playground.pattern.cardeffect

import kr.alham.playground.domain.battle.BattleStatus
import kr.alham.playground.pattern.calculator.CardValueCalculator
import org.springframework.stereotype.Component

@Component
class DefaultCardEffect(
    cardValueCalculator: CardValueCalculator
): TargetBasedCardEffect(cardValueCalculator) {

    override fun selfToSelf(targetOne: BattleStatus, targetTwo: BattleStatus){

        val cardOne = targetOne.card
        val cardTwo = targetTwo.card

        val targetOneStatus = targetOne.status
        val targetTwoStatus = targetTwo.status


        val cardOneSelfNum = cardOne.effectSelfNum
        val cardOneSelfStatus = cardOne.effectSelfStat

        val cardTwoSelfNum = cardTwo.effectSelfNum
        val cardTwoSelfStatus = cardTwo.effectSelfStat

        val cardOneSelfValue = cardValueCalculator.calculateCardValue(cardOne, targetOneStatus, cardOneSelfNum)
        val cardTwoSelfValue = cardValueCalculator.calculateCardValue(cardTwo, targetTwoStatus, cardTwoSelfNum)

        val targetOneOrgStatus = targetOne.target.getStatusValue(cardOneSelfStatus)
        val targetTwoOrgStatus = targetTwo.target.getStatusValue(cardTwoSelfStatus)


        targetOneStatus.addMaxValueCheck(cardOneSelfStatus, cardOneSelfValue,targetOneOrgStatus)
        targetTwoStatus.addMaxValueCheck(cardTwoSelfStatus, cardTwoSelfValue,targetTwoOrgStatus)
    }
    override fun selfToOpponent(targetOne: BattleStatus, targetTwo: BattleStatus){
        val cardOne = targetOne.card
        val cardTwo = targetTwo.card

        val targetOneStatus = targetOne.status
        val targetTwoStatus = targetTwo.status

        val cardOneSelfNum = cardOne.effectSelfNum
        val cardOneSelfStatus = cardOne.effectSelfStat

        val cardTwoOpponentNum = cardTwo.effectOpponentNum
        val cardTwoOpponentStatus = cardTwo.effectOpponentStat

        val cardOneSelfValue = cardValueCalculator.calculateCardValue(cardOne, targetOneStatus, cardOneSelfNum)
        val cardTwoOpponentValue = cardValueCalculator.calculateCardValue(cardTwo, targetTwoStatus, cardTwoOpponentNum)



        targetOneStatus.addMaxValueCheck(cardOneSelfStatus, cardOneSelfValue,targetOne.target.getStatusValue(cardOneSelfStatus))
        targetOneStatus.addMaxValueCheck(cardTwoOpponentStatus, cardTwoOpponentValue,targetOne.target.getStatusValue(cardTwoOpponentStatus))

    }
    override fun selfToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){
        val cardOne = targetOne.card
        val cardTwo = targetTwo.card

        val targetOneStatus = targetOne.status
        val targetTwoStatus = targetTwo.status

        val cardOneSelfNum = cardOne.effectSelfNum
        val cardOneSelfStatus = cardOne.effectSelfStat

        val cardTwoSelfNum = cardTwo.effectSelfNum
        val cardTwoSelfStatus = cardTwo.effectSelfStat

        val cardTwoOpponentNum = cardTwo.effectOpponentNum
        val cardTwoOpponentStatus = cardTwo.effectOpponentStat

        val cardOneSelfValue = cardValueCalculator.calculateCardValue(cardOne, targetOneStatus, cardOneSelfNum)
        val cardTwoSelfValue = cardValueCalculator.calculateCardValue(cardTwo, targetTwoStatus, cardTwoSelfNum)
        val cardTwoOpponentValue = cardValueCalculator.calculateCardValue(cardTwo, targetTwoStatus, cardTwoOpponentNum)



        targetOneStatus.addMaxValueCheck(cardOneSelfStatus, cardOneSelfValue,targetOne.target.getStatusValue(cardOneSelfStatus))
        targetOneStatus.addMaxValueCheck(cardTwoOpponentStatus, cardTwoOpponentValue,targetOne.target.getStatusValue(cardTwoOpponentStatus))
        targetTwoStatus.addMaxValueCheck(cardTwoSelfStatus, cardTwoSelfValue,targetTwo.target.getStatusValue(cardTwoSelfStatus))
    }
    override fun opponentToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){
        val cardOne = targetOne.card
        val cardTwo = targetTwo.card

        val targetOneStatus = targetOne.status
        val targetTwoStatus = targetTwo.status

        val cardOneOpponentNum = cardOne.effectOpponentNum
        val cardOneOpponentStatus = cardOne.effectOpponentStat

        val cardTwoSelfNum = cardTwo.effectSelfNum
        val cardTwoSelfStatus = cardTwo.effectSelfStat

        val cardTwoOpponentNum = cardTwo.effectOpponentNum
        val cardTwoOpponentStatus = cardTwo.effectOpponentStat

        val cardOneOpponentValue = cardValueCalculator.calculateCardValue(cardOne, targetOneStatus, cardOneOpponentNum)
        val cardTwoSelfValue = cardValueCalculator.calculateCardValue(cardTwo, targetTwoStatus, cardTwoSelfNum)
        val cardTwoOpponentValue = cardValueCalculator.calculateCardValue(cardTwo, targetTwoStatus, cardTwoOpponentNum)

        targetOneStatus.addMaxValueCheck(cardTwoOpponentStatus, cardTwoOpponentValue,targetOne.target.getStatusValue(cardTwoOpponentStatus))
        targetTwoStatus.addMaxValueCheck(cardOneOpponentStatus, cardOneOpponentValue,targetTwo.target.getStatusValue(cardOneOpponentStatus))
        targetTwoStatus.addMaxValueCheck(cardTwoSelfStatus, cardTwoSelfValue,targetTwo.target.getStatusValue(cardTwoSelfStatus))

    }
    override fun opponentToOpponent(targetOne: BattleStatus, targetTwo: BattleStatus){
        val cardOne = targetOne.card
        val cardTwo = targetTwo.card

        val targetOneStatus = targetOne.status
        val targetTwoStatus = targetTwo.status

        val cardOneOpponentNum = cardOne.effectOpponentNum
        val cardOneOpponentStatus = cardOne.effectOpponentStat

        val cardTwoOpponentNum = cardTwo.effectOpponentNum
        val cardTwoOpponentStatus = cardTwo.effectOpponentStat

        val cardOneOpponentValue = cardValueCalculator.calculateCardValue(cardOne, targetOneStatus, cardOneOpponentNum)
        val cardTwoOpponentValue = cardValueCalculator.calculateCardValue(cardTwo, targetTwoStatus, cardTwoOpponentNum)

        targetOneStatus.addMaxValueCheck(cardTwoOpponentStatus, cardTwoOpponentValue,targetOne.target.getStatusValue(cardTwoOpponentStatus))
        targetTwoStatus.addMaxValueCheck(cardOneOpponentStatus, cardOneOpponentValue,targetTwo.target.getStatusValue(cardOneOpponentStatus))

    }
    override fun mutualToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){
        val cardOne = targetOne.card
        val cardTwo = targetTwo.card

        val targetOneStatus = targetOne.status
        val targetTwoStatus = targetTwo.status

        val cardOneSelfNum = cardOne.effectSelfNum
        val cardOneSelfStatus = cardOne.effectSelfStat
        val cardOneOpponentNum = cardOne.effectOpponentNum
        val cardOneOpponentStatus = cardOne.effectOpponentStat

        val cardTwoSelfNum = cardTwo.effectSelfNum
        val cardTwoSelfStatus = cardTwo.effectSelfStat
        val cardTwoOpponentNum = cardTwo.effectOpponentNum
        val cardTwoOpponentStatus = cardTwo.effectOpponentStat

        val cardOneSelfValue = cardValueCalculator.calculateCardValue(cardOne, targetOneStatus, cardOneSelfNum)
        val cardOneOpponentValue = cardValueCalculator.calculateCardValue(cardOne, targetOneStatus, cardOneOpponentNum)

        val cardTwoSelfValue = cardValueCalculator.calculateCardValue(cardTwo, targetTwoStatus, cardTwoSelfNum)
        val cardTwoOpponentValue = cardValueCalculator.calculateCardValue(cardTwo, targetTwoStatus, cardTwoOpponentNum)

        targetOneStatus.addMaxValueCheck(cardOneSelfStatus, cardOneSelfValue,targetOne.target.getStatusValue(cardOneSelfStatus))
        targetOneStatus.addMaxValueCheck(cardTwoOpponentStatus, cardTwoOpponentValue,targetOne.target.getStatusValue(cardTwoOpponentStatus))

        targetTwoStatus.addMaxValueCheck(cardTwoSelfStatus, cardTwoSelfValue,targetTwo.target.getStatusValue(cardTwoSelfStatus))
        targetTwoStatus.addMaxValueCheck(cardOneOpponentStatus, cardOneOpponentValue,targetTwo.target.getStatusValue(cardOneOpponentStatus))

    }
}