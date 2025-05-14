package kr.alham.playground.pattern.cardeffect

import kr.alham.playground.domain.battle.BattleStatus
import kr.alham.playground.domain.common.TargetElementStatus
import kr.alham.playground.domain.common.TargetElementStatusMap
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

        val cardOneSelfDamage = cardOne.calculateValue(targetOneStatus,cardOneSelfNum)
        val cardTwoSelfDamage = cardOne.calculateValue(targetTwoStatus,cardTwoSelfNum)

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

        val cardOneSelfDamage = cardOne.calculateValue(targetOneStatus,cardOneSelfNum)
        val cardTwoOpponentDamage = cardTwo.calculateValue(targetTwoStatus,cardTwoOpponentNum)

        var targetOneAfterEffectStat = targetOneStatus.get(cardOneSelfStatus) - cardOneSelfDamage
        targetOneStatus.set(cardOneSelfStatus,targetOneAfterEffectStat)

        targetOneAfterEffectStat = targetOneStatus.get(cardTwoOpponentStatus) - calculateDamage(cardTwoOpponentDamage,0.0,targetOneStatus)
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

        val cardOneSelfDamage = cardOne.calculateValue(targetOneStatus,targetOneSelfNum)
        val cardTwoSelfDamage = cardTwo.calculateValue(targetTwoStatus,targetTwoSelfNum)
        val cardTwoOpponentDamage = cardTwo.calculateValue(targetTwoStatus,targetTwoOpponentNum)

        var targetOneAfterEffectStat = targetOneStatus.get(targetOneSelfStatus) - cardOneSelfDamage
        targetOneStatus.set(targetOneSelfStatus,targetOneAfterEffectStat)

        targetOneAfterEffectStat = targetOneStatus.get(targetTwoOpponentStatus) - calculateDamage(cardTwoOpponentDamage,0.0,targetOneStatus)
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
        val cardOneOpponentDamage = cardOne.calculateValue(targetOneStatus,targetOneOpponentNum)
        val cardTwoSelfDamage = cardTwo.calculateValue(targetTwoStatus,targetTwoSelfNum)
        val cardTwoOpponentDamage = cardTwo.calculateValue(targetTwoStatus,targetTwoOpponentNum)

        val targetOneEffectStat = targetOneStatus.get(targetTwoOpponentStatus) - calculateDamage(cardTwoOpponentDamage,0.0,targetOneStatus)
        targetOneStatus.set(targetTwoOpponentStatus,targetOneEffectStat)

        var targetTwoEffectStat = targetTwoStatus.get(targetOneOpponentStatus) - calculateDamage(cardOneOpponentDamage,0.0,targetTwoStatus)
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
        val cardOneOpponentDamage = cardOne.calculateValue(targetOneStatus,targetOneOpponentNum)
        val cardTwoOpponentDamage = cardTwo.calculateValue(targetTwoStatus,targetTwoOpponentNum)

        val targetOneEffectStat = targetOneStatus.get(targetOneOpponentStatus) - calculateDamage(cardTwoOpponentDamage,0.0,targetOneStatus)
        targetOneStatus.set(targetTwoOpponentStatus,targetOneEffectStat)

        val targetTwoEffectStat = targetTwoStatus.get(targetTwoOpponentStatus) - calculateDamage(cardOneOpponentDamage,0.0,targetTwoStatus)
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
        val cardOneSelfDamage = cardOne.calculateValue(targetOneStatus,targetOneSelfNum)
        val cardOneOpponentDamage = cardOne.calculateValue(targetOneStatus,targetOneOpponentNum)

        val cardTwoSelfDamage = cardTwo.calculateValue(targetTwoStatus,targetTwoSelfNum)
        val cardTwoOpponentDamage = cardTwo.calculateValue(targetTwoStatus,targetTwoOpponentNum)

        var targetOneEffectStat = targetOneStatus.get(targetOneSelfStatus) - cardOneSelfDamage
        targetOneStatus.set(targetOneSelfStatus,targetOneEffectStat)
        targetOneEffectStat = targetOneStatus.get(targetTwoOpponentStatus) - calculateDamage(cardTwoOpponentDamage,0.0,targetOneStatus)
        targetOneStatus.set(targetTwoOpponentStatus,targetOneEffectStat)

        var targetTwoEffectStat = targetTwoStatus.get(targetTwoSelfStatus) - cardTwoSelfDamage
        targetTwoStatus.set(targetTwoSelfStatus,targetTwoEffectStat)
        targetTwoEffectStat = targetTwoStatus.get(targetOneOpponentStatus) - calculateDamage(cardOneOpponentDamage,0.0,targetTwoStatus)
        targetTwoStatus.set(targetOneOpponentStatus,targetTwoEffectStat)

    }
}


@Component
class AttackToDefenceCardEffect() :TargetBasedCardEffect() {
    override fun supportedType(): Pair<CardType, CardType> = Pair(CardType.ATTACK, CardType.DEFENCE)
    override fun selfToSelf(targetOne: BattleStatus, targetTwo: BattleStatus){

        //사실상 상대방 카드에 대한 효과가 없음
        val attacker = distinctTarget(CardType.ATTACK,targetOne,targetTwo)

        val attackCard = attacker.card

        val attackerStatus = attacker.status

        val attackCardSelfNum = attackCard.effectSelfNum
        val attackCardSelfStatus = attackCard.effectSelfStat

        val attackCardDamage = attackCard.calculateValue(attackerStatus,attackCardSelfNum)

        val attackerEffectStat = attackerStatus.get(attackCardSelfStatus) - attackCardDamage
        attackerStatus.set(attackCardSelfStatus,attackerEffectStat)

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

        val cardOneSelfValue = cardOne.calculateValue(targetOneStatus,cardOneSelfNum)
        val cardTwoOpponentValue = cardTwo.calculateValue(targetTwoStatus,cardTwoOpponentNum)

        var damage = 0.0
        var effectedStat = 0.0

        //self가 공격인경우
        if(checkTarget(CardType.ATTACK,targetOne)){
            //방어 계산
            if(cardOneSelfValue > cardTwoOpponentValue) {
                damage = cardOneSelfValue - cardTwoOpponentValue
            }

            effectedStat = targetOneStatus.get(cardOneSelfStatus) - damage
        }else{
            //self가 방어인 경우
            damage = calculateDamage(cardTwoOpponentValue,cardOneSelfValue,targetOneStatus)
            effectedStat = targetOneStatus.get(cardTwoOpponentStatus) - damage
        }

        targetOneStatus.set(cardOneSelfStatus,effectedStat)

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

        val cardOneSelfValue = cardOne.calculateValue(targetOneStatus,cardOneSelfNum)

        val cardTwoSelfValue = cardTwo.calculateValue(targetTwoStatus,cardTwoSelfNum)
        val cardTwoOpponentValue = cardTwo.calculateValue(targetTwoStatus,cardTwoOpponentNum)

        var damage = 0.0

        var targetOneEffectStat = targetOneStatus.get(cardOneSelfStatus)
        var targetTwoEffectStat = targetTwoStatus.get(cardTwoSelfStatus)


        //self가 공격 mutual이 방어인경우
        if(checkTarget(CardType.ATTACK,targetOne)){
            if(cardOneSelfValue > cardTwoOpponentValue){
                damage = cardOneSelfValue - cardTwoOpponentValue
            }
            targetOneEffectStat -= damage
        }else{
            //self가 방어 mutual이 공격인 경우
            damage = calculateDamage(cardTwoOpponentValue,cardOneSelfValue,targetOneStatus)
            targetOneEffectStat = targetOneStatus.get(cardTwoOpponentStatus) - damage
            targetTwoEffectStat = targetTwoStatus.get(cardTwoSelfStatus) - cardTwoSelfValue
        }

        targetOneStatus.set(cardOneSelfStatus,targetOneEffectStat)
        targetTwoStatus.set(cardTwoSelfStatus,targetTwoEffectStat)

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

        val cardOneOpponentValue = cardOne.calculateValue(targetOneStatus,cardOneOpponentNum)

        val cardTwoSelfValue = cardTwo.calculateValue(targetTwoStatus,cardTwoSelfNum)
        val cardTwoOpponentValue = cardTwo.calculateValue(targetTwoStatus,cardTwoOpponentNum)

        var damage = 0.0

        var targetOneEffectStat = targetOneStatus.get(cardTwoOpponentStatus)
        var targetTwoEffectStat = targetTwoStatus.get(cardOneOpponentStatus)

        if(checkTarget(CardType.ATTACK,targetOne)) {
            //공격이 상대방 타겟인경우 경우
            damage = calculateDamage(cardOneOpponentValue,cardTwoSelfValue,targetTwoStatus)
            targetTwoEffectStat -= damage
        }else{
            //방어가 상대방 타겟인경우
            if(cardTwoSelfValue > cardOneOpponentValue){
                damage = cardTwoSelfValue - cardOneOpponentValue
                targetOneEffectStat -= damage
            }
            targetOneEffectStat -= calculateDamage(cardTwoOpponentValue,0.0,targetOneStatus)
        }

        targetOneStatus.set(cardTwoOpponentStatus,targetOneEffectStat)
        targetTwoStatus.set(cardOneOpponentStatus,targetTwoEffectStat)

    }
    override fun opponentToOpponent(targetOne: BattleStatus, targetTwo: BattleStatus){

        val attacker = distinctTarget(CardType.ATTACK,targetOne,targetTwo)
        val defender = distinctTarget(CardType.DEFENCE,targetOne,targetTwo)

        val attackerCard = attacker.card
        val defenderCard = defender.card

        val attackerStatus = attacker.status
        val defenderStatus = defender.status

        val attackNum = attackerCard.effectOpponentNum
        val attackStatus = attackerCard.effectOpponentStat

        val defenderEffectStatus = defenderStatus.get(attackStatus) - calculateDamage(attackNum,0.0,defenderStatus)

        defenderStatus.set(attackStatus,defenderEffectStatus)

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

        val cardOneSelfValue = cardOne.calculateValue(targetOneStatus,cardOneSelfNum)
        val cardOneOpponentValue = cardOne.calculateValue(targetOneStatus,cardOneOpponentNum)

        val cardTwoSelfValue = cardTwo.calculateValue(targetTwoStatus,cardTwoSelfNum)
        val cardTwoOpponentValue = cardTwo.calculateValue(targetTwoStatus,cardTwoOpponentNum)

        var targetOneEffectStatus = targetOneStatus.get(cardOneSelfStatus)
        var targetTwoEffectStatus = targetTwoStatus.get(cardTwoSelfStatus)

        if(checkTarget(CardType.ATTACK,targetOne)) {
            //targetOne이 attackCard인것
            if(cardOneSelfValue > cardTwoOpponentValue){
                //cardTwo가 cardOne데미지를 방어
                val damage = cardOneSelfValue - cardTwoOpponentValue
                targetOneEffectStatus -= damage
            }
            val effectedTargetTwoDamage = calculateDamage(cardOneOpponentValue,cardTwoSelfValue,targetTwoStatus)
            targetTwoEffectStatus -= effectedTargetTwoDamage
        }else{
            //targetOne이 defenceCard인것
            val effectedTargetOneDamage = calculateDamage(cardTwoOpponentNum,cardOneSelfValue,targetOneStatus)
            targetOneEffectStatus -= effectedTargetOneDamage
            if(cardTwoSelfValue > cardOneOpponentValue){
                //cardOne가 cardTwo데미지를 방어
                val damage = cardTwoSelfValue - cardOneOpponentValue
                targetTwoEffectStatus -= damage
            }
        }

        targetOneStatus.set(cardOneSelfStatus,targetOneEffectStatus)
        targetTwoStatus.set(cardOneOpponentStatus,targetTwoEffectStatus)

    }

}

@Component
class AttackToEvasionCardEffect() :TargetBasedCardEffect() {
    override fun supportedType(): Pair<CardType, CardType> = Pair(CardType.ATTACK, CardType.EVASION)
    override fun selfToSelf(targetOne: BattleStatus, targetTwo: BattleStatus){
        val attacker = distinctTarget(CardType.ATTACK,targetOne,targetTwo)

        val attackCard = attacker.card

        val attackStatus = attacker.status

        val attackNum = attackCard.effectSelfNum
        val attackStatusType = attackCard.effectSelfStat

        val attackCardDamage = attackCard.calculateValue(attackStatus,attackNum)
        val attackCardEffectStat = attackStatus.get(attackStatusType) - attackCardDamage

        attackStatus.set(attackStatusType,attackCardEffectStat)

    }
    override fun selfToOpponent(targetOne: BattleStatus, targetTwo: BattleStatus){

    }
    override fun selfToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){

    }
    override fun opponentToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){

    }
    override fun opponentToOpponent(targetOne: BattleStatus, targetTwo: BattleStatus){

    }
    override fun mutualToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){

    }

}



