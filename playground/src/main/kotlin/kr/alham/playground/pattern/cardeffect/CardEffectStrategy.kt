package kr.alham.playground.pattern.cardeffect

import kr.alham.playground.domain.battle.BattleStatus
import kr.alham.playground.domain.common.TargetElementStatus
import kr.alham.playground.domain.common.TargetElementStatusMap
import kr.alham.playground.domain.enums.CardTarget
import kr.alham.playground.domain.enums.CardType
import kr.alham.playground.pattern.calculator.CardValueCalculator

interface CardEffectStrategy {
    fun supportedType(): Pair<CardType,CardType>?
    fun applyEffect(targetOne: BattleStatus, targetTwo: BattleStatus)

}

abstract class TargetBasedCardEffect(
    protected val cardValueCalculator: CardValueCalculator
): CardEffectStrategy {
    override fun supportedType(): Pair<CardType, CardType>? = null

    override fun applyEffect(targetOne: BattleStatus, targetTwo: BattleStatus){
        val cardOneTarget = targetOne.card.cardTarget
        val cardTwoTarget = targetTwo.card.cardTarget

        if(cardOneTarget == CardTarget.SELF && cardTwoTarget == CardTarget.SELF){
            //셀프 vs 셀프
            selfToSelf(targetOne, targetTwo)
        }else if(cardOneTarget == CardTarget.SELF && cardTwoTarget == CardTarget.OPPONENT){
            //셀프 vs 상대
            selfToOpponent(targetOne, targetTwo)
        }else if(cardTwoTarget == CardTarget.SELF && cardOneTarget == CardTarget.OPPONENT) {
            //상대 vs 셀프
            selfToOpponent(targetTwo, targetOne)
        }else if(cardOneTarget == CardTarget.SELF && cardTwoTarget == CardTarget.MUTUAL) {
            //셀프 vs 상호작용
            selfToMutual(targetOne, targetTwo)
        }else if(cardTwoTarget == CardTarget.SELF && cardOneTarget == CardTarget.MUTUAL) {
            //상호작용 vs 셀프
            selfToMutual(targetTwo, targetOne)
        }else if(cardOneTarget == CardTarget.OPPONENT && cardTwoTarget == CardTarget.MUTUAL) {
            //상대 vs 상호작용
            opponentToMutual(targetOne, targetTwo)
        }else if(cardTwoTarget == CardTarget.OPPONENT && cardOneTarget == CardTarget.MUTUAL) {
            //상호작용 vs 상대
            opponentToMutual(targetTwo, targetOne)
        }else if(cardOneTarget == CardTarget.OPPONENT && cardTwoTarget == CardTarget.OPPONENT){
            //상대 vs 상대
            opponentToOpponent(targetOne,targetTwo)
        } else{
            //상호작용 vs 상호작용
            mutualToMutual(targetOne, targetTwo)
        }

    }

    protected open fun selfToSelf(targetOne: BattleStatus, targetTwo: BattleStatus){}
    protected open fun selfToOpponent(targetOne: BattleStatus, targetTwo: BattleStatus){}
    protected open fun selfToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){}
    protected open fun opponentToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){}
    protected open fun opponentToOpponent(targetOne: BattleStatus, targetTwo: BattleStatus){}
    protected open fun mutualToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){}

    protected open fun calculateDamage(attackNum: Double, defenceNum: Double, defenderStatus: TargetElementStatusMap): Double {
        var damage = attackNum
        val calculateDefenceNum = defenceNum + defenderStatus.get(TargetElementStatus.DEF)

        if(damage > calculateDefenceNum){
            damage = attackNum - calculateDefenceNum
        }else{
            damage = 0.0
        }
        return damage

    }

    protected open fun calculateEvasionRate(attackNum: Double, evasionNum: Double): Double {

        var damage = attackNum
        val evasionRate = Math.random()
        //회피판단
        if(evasionRate < evasionNum) {
            println("회피 성공")
            damage = 0.0
        }else{
            println("회피 실패")
        }

        println("회피확률 : $evasionNum, 수치 : $evasionRate")

        return damage
    }

    protected open fun distinctTarget(cardType: CardType,targetOne: BattleStatus, targetTwo: BattleStatus): BattleStatus {
        if(targetOne.card.cardType == cardType) {
            return targetOne
        }else{
            return targetTwo
        }
    }

    protected open fun checkTarget(cardType: CardType, target: BattleStatus): Boolean {
        return target.card.cardType == cardType
    }


}