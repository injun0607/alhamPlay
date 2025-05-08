package kr.alham.playground.pattern.cardeffect

import kr.alham.playground.domain.battle.BattleStatus
import kr.alham.playground.domain.enums.CardType
import org.springframework.stereotype.Component

/**
 * 공격 vs 공격 카드 효과
 * 1.
 * 2.
 * 3.
 */
@Component
class AttackToAttackCardEffect() :TargetBasedCardEffect(){
    override fun supportedType(): Pair<CardType,CardType> = Pair(CardType.ATTACK, CardType.ATTACK)
    override fun selfToSelf(targetOne: BattleStatus, targetTwo: BattleStatus){
        val cardOne = targetOne.card
        val cardTwo = targetTwo.card

        val targetOneStatus = targetOne.status
        val targetTwoStatus = targetTwo.status

        val cardOneSelfNum = cardOne.effectSelfNum
        val cardOneStatus = cardOne.effectSelfStat

        val cardTwoSelfNum = cardTwo.effectSelfNum
        val cardTwoStatus = cardTwo.effectSelfStat

        val cardOneDamage = calculateDamage(targetOne)
        val cardTwoDamage = calculateDamage(targetTwo)

        val targetOneAfterEffectStat = targetOneStatus.get(cardOneStatus) - cardOneDamage
        val targetTwoAfterEffectStat = targetTwoStatus.get(cardTwoStatus) - cardTwoDamage

        targetOneStatus.set(cardOneStatus,targetOneAfterEffectStat)
        targetTwoStatus.set(cardTwoStatus,targetTwoAfterEffectStat)
    }
    override fun selfToOpponent(targetOne: BattleStatus, targetTwo: BattleStatus){}
    override fun selfToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){}
    override fun opponentToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){}
    override fun opponentToOpponent(targetOne: BattleStatus, targetTwo: BattleStatus){}
    override fun mutualToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){}
}


@Component
class AttackToDefenceCardEffect() :TargetBasedCardEffect() {
    override fun supportedType(): Pair<CardType, CardType> = Pair(CardType.ATTACK, CardType.DEFENCE)
    override fun selfToSelf(targetOne: BattleStatus, targetTwo: BattleStatus){}
    override fun selfToOpponent(targetOne: BattleStatus, targetTwo: BattleStatus){}
    override fun selfToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){}
    override fun opponentToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){}
    override fun opponentToOpponent(targetOne: BattleStatus, targetTwo: BattleStatus){}
    override fun mutualToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){}


}

@Component
class AttackToEvasionCardEffect() :TargetBasedCardEffect() {
    override fun supportedType(): Pair<CardType, CardType> = Pair(CardType.ATTACK, CardType.EVASION)
    override fun selfToSelf(targetOne: BattleStatus, targetTwo: BattleStatus){}
    override fun selfToOpponent(targetOne: BattleStatus, targetTwo: BattleStatus){}
    override fun selfToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){}
    override fun opponentToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){}
    override fun opponentToOpponent(targetOne: BattleStatus, targetTwo: BattleStatus){}
    override fun mutualToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){}

}



