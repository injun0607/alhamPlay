package kr.alham.playground.system.cardeffect

import kr.alham.playground.domain.battle.BattleStatus
import kr.alham.playground.domain.enums.CardType
import kr.alham.playground.system.calculator.CardValueCalculator
import kr.alham.playground.system.calculator.DamageCalculator
import kr.alham.playground.system.calculator.EvasionRateCalculator
import org.springframework.stereotype.Component
import kotlin.math.abs

/**
 * 공격 vs 공격 카드 효과
 * 1.
 * 2.
 * 3.
 */
@Component
class AttackToAttackCardEffect(
    cardValueCalculator: CardValueCalculator,
    private val damageCalculator: DamageCalculator
) :TargetBasedCardEffect(cardValueCalculator) {
    override fun supportedType(): Pair<CardType,CardType> = Pair(CardType.ATTACK, CardType.ATTACK)

    override fun selfToSelf(targetOne: BattleStatus, targetTwo: BattleStatus){
        val cardOne = targetOne.card
        val cardTwo = targetTwo.card

        val targetOneStatus = targetOne.status
        val targetTwoStatus = targetTwo.status

        val cardOneSelfNum = cardOne.getEffectSelfNumValue()
        val cardOneSelfStatus = cardOne.effectSelfStat

        val cardTwoSelfNum = cardTwo.getEffectSelfNumValue()
        val cardTwoSelfStatus = cardTwo.effectSelfStat

        val cardOneSelfDamage = cardValueCalculator.calculateCardValue(cardOne,targetOneStatus,cardOneSelfNum)
        val cardTwoSelfDamage = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,cardTwoSelfNum)

        targetOneStatus.addMaxValueCheck(cardOneSelfStatus,cardOneSelfDamage,null)
        targetTwoStatus.addMaxValueCheck(cardTwoSelfStatus,cardTwoSelfDamage,null)

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

        val cardOneSelfDamage = cardValueCalculator.calculateCardValue(cardOne,targetOneStatus,cardOneSelfNum)
        val cardTwoOpponentDamage = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,cardTwoOpponentNum)

        val targetTwoOpponentDamage = damageCalculator.calculateDamage(cardTwoOpponentDamage,0.0,targetOneStatus)

        targetOneStatus.addMaxValueCheck(cardOneSelfStatus,cardOneSelfDamage,null)
        targetOneStatus.addMaxValueCheck(cardTwoOpponentStatus,targetTwoOpponentDamage,null)

    }
    override fun selfToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){
        val cardOne = targetOne.card
        val cardTwo = targetTwo.card

        val targetOneStatus = targetOne.status
        val targetTwoStatus = targetTwo.status

        val targetOneSelfNum = cardOne.getEffectSelfNumValue()
        val targetOneSelfStatus = cardOne.effectSelfStat

        val targetTwoSelfNum = cardTwo.getEffectSelfNumValue()
        val targetTwoSelfStatus = cardTwo.effectSelfStat
        val targetTwoOpponentNum = cardTwo.getEffectOpponentNumValue()
        val targetTwoOpponentStatus = cardTwo.effectOpponentStat

        val cardOneSelfDamage = cardValueCalculator.calculateCardValue(cardOne,targetOneStatus,targetOneSelfNum)
        val cardTwoSelfDamage = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,targetTwoSelfNum)
        val cardTwoOpponentDamage = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,targetTwoOpponentNum)


        val targetTwoOpponentDamage =  damageCalculator.calculateDamage(cardTwoOpponentDamage,0.0,targetOneStatus)
        targetOneStatus.addMaxValueCheck(targetOneSelfStatus,cardOneSelfDamage,null)
        targetOneStatus.addMaxValueCheck(targetOneSelfStatus,targetTwoOpponentDamage,null)
        targetTwoStatus.addMaxValueCheck(targetTwoSelfStatus,cardTwoSelfDamage,null)

    }
    override fun opponentToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){
        val cardOne = targetOne.card
        val cardTwo = targetTwo.card

        val targetOneStatus = targetOne.status
        val targetTwoStatus = targetTwo.status

        val targetOneOpponentNum = cardOne.getEffectOpponentNumValue()
        val targetOneOpponentStatus = cardOne.effectOpponentStat

        val targetTwoSelfNum = cardTwo.getEffectSelfNumValue()
        val targetTwoSelfStatus = cardTwo.effectSelfStat
        val targetTwoOpponentNum = cardTwo.getEffectOpponentNumValue()
        val targetTwoOpponentStatus = cardTwo.effectOpponentStat

        //TODO - 각 카드의 속성을 확인해서 데미지 계산로직 추가필요
        val cardOneOpponentDamage = cardValueCalculator.calculateCardValue(cardOne,targetOneStatus,targetOneOpponentNum)
        val cardTwoSelfDamage = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,targetTwoSelfNum)
        val cardTwoOpponentDamage = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,targetTwoOpponentNum)

        val targetOneOpponentDamage = damageCalculator.calculateDamage(cardOneOpponentDamage,0.0,targetTwoStatus)
        val targetTwoOpponentDamage = damageCalculator.calculateDamage(cardTwoOpponentDamage,0.0,targetOneStatus)

        targetOneStatus.addMaxValueCheck(targetTwoOpponentStatus,targetTwoOpponentDamage,null)


        targetTwoStatus.addMaxValueCheck(targetTwoSelfStatus,cardTwoSelfDamage,null)
        targetTwoStatus.addMaxValueCheck(targetOneOpponentStatus,targetOneOpponentDamage,null)

    }
    override fun opponentToOpponent(targetOne: BattleStatus, targetTwo: BattleStatus){

        val cardOne = targetOne.card
        val cardTwo = targetTwo.card

        val targetOneStatus = targetOne.status
        val targetTwoStatus = targetTwo.status

        val targetOneOpponentNum = cardOne.getEffectOpponentNumValue()
        val targetOneOpponentStatus = cardOne.effectOpponentStat

        val targetTwoOpponentNum = cardTwo.getEffectOpponentNumValue()
        val targetTwoOpponentStatus = cardTwo.effectOpponentStat

        //TODO - 각 카드의 속성을 확인해서 데미지 계산로직 추가필요
        val cardOneOpponentDamage = cardValueCalculator.calculateCardValue(cardOne,targetOneStatus,targetOneOpponentNum)
        val cardTwoOpponentDamage = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,targetTwoOpponentNum)

        val targetOneOpponentDamage = damageCalculator.calculateDamage(cardOneOpponentDamage,0.0,targetTwoStatus)
        val targetTwoOpponentDamage = damageCalculator.calculateDamage(cardTwoOpponentDamage,0.0,targetOneStatus)

        targetOneStatus.addMaxValueCheck(targetTwoOpponentStatus,targetTwoOpponentDamage,null)
        targetTwoStatus.addMaxValueCheck(targetOneOpponentStatus,targetOneOpponentDamage,null)

    }
    override fun mutualToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){
        val cardOne = targetOne.card
        val cardTwo = targetTwo.card

        val targetOneStatus = targetOne.status
        val targetTwoStatus = targetTwo.status

        val targetOneSelfNum = cardOne.getEffectSelfNumValue()
        val targetOneSelfStatus = cardOne.effectSelfStat
        val targetOneOpponentNum = cardOne.getEffectOpponentNumValue()
        val targetOneOpponentStatus = cardOne.effectOpponentStat

        val targetTwoSelfNum = cardTwo.getEffectSelfNumValue()
        val targetTwoSelfStatus = cardTwo.effectSelfStat
        val targetTwoOpponentNum = cardTwo.getEffectOpponentNumValue()
        val targetTwoOpponentStatus = cardTwo.effectOpponentStat

        //TODO - 각 카드의 속성을 확인해서 데미지 계산로직 추가필요
        val cardOneSelfDamage = cardValueCalculator.calculateCardValue(cardOne,targetOneStatus,targetOneSelfNum)
        val cardOneOpponentDamage = cardValueCalculator.calculateCardValue(cardOne,targetOneStatus,targetOneOpponentNum)

        val cardTwoSelfDamage = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,targetTwoSelfNum)
        val cardTwoOpponentDamage = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,targetTwoOpponentNum)

        val targetOneOpponentDamage = damageCalculator.calculateDamage(cardOneOpponentDamage,0.0,targetTwoStatus)
        val targetTwoOpponentDamage = damageCalculator.calculateDamage(cardTwoOpponentDamage,0.0,targetOneStatus)


        targetOneStatus.addMaxValueCheck(targetOneSelfStatus,cardOneSelfDamage,null)
        targetOneStatus.addMaxValueCheck(targetTwoOpponentStatus,targetTwoOpponentDamage,null)

        targetTwoStatus.addMaxValueCheck(targetTwoSelfStatus,cardTwoSelfDamage,null)
        targetTwoStatus.addMaxValueCheck(targetOneOpponentStatus,targetOneOpponentDamage,null)

    }
}


@Component
class AttackToDefenceCardEffect(
    cardValueCalculator: CardValueCalculator,
    private val damageCalculator: DamageCalculator
) :TargetBasedCardEffect(cardValueCalculator) {
    override fun supportedType(): Pair<CardType, CardType> = Pair(CardType.ATTACK, CardType.DEFENCE)
    override fun selfToSelf(targetOne: BattleStatus, targetTwo: BattleStatus){

        //사실상 상대방 카드에 대한 효과가 없음
        val attacker = distinctTarget(CardType.ATTACK,targetOne,targetTwo)

        val attackCard = attacker.card

        val attackerStatus = attacker.status

        val attackCardSelfNum = attackCard.getEffectSelfNumValue()
        val attackCardSelfStatus = attackCard.effectSelfStat

        val attackCardDamage = cardValueCalculator.calculateCardValue(attackCard,attackerStatus,attackCardSelfNum)

        attackerStatus.addMaxValueCheck(attackCardSelfStatus,attackCardDamage,null)
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

        val cardOneSelfValue = cardValueCalculator.calculateCardValue(cardOne,targetOneStatus,cardOneSelfNum)
        val cardTwoOpponentValue = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,cardTwoOpponentNum)

        var damage = 0.0

        //self가 공격인경우
        if(checkTarget(CardType.ATTACK,targetOne)){
            //방어 계산
            if(abs(cardOneSelfValue) > cardTwoOpponentValue) {
                damage = -(abs(cardOneSelfValue) - cardTwoOpponentValue)
            }
            targetOneStatus.addMaxValueCheck(cardOneSelfStatus,damage,null)
        }else{
            //self가 방어인 경우
            damage = damageCalculator.calculateDamage(cardTwoOpponentValue,cardOneSelfValue,targetOneStatus)
            targetOneStatus.addMaxValueCheck(cardTwoOpponentStatus,damage,null)
        }


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

        val cardOneSelfValue = cardValueCalculator.calculateCardValue(cardOne,targetOneStatus,cardOneSelfNum)

        val cardTwoSelfValue = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,cardTwoSelfNum)
        val cardTwoOpponentValue = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,cardTwoOpponentNum)

        var damage = 0.0

        //self가 공격 mutual이 방어인경우
        if(checkTarget(CardType.ATTACK,targetOne)){
            if(abs(cardOneSelfValue) > cardTwoOpponentValue){
                damage = -(abs(cardOneSelfValue) - cardTwoOpponentValue)
            }
            targetOneStatus.addMaxValueCheck(cardOneSelfStatus,damage,null)
        }else{
            //self가 방어 mutual이 공격인 경우
            damage = damageCalculator.calculateDamage(cardTwoOpponentValue,cardOneSelfValue,targetOneStatus)

            targetOneStatus.addMaxValueCheck(cardTwoOpponentStatus,damage,null)
            targetTwoStatus.addMaxValueCheck(cardTwoSelfStatus,cardTwoSelfValue,null)

        }

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

        val cardOneOpponentValue = cardValueCalculator.calculateCardValue(cardOne,targetOneStatus,cardOneOpponentNum)

        val cardTwoSelfValue = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,cardTwoSelfNum)
        val cardTwoOpponentValue = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,cardTwoOpponentNum)

        var damage = 0.0

        if(checkTarget(CardType.ATTACK,targetOne)) {
            //공격이 상대방 타겟인경우 경우
            damage = damageCalculator.calculateDamage(cardOneOpponentValue,cardTwoSelfValue,targetTwoStatus)
            targetTwoStatus.addMaxValueCheck(cardOneOpponentStatus,damage,null)
        }else{

            //방어가 상대방 타겟 공격이 상호인경우
            if(abs(cardTwoSelfValue) > cardOneOpponentValue){
                damage = -(abs(cardTwoSelfValue) - cardOneOpponentValue)
                targetTwoStatus.addMaxValueCheck(cardTwoSelfStatus,damage,null)
            }

            val targetTwoOpponentDamage = damageCalculator.calculateDamage(cardTwoOpponentValue,0.0,targetOneStatus)
            targetOneStatus.addMaxValueCheck(cardTwoOpponentStatus,targetTwoOpponentDamage,null)
        }

    }
    override fun opponentToOpponent(targetOne: BattleStatus, targetTwo: BattleStatus){

        val attacker = distinctTarget(CardType.ATTACK,targetOne,targetTwo)
        val defender = distinctTarget(CardType.DEFENCE,targetOne,targetTwo)

        val attackerCard = attacker.card

        val attackerStatus = attacker.status
        val defenderStatus = defender.status

        val attackNum = attackerCard.getEffectOpponentNumValue()
        val attackStatus = attackerCard.effectOpponentStat

        val attackCardValue = cardValueCalculator.calculateCardValue(attackerCard,attackerStatus,attackNum)
        defenderStatus.addMaxValueCheck(attackStatus,attackCardValue,null)

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

        val cardOneSelfValue = cardValueCalculator.calculateCardValue(cardOne,targetOneStatus,cardOneSelfNum)
        val cardOneOpponentValue = cardValueCalculator.calculateCardValue(cardOne,targetOneStatus,cardOneOpponentNum)

        val cardTwoSelfValue = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,cardTwoSelfNum)
        val cardTwoOpponentValue = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,cardTwoOpponentNum)

        if(checkTarget(CardType.ATTACK,targetOne)) {
            //targetOne이 attackCard인것
            if(abs(cardOneSelfValue) > cardTwoOpponentValue){
                //cardTwo가 cardOne데미지를 방어
                val damage = -(abs(cardOneSelfValue) - cardTwoOpponentValue)
                targetOneStatus.addMaxValueCheck(cardOneSelfStatus,damage,null)
            }
            val effectedTargetTwoDamage = damageCalculator.calculateDamage(cardOneOpponentValue,cardTwoSelfValue,targetTwoStatus)
            targetTwoStatus.addMaxValueCheck(cardOneOpponentStatus, effectedTargetTwoDamage,null)
        }else{
            //targetOne이 defenceCard인것
            val effectedTargetOneDamage = damageCalculator.calculateDamage(cardTwoOpponentNum,cardOneSelfValue,targetOneStatus)
            targetOneStatus.addMaxValueCheck(cardTwoOpponentStatus, effectedTargetOneDamage,null)
            if(abs(cardTwoSelfValue) > cardOneOpponentValue){
                //cardOne가 cardTwo데미지를 방어
                val damage = -(abs(cardTwoSelfValue) - cardOneOpponentValue)
                targetTwoStatus.addMaxValueCheck(cardTwoSelfStatus,damage,null)
            }
        }


    }

}

@Component
class AttackToEvasionCardEffect(
    cardValueCalculator: CardValueCalculator,
    private val damageCalculator: DamageCalculator,
    private val evasionRateCalculator: EvasionRateCalculator
) :TargetBasedCardEffect(cardValueCalculator) {
    override fun supportedType(): Pair<CardType, CardType> = Pair(CardType.ATTACK, CardType.EVASION)
    override fun selfToSelf(targetOne: BattleStatus, targetTwo: BattleStatus){
        val attacker = distinctTarget(CardType.ATTACK,targetOne,targetTwo)

        val attackCard = attacker.card

        val attackStatus = attacker.status

        val attackNum = attackCard.getEffectSelfNumValue()
        val attackStatusType = attackCard.effectSelfStat

        val attackCardDamage = cardValueCalculator.calculateCardValue(attackCard,attackStatus,attackNum)

        attackStatus.addMaxValueCheck(attackStatusType, attackCardDamage, null)

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

        var cardOneSelfValue = cardValueCalculator.calculateCardValue(cardOne,targetOneStatus,cardOneSelfNum)
        var cardTwoOpponentValue = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,cardTwoOpponentNum)


        var damage = 0.0

        if(checkTarget(CardType.ATTACK,targetOne)) {
            //공격이 self인경우 회피는 상대방 걸어준다
            if(!evasionRateCalculator.calculateEvasionSuccess(cardTwoOpponentValue)) {
                //회피 실패
                damage = cardOneSelfValue
            }
            targetOneStatus.addMaxValueCheck(cardOneSelfStatus,damage,null)
        }else{
            if(!evasionRateCalculator.calculateEvasionSuccess(cardOneSelfValue)) {
                //회피 실패
                damage = cardTwoOpponentValue
            }
            val targetTwoOpponentDamage = damageCalculator.calculateDamage(damage,0.0,targetOneStatus)
            targetOneStatus.addMaxValueCheck(cardTwoOpponentStatus,targetTwoOpponentDamage,null)
        }

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

        val cardOneSelfValue = cardValueCalculator.calculateCardValue(cardOne,targetOneStatus,cardOneSelfNum)

        val cardTwoSelfValue = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,cardTwoSelfNum)
        val cardTwoOpponentValue = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,cardTwoOpponentNum)


        if(checkTarget(CardType.ATTACK,targetOne)){
            //self가 공격인경우
            if(!evasionRateCalculator.calculateEvasionSuccess(cardTwoOpponentValue)){
                //상대가 걸어준 회피가 실패했을경우
                targetOneStatus.addMaxValueCheck(cardOneSelfStatus,cardOneSelfValue,null)
            }
        }else{
            //mutual이 공격인경우
            if(!evasionRateCalculator.calculateEvasionSuccess(cardOneSelfValue)){
                //내가 시전한 회피가 실패했을경우
                val targetTwoOpponentDamage = damageCalculator.calculateDamage(cardTwoOpponentValue,0.0,targetOneStatus)
                targetOneStatus.addMaxValueCheck(cardTwoOpponentStatus,targetTwoOpponentDamage,null)
            }

            targetTwoStatus.addMaxValueCheck(cardTwoSelfStatus,cardTwoSelfValue,null)
        }

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

        val cardOneOpponentValue = cardValueCalculator.calculateCardValue(cardOne,targetOneStatus,cardOneOpponentNum)
        val cardTwoSelfValue = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,cardTwoSelfNum)
        val cardTwoOpponentValue = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,cardTwoOpponentNum)

        if(checkTarget(CardType.ATTACK,targetOne)) {
            //공격이 상대방 타겟인경우 경우
            if (!evasionRateCalculator.calculateEvasionSuccess(cardTwoSelfValue)) {
                val targetOneOpponentDamage = damageCalculator.calculateDamage(cardOneOpponentValue, 0.0, targetTwoStatus)
                targetTwoStatus.addMaxValueCheck(cardOneOpponentStatus,targetOneOpponentDamage,null)
            }
        }else{
            //공격이 상호작용인 경우 회피가 상대방 타겟
            if(!evasionRateCalculator.calculateEvasionSuccess(cardTwoOpponentNum)){
                targetTwoStatus.addMaxValueCheck(cardTwoSelfStatus,cardTwoSelfValue,null)
            }
            val targetTwoOpponentDamage = damageCalculator.calculateDamage(cardTwoOpponentValue,0.0,targetOneStatus)
            targetOneStatus.addMaxValueCheck(cardTwoOpponentStatus,targetTwoOpponentDamage,null)
        }

    }

    override fun opponentToOpponent(targetOne: BattleStatus, targetTwo: BattleStatus){
        val attacker = distinctTarget(CardType.ATTACK,targetOne,targetTwo)
        val evader = distinctTarget(CardType.EVASION,targetOne,targetTwo)

        val attackerCard = attacker.card
        val evaderCard = evader.card

        val attackerStatus = attacker.status
        val evaderStatus = evader.status

        val attackNum = attackerCard.getEffectOpponentNumValue()
        val attackStatus = attackerCard.effectOpponentStat

        val attackCardValue = cardValueCalculator.calculateCardValue(attackerCard,attackerStatus,attackNum)

        val attackerOpponentDamage = damageCalculator.calculateDamage(attackCardValue,0.0,evaderStatus)

        evaderStatus.addMaxValueCheck(attackStatus,attackerOpponentDamage,null)

    }
    override fun mutualToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){
        val attacker = distinctTarget(CardType.ATTACK,targetOne,targetTwo)
        val evader = distinctTarget(CardType.EVASION,targetOne,targetTwo)

        val attackerCard = attacker.card
        val evaderCard = evader.card

        val attackerStatus = attacker.status
        val evaderStatus = evader.status

        val attackSelfNum = attackerCard.getEffectSelfNumValue()
        val attackSelfStatus = attackerCard.effectSelfStat

        val attackOpponentNum = attackerCard.getEffectOpponentNumValue()
        val attackOpponentStatus = attackerCard.effectOpponentStat

        val evaderSelfNum = evaderCard.getEffectSelfNumValue()
        val evaderSelfStatus = evaderCard.effectSelfStat

        val evaderOpponentNum = evaderCard.getEffectOpponentNumValue()
        val evaderOpponentStatus = evaderCard.effectOpponentStat

        val attackSelfValue = cardValueCalculator.calculateCardValue(attackerCard,attackerStatus,attackSelfNum)
        val attackOpponentValue = cardValueCalculator.calculateCardValue(attackerCard,attackerStatus,attackOpponentNum)

        val evaderSelfValue = cardValueCalculator.calculateCardValue(evaderCard,evaderStatus,evaderSelfNum)
        val evaderOpponentValue = cardValueCalculator.calculateCardValue(evaderCard,evaderStatus,evaderOpponentNum)

        var attackerEffectStatus = attackerStatus.get(attackSelfStatus)
        var evaderEffectStatus = evaderStatus.get(evaderSelfStatus)

        if(!evasionRateCalculator.calculateEvasionSuccess(evaderSelfValue)){
            //evader 회피 실패일시
            val attackerOpponentDamage = damageCalculator.calculateDamage(attackOpponentValue,0.0,evaderStatus)
            evaderStatus.addMaxValueCheck(attackOpponentStatus, attackerOpponentDamage, null)
        }

        if(!evasionRateCalculator.calculateEvasionSuccess(evaderOpponentValue)){
            //상대방의 회피 버프 실패시
            attackerStatus.addMaxValueCheck(attackSelfStatus, attackSelfValue, null)
        }

    }

}



