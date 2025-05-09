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
        val cardOneSelfStatus = cardOne.effectSelfStat

        val cardTwoSelfNum = cardTwo.effectSelfNum
        val cardTwoSelfStatus = cardTwo.effectSelfStat

        val cardOneSelfDamage = cardOne.calculateDamage(targetOneStatus,cardOneSelfNum)
        val cardTwoSelfDamage = cardOne.calculateDamage(targetTwoStatus,cardTwoSelfNum)

        val targetOneAfterEffectStat = targetOneStatus.get(cardOneSelfStatus) - cardOneSelfDamage
        targetOneStatus.set(cardOneSelfStatus,targetOneAfterEffectStat)

        val targetTwoAfterEffectStat = targetTwoStatus.get(cardTwoSelfStatus) - cardTwoSelfDamage
        targetTwoStatus.set(cardTwoSelfStatus,targetTwoAfterEffectStat)
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

        val cardOneSelfDamage = cardOne.calculateDamage(targetOneStatus,cardOneSelfNum)
        val cardTwoOpponentDamage = cardTwo.calculateDamage(targetTwoStatus,cardTwoOpponentNum)

        var targetOneAfterEffectStat = targetOneStatus.get(cardOneSelfStatus) - cardOneSelfDamage
        targetOneStatus.set(cardOneSelfStatus,targetOneAfterEffectStat)

        targetOneAfterEffectStat = targetOneStatus.get(cardTwoOpponentStatus) - cardTwoOpponentDamage
        targetOneStatus.set(cardTwoOpponentStatus,targetOneAfterEffectStat)

    }
    override fun selfToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){
        val cardOne = targetOne.card
        val cardTwo = targetTwo.card

        val targetOneStatus = targetOne.status
        val targetTwoStatus = targetTwo.status

        val targetOneSelfNum = cardOne.effectSelfNum
        val targetOneSelfStatus = cardOne.effectSelfStat

        val targetTwoSelfNum = cardTwo.effectSelfNum
        val targetTwoSelfStatus = cardTwo.effectSelfStat
        val targetTwoOpponentNum = cardTwo.effectOpponentNum
        val targetTwoOpponentStatus = cardTwo.effectOpponentStat

        val cardOneSelfDamage = cardOne.calculateDamage(targetOneStatus,targetOneSelfNum)
        val cardTwoSelfDamage = cardTwo.calculateDamage(targetTwoStatus,targetTwoSelfNum)
        val cardTwoOpponentDamage = cardTwo.calculateDamage(targetTwoStatus,targetTwoOpponentNum)

        var targetOneAfterEffectStat = targetOneStatus.get(targetOneSelfStatus) - cardOneSelfDamage
        targetOneStatus.set(targetOneSelfStatus,targetOneAfterEffectStat)

        targetOneAfterEffectStat = targetOneStatus.get(targetTwoOpponentStatus) - cardTwoOpponentDamage
        targetOneStatus.set(targetTwoOpponentStatus,targetOneAfterEffectStat)

        val targetTwoAfterEffectStat = targetTwoStatus.get(targetTwoSelfStatus) - cardTwoSelfDamage
        targetTwoStatus.set(targetTwoSelfStatus,targetTwoAfterEffectStat)

    }
    override fun opponentToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){
        val cardOne = targetOne.card
        val cardTwo = targetTwo.card

        val targetOneStatus = targetOne.status
        val targetTwoStatus = targetTwo.status

        val targetOneOpponentNum = cardOne.effectOpponentNum
        val targetOneOpponentStatus = cardOne.effectOpponentStat

        val targetTwoSelfNum = cardTwo.effectSelfNum
        val targetTwoSelfStatus = cardTwo.effectSelfStat
        val targetTwoOpponentNum = cardTwo.effectOpponentNum
        val targetTwoOpponentStatus = cardTwo.effectOpponentStat

        //TODO - 각 카드의 속성을 확인해서 데미지 계산로직 추가필요
        val cardOneOpponentDamage = cardOne.calculateDamage(targetOneStatus,targetOneOpponentNum)
        val cardTwoSelfDamage = cardTwo.calculateDamage(targetTwoStatus,targetTwoSelfNum)
        val cardTwoOpponentDamage = cardTwo.calculateDamage(targetTwoStatus,targetTwoOpponentNum)

        val targetOneEffectStat = targetOneStatus.get(targetTwoOpponentStatus) - cardTwoOpponentDamage
        targetOneStatus.set(targetTwoOpponentStatus,targetOneEffectStat)

        var targetTwoEffectStat = targetTwoStatus.get(targetOneOpponentStatus) - cardOneOpponentDamage
        targetTwoStatus.set(targetOneOpponentStatus,targetTwoEffectStat)

        targetTwoEffectStat = targetTwoStatus.get(targetTwoSelfStatus) - cardTwoSelfDamage
        targetTwoStatus.set(targetTwoSelfStatus,targetTwoEffectStat)

    }
    override fun opponentToOpponent(targetOne: BattleStatus, targetTwo: BattleStatus){

        val cardOne = targetOne.card
        val cardTwo = targetTwo.card

        val targetOneStatus = targetOne.status
        val targetTwoStatus = targetTwo.status

        val targetOneOpponentNum = cardOne.effectOpponentNum
        val targetOneOpponentStatus = cardOne.effectOpponentStat

        val targetTwoOpponentNum = cardTwo.effectOpponentNum
        val targetTwoOpponentStatus = cardTwo.effectOpponentStat

        //TODO - 각 카드의 속성을 확인해서 데미지 계산로직 추가필요
        val cardOneOpponentDamage = cardOne.calculateDamage(targetOneStatus,targetOneOpponentNum)
        val cardTwoOpponentDamage = cardTwo.calculateDamage(targetTwoStatus,targetTwoOpponentNum)

        val targetOneEffectStat = targetOneStatus.get(targetOneOpponentStatus) - cardTwoOpponentDamage
        targetOneStatus.set(targetTwoOpponentStatus,targetOneEffectStat)

        val targetTwoEffectStat = targetTwoStatus.get(targetTwoOpponentStatus) - cardOneOpponentDamage
        targetTwoStatus.set(targetOneOpponentStatus,targetTwoEffectStat)

    }
    override fun mutualToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){
        val cardOne = targetOne.card
        val cardTwo = targetTwo.card

        val targetOneStatus = targetOne.status
        val targetTwoStatus = targetTwo.status

        val targetOneSelfNum = cardOne.effectSelfNum
        val targetOneSelfStatus = cardOne.effectSelfStat
        val targetOneOpponentNum = cardOne.effectOpponentNum
        val targetOneOpponentStatus = cardOne.effectOpponentStat

        val targetTwoSelfNum = cardTwo.effectSelfNum
        val targetTwoSelfStatus = cardTwo.effectSelfStat
        val targetTwoOpponentNum = cardTwo.effectOpponentNum
        val targetTwoOpponentStatus = cardTwo.effectOpponentStat

        //TODO - 각 카드의 속성을 확인해서 데미지 계산로직 추가필요
        val cardOneSelfDamage = cardOne.calculateDamage(targetOneStatus,targetOneSelfNum)
        val cardOneOpponentDamage = cardOne.calculateDamage(targetOneStatus,targetOneOpponentNum)

        val cardTwoSelfDamage = cardTwo.calculateDamage(targetTwoStatus,targetTwoSelfNum)
        val cardTwoOpponentDamage = cardTwo.calculateDamage(targetTwoStatus,targetTwoOpponentNum)

        var targetOneEffectStat = targetOneStatus.get(targetOneSelfStatus) - cardOneSelfDamage
        targetOneStatus.set(targetOneSelfStatus,targetOneEffectStat)
        targetOneEffectStat = targetOneStatus.get(targetTwoOpponentStatus) - cardTwoOpponentDamage
        targetOneStatus.set(targetTwoOpponentStatus,targetOneEffectStat)

        var targetTwoEffectStat = targetTwoStatus.get(targetTwoSelfStatus) - cardTwoSelfDamage
        targetTwoStatus.set(targetTwoSelfStatus,targetTwoEffectStat)
        targetTwoEffectStat = targetTwoStatus.get(targetOneOpponentStatus) - cardOneOpponentDamage
        targetTwoStatus.set(targetOneOpponentStatus,targetTwoEffectStat)

    }
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



