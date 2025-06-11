package kr.alham.playground.system.cardeffect

import kr.alham.playground.domain.battle.BattleStatus
import kr.alham.playground.system.calculator.CardValueCalculator
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


        val cardOneSelfNum = cardOne.getEffectSelfNumValue()
        val cardOneSelfStatus = cardOne.effectSelfStat

        val cardTwoSelfNum = cardTwo.getEffectSelfNumValue()
        val cardTwoSelfStatus = cardTwo.effectSelfStat

        val cardOneSelfValue = cardValueCalculator.calculateCardValue(cardOne, targetOneStatus, cardOneSelfNum)
        val cardTwoSelfValue = cardValueCalculator.calculateCardValue(cardTwo, targetTwoStatus, cardTwoSelfNum)

        val targetOneOrgStatus = targetOne.target.getStatusMaxValue(cardOneSelfStatus)
        val targetTwoOrgStatus = targetTwo.target.getStatusMaxValue(cardTwoSelfStatus)


        targetOneStatus.addMaxValueCheck(cardOneSelfStatus, cardOneSelfValue,targetOneOrgStatus)
        targetTwoStatus.addMaxValueCheck(cardTwoSelfStatus, cardTwoSelfValue,targetTwoOrgStatus)
    }
    override fun selfToOpponent(targetOne: BattleStatus, targetTwo: BattleStatus){
        val cardOne = targetOne.card
        val cardTwo = targetTwo.card

        val targetOneStatus = targetOne.status
        val targetTwoStatus = targetTwo.status

        val cardOneSelfNum = cardOne.getEffectSelfNumValue()
        val cardOneSelfStatus = cardOne.effectSelfStat

        val cardTwoOpponentNum = cardTwo.getEffectOpponentNumValue()
        val cardTwoOpponentStatus = cardTwo.effectOpponentStat

        val cardOneSelfValue = cardValueCalculator.calculateCardValue(cardOne, targetOneStatus, cardOneSelfNum)
        val cardTwoOpponentValue = cardValueCalculator.calculateCardValue(cardTwo, targetTwoStatus, cardTwoOpponentNum)



        targetOneStatus.addMaxValueCheck(cardOneSelfStatus, cardOneSelfValue,targetOne.target.getStatusMaxValue(cardOneSelfStatus))
        targetOneStatus.addMaxValueCheck(cardTwoOpponentStatus, cardTwoOpponentValue,targetOne.target.getStatusMaxValue(cardTwoOpponentStatus))

    }
    override fun selfToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){
        val cardOne = targetOne.card
        val cardTwo = targetTwo.card

        val targetOneStatus = targetOne.status
        val targetTwoStatus = targetTwo.status

        val cardOneSelfNum = cardOne.getEffectSelfNumValue()
        val cardOneSelfStatus = cardOne.effectSelfStat

        val cardTwoSelfNum = cardTwo.getEffectSelfNumValue()
        val cardTwoSelfStatus = cardTwo.effectSelfStat

        val cardTwoOpponentNum = cardTwo.getEffectOpponentNumValue()
        val cardTwoOpponentStatus = cardTwo.effectOpponentStat

        val cardOneSelfValue = cardValueCalculator.calculateCardValue(cardOne, targetOneStatus, cardOneSelfNum)
        val cardTwoSelfValue = cardValueCalculator.calculateCardValue(cardTwo, targetTwoStatus, cardTwoSelfNum)
        val cardTwoOpponentValue = cardValueCalculator.calculateCardValue(cardTwo, targetTwoStatus, cardTwoOpponentNum)



        targetOneStatus.addMaxValueCheck(cardOneSelfStatus, cardOneSelfValue,targetOne.target.getStatusMaxValue(cardOneSelfStatus))
        targetOneStatus.addMaxValueCheck(cardTwoOpponentStatus, cardTwoOpponentValue,targetOne.target.getStatusMaxValue(cardTwoOpponentStatus))
        targetTwoStatus.addMaxValueCheck(cardTwoSelfStatus, cardTwoSelfValue,targetTwo.target.getStatusMaxValue(cardTwoSelfStatus))
    }
    override fun opponentToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){
        val cardOne = targetOne.card
        val cardTwo = targetTwo.card

        val targetOneStatus = targetOne.status
        val targetTwoStatus = targetTwo.status

        val cardOneOpponentNum = cardOne.getEffectOpponentNumValue()
        val cardOneOpponentStatus = cardOne.effectOpponentStat

        val cardTwoSelfNum = cardTwo.getEffectSelfNumValue()
        val cardTwoSelfStatus = cardTwo.effectSelfStat

        val cardTwoOpponentNum = cardTwo.getEffectOpponentNumValue()
        val cardTwoOpponentStatus = cardTwo.effectOpponentStat

        val cardOneOpponentValue = cardValueCalculator.calculateCardValue(cardOne, targetOneStatus, cardOneOpponentNum)
        val cardTwoSelfValue = cardValueCalculator.calculateCardValue(cardTwo, targetTwoStatus, cardTwoSelfNum)
        val cardTwoOpponentValue = cardValueCalculator.calculateCardValue(cardTwo, targetTwoStatus, cardTwoOpponentNum)

        targetOneStatus.addMaxValueCheck(cardTwoOpponentStatus, cardTwoOpponentValue,targetOne.target.getStatusMaxValue(cardTwoOpponentStatus))
        targetTwoStatus.addMaxValueCheck(cardOneOpponentStatus, cardOneOpponentValue,targetTwo.target.getStatusMaxValue(cardOneOpponentStatus))
        targetTwoStatus.addMaxValueCheck(cardTwoSelfStatus, cardTwoSelfValue,targetTwo.target.getStatusMaxValue(cardTwoSelfStatus))

    }
    override fun opponentToOpponent(targetOne: BattleStatus, targetTwo: BattleStatus){
        val cardOne = targetOne.card
        val cardTwo = targetTwo.card

        val targetOneStatus = targetOne.status
        val targetTwoStatus = targetTwo.status

        val cardOneOpponentNum = cardOne.getEffectOpponentNumValue()
        val cardOneOpponentStatus = cardOne.effectOpponentStat

        val cardTwoOpponentNum = cardTwo.getEffectOpponentNumValue()
        val cardTwoOpponentStatus = cardTwo.effectOpponentStat

        val cardOneOpponentValue = cardValueCalculator.calculateCardValue(cardOne, targetOneStatus, cardOneOpponentNum)
        val cardTwoOpponentValue = cardValueCalculator.calculateCardValue(cardTwo, targetTwoStatus, cardTwoOpponentNum)

        targetOneStatus.addMaxValueCheck(cardTwoOpponentStatus, cardTwoOpponentValue,targetOne.target.getStatusMaxValue(cardTwoOpponentStatus))
        targetTwoStatus.addMaxValueCheck(cardOneOpponentStatus, cardOneOpponentValue,targetTwo.target.getStatusMaxValue(cardOneOpponentStatus))

    }
    override fun mutualToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){
        val cardOne = targetOne.card
        val cardTwo = targetTwo.card

        val targetOneStatus = targetOne.status
        val targetTwoStatus = targetTwo.status

        val cardOneSelfNum = cardOne.getEffectSelfNumValue()
        val cardOneSelfStatus = cardOne.effectSelfStat
        val cardOneOpponentNum = cardOne.getEffectOpponentNumValue()
        val cardOneOpponentStatus = cardOne.effectOpponentStat

        val cardTwoSelfNum = cardTwo.getEffectSelfNumValue()
        val cardTwoSelfStatus = cardTwo.effectSelfStat
        val cardTwoOpponentNum = cardTwo.getEffectOpponentNumValue()
        val cardTwoOpponentStatus = cardTwo.effectOpponentStat

        val cardOneSelfValue = cardValueCalculator.calculateCardValue(cardOne, targetOneStatus, cardOneSelfNum)
        val cardOneOpponentValue = cardValueCalculator.calculateCardValue(cardOne, targetOneStatus, cardOneOpponentNum)

        val cardTwoSelfValue = cardValueCalculator.calculateCardValue(cardTwo, targetTwoStatus, cardTwoSelfNum)
        val cardTwoOpponentValue = cardValueCalculator.calculateCardValue(cardTwo, targetTwoStatus, cardTwoOpponentNum)

        targetOneStatus.addMaxValueCheck(cardOneSelfStatus, cardOneSelfValue,targetOne.target.getStatusMaxValue(cardOneSelfStatus))
        targetOneStatus.addMaxValueCheck(cardTwoOpponentStatus, cardTwoOpponentValue,targetOne.target.getStatusMaxValue(cardTwoOpponentStatus))

        targetTwoStatus.addMaxValueCheck(cardTwoSelfStatus, cardTwoSelfValue,targetTwo.target.getStatusMaxValue(cardTwoSelfStatus))
        targetTwoStatus.addMaxValueCheck(cardOneOpponentStatus, cardOneOpponentValue,targetTwo.target.getStatusMaxValue(cardOneOpponentStatus))

    }
}