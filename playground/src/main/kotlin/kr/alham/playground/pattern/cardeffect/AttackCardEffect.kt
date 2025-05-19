package kr.alham.playground.pattern.cardeffect

import kr.alham.playground.domain.battle.BattleStatus
import kr.alham.playground.domain.enums.CardType
import kr.alham.playground.pattern.calculator.CardValueCalculator
import kr.alham.playground.pattern.calculator.DamageCalculator
import kr.alham.playground.pattern.calculator.EvasionRateCalculator
import org.springframework.stereotype.Component

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

        val cardOneSelfNum = cardOne.effectSelfNum
        val cardOneSelfStatus = cardOne.effectSelfStat

        val cardTwoSelfNum = cardTwo.effectSelfNum
        val cardTwoSelfStatus = cardTwo.effectSelfStat

        val cardOneSelfDamage = cardValueCalculator.calculateCardValue(cardOne,targetOneStatus,cardOneSelfNum)
        val cardTwoSelfDamage = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,cardTwoSelfNum)

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

        val cardOneSelfDamage = cardValueCalculator.calculateCardValue(cardOne,targetOneStatus,cardOneSelfNum)
        val cardTwoOpponentDamage = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,cardTwoOpponentNum)

        var targetOneAfterEffectStat = targetOneStatus.get(cardOneSelfStatus) - cardOneSelfDamage
        targetOneStatus.set(cardOneSelfStatus,targetOneAfterEffectStat)

        targetOneAfterEffectStat = targetOneStatus.get(cardTwoOpponentStatus) - damageCalculator.calculateDamage(cardTwoOpponentDamage,0.0,targetOneStatus)
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

        val cardOneSelfDamage = cardValueCalculator.calculateCardValue(cardOne,targetOneStatus,targetOneSelfNum)
        val cardTwoSelfDamage = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,targetTwoSelfNum)
        val cardTwoOpponentDamage = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,targetTwoOpponentNum)

        var targetOneAfterEffectStat = targetOneStatus.get(targetOneSelfStatus) - cardOneSelfDamage
        targetOneStatus.set(targetOneSelfStatus,targetOneAfterEffectStat)

        targetOneAfterEffectStat = targetOneStatus.get(targetTwoOpponentStatus) - damageCalculator.calculateDamage(cardTwoOpponentDamage,0.0,targetOneStatus)
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
        val cardOneOpponentDamage = cardValueCalculator.calculateCardValue(cardOne,targetOneStatus,targetOneOpponentNum)
        val cardTwoSelfDamage = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,targetTwoSelfNum)
        val cardTwoOpponentDamage = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,targetTwoOpponentNum)

        val targetOneEffectStat = targetOneStatus.get(targetTwoOpponentStatus) - damageCalculator.calculateDamage(cardTwoOpponentDamage,0.0,targetOneStatus)
        targetOneStatus.set(targetTwoOpponentStatus,targetOneEffectStat)

        var targetTwoEffectStat = targetTwoStatus.get(targetOneOpponentStatus) - damageCalculator.calculateDamage(cardOneOpponentDamage,0.0,targetTwoStatus)
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
        val cardOneOpponentDamage = cardValueCalculator.calculateCardValue(cardOne,targetOneStatus,targetOneOpponentNum)
        val cardTwoOpponentDamage = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,targetTwoOpponentNum)

        val targetOneEffectStat = targetOneStatus.get(targetOneOpponentStatus) - damageCalculator.calculateDamage(cardTwoOpponentDamage,0.0,targetOneStatus)
        targetOneStatus.set(targetTwoOpponentStatus,targetOneEffectStat)

        val targetTwoEffectStat = targetTwoStatus.get(targetTwoOpponentStatus) - damageCalculator.calculateDamage(cardOneOpponentDamage,0.0,targetTwoStatus)
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
        val cardOneSelfDamage = cardValueCalculator.calculateCardValue(cardOne,targetOneStatus,targetOneSelfNum)
        val cardOneOpponentDamage = cardValueCalculator.calculateCardValue(cardOne,targetOneStatus,targetOneOpponentNum)

        val cardTwoSelfDamage = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,targetTwoSelfNum)
        val cardTwoOpponentDamage = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,targetTwoOpponentNum)

        var targetOneEffectStat = targetOneStatus.get(targetOneSelfStatus) - cardOneSelfDamage
        targetOneStatus.set(targetOneSelfStatus,targetOneEffectStat)
        targetOneEffectStat = targetOneStatus.get(targetTwoOpponentStatus) - damageCalculator.calculateDamage(cardTwoOpponentDamage,0.0,targetOneStatus)
        targetOneStatus.set(targetTwoOpponentStatus,targetOneEffectStat)

        var targetTwoEffectStat = targetTwoStatus.get(targetTwoSelfStatus) - cardTwoSelfDamage
        targetTwoStatus.set(targetTwoSelfStatus,targetTwoEffectStat)
        targetTwoEffectStat = targetTwoStatus.get(targetOneOpponentStatus) - damageCalculator.calculateDamage(cardOneOpponentDamage,0.0,targetTwoStatus)
        targetTwoStatus.set(targetOneOpponentStatus,targetTwoEffectStat)

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

        val attackCardSelfNum = attackCard.effectSelfNum
        val attackCardSelfStatus = attackCard.effectSelfStat

        val attackCardDamage = cardValueCalculator.calculateCardValue(attackCard,attackerStatus,attackCardSelfNum)

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

        val cardOneSelfValue = cardValueCalculator.calculateCardValue(cardOne,targetOneStatus,cardOneSelfNum)
        val cardTwoOpponentValue = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,cardTwoOpponentNum)

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
            damage = damageCalculator.calculateDamage(cardTwoOpponentValue,cardOneSelfValue,targetOneStatus)
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

        val cardOneSelfValue = cardValueCalculator.calculateCardValue(cardOne,targetOneStatus,cardOneSelfNum)

        val cardTwoSelfValue = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,cardTwoSelfNum)
        val cardTwoOpponentValue = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,cardTwoOpponentNum)

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
            damage = damageCalculator.calculateDamage(cardTwoOpponentValue,cardOneSelfValue,targetOneStatus)
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

        val cardOneOpponentValue = cardValueCalculator.calculateCardValue(cardOne,targetOneStatus,cardOneOpponentNum)

        val cardTwoSelfValue = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,cardTwoSelfNum)
        val cardTwoOpponentValue = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,cardTwoOpponentNum)

        var damage = 0.0

        var targetOneEffectStat = targetOneStatus.get(cardTwoOpponentStatus)
        var targetTwoEffectStat = targetTwoStatus.get(cardOneOpponentStatus)

        if(checkTarget(CardType.ATTACK,targetOne)) {
            //공격이 상대방 타겟인경우 경우
            damage = damageCalculator.calculateDamage(cardOneOpponentValue,cardTwoSelfValue,targetTwoStatus)
            targetTwoEffectStat -= damage
        }else{
            //방어가 상대방 타겟인경우
            if(cardTwoSelfValue > cardOneOpponentValue){
                damage = cardTwoSelfValue - cardOneOpponentValue
                targetOneEffectStat -= damage
            }
            targetOneEffectStat -= damageCalculator.calculateDamage(cardTwoOpponentValue,0.0,targetOneStatus)
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

        val attackCardValue = cardValueCalculator.calculateCardValue(attackerCard,attackerStatus,attackNum)

        val defenderEffectStatus = defenderStatus.get(attackStatus) - damageCalculator.calculateDamage(attackCardValue,0.0,defenderStatus)

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

        val cardOneSelfValue = cardValueCalculator.calculateCardValue(cardOne,targetOneStatus,cardOneSelfNum)
        val cardOneOpponentValue = cardValueCalculator.calculateCardValue(cardOne,targetOneStatus,cardOneOpponentNum)

        val cardTwoSelfValue = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,cardTwoSelfNum)
        val cardTwoOpponentValue = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,cardTwoOpponentNum)

        var targetOneEffectStatus = targetOneStatus.get(cardOneSelfStatus)
        var targetTwoEffectStatus = targetTwoStatus.get(cardTwoSelfStatus)

        if(checkTarget(CardType.ATTACK,targetOne)) {
            //targetOne이 attackCard인것
            if(cardOneSelfValue > cardTwoOpponentValue){
                //cardTwo가 cardOne데미지를 방어
                val damage = cardOneSelfValue - cardTwoOpponentValue
                targetOneEffectStatus -= damage
            }
            val effectedTargetTwoDamage = damageCalculator.calculateDamage(cardOneOpponentValue,cardTwoSelfValue,targetTwoStatus)
            targetTwoEffectStatus -= effectedTargetTwoDamage
        }else{
            //targetOne이 defenceCard인것
            val effectedTargetOneDamage = damageCalculator.calculateDamage(cardTwoOpponentNum,cardOneSelfValue,targetOneStatus)
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

        val attackNum = attackCard.effectSelfNum
        val attackStatusType = attackCard.effectSelfStat

        val attackCardDamage = cardValueCalculator.calculateCardValue(attackCard,attackStatus,attackNum)
        val attackCardEffectStat = attackStatus.get(attackStatusType) - attackCardDamage

        attackStatus.set(attackStatusType,attackCardEffectStat)

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

        var cardOneSelfValue = cardValueCalculator.calculateCardValue(cardOne,targetOneStatus,cardOneSelfNum)
        var cardTwoOpponentValue = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,cardTwoOpponentNum)

        var targetOneEffectStatus = targetOneStatus.get(cardOneSelfStatus)

        var damage = 0.0

        if(checkTarget(CardType.ATTACK,targetOne)) {
            //공격이 self인경우 회피는 상대방 걸어준다
            if(!evasionRateCalculator.calculateEvasionSuccess(cardTwoOpponentValue)) {
                //회피 실패
                damage = cardOneSelfValue
            }
            targetOneEffectStatus -= damage
        }else{
            if(!evasionRateCalculator.calculateEvasionSuccess(cardOneSelfValue)) {
                //회피 실패
                damage = cardTwoOpponentValue
            }
            targetOneEffectStatus -= damageCalculator.calculateDamage(damage,0.0,targetOneStatus)
        }

        targetOneStatus.set(cardOneSelfStatus,targetOneEffectStatus)

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

        val cardOneSelfValue = cardValueCalculator.calculateCardValue(cardOne,targetOneStatus,cardOneSelfNum)

        val cardTwoSelfValue = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,cardTwoSelfNum)
        val cardTwoOpponentValue = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,cardTwoOpponentNum)

        var targetOneEffectStatus = targetOneStatus.get(cardOneSelfStatus)
        var targetTwoEffectStatus = targetTwoStatus.get(cardTwoSelfStatus)

        if(checkTarget(CardType.ATTACK,targetOne)){
            //self가 공격인경우
            if(!evasionRateCalculator.calculateEvasionSuccess(cardTwoOpponentValue)){
                //상대가 걸어준 회피가 실패했을경우
                targetOneEffectStatus -= damageCalculator.calculateDamage(cardOneSelfValue,0.0,targetOneStatus)
            }
        }else{
            //mutual이 공격인경우
            if(!evasionRateCalculator.calculateEvasionSuccess(cardOneSelfValue)){
                //내가 시전한 회피가 실패했을경우
                targetOneEffectStatus -= damageCalculator.calculateDamage(cardTwoOpponentValue,0.0,targetOneStatus)
            }

            targetTwoEffectStatus -= cardTwoSelfValue
        }

        targetOneStatus.set(cardOneSelfStatus,targetOneEffectStatus)
        targetTwoStatus.set(cardTwoSelfStatus,targetTwoEffectStatus)
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

        val cardOneOpponentValue = cardValueCalculator.calculateCardValue(cardOne,targetOneStatus,cardOneOpponentNum)
        val cardTwoSelfValue = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,cardTwoSelfNum)
        val cardTwoOpponentValue = cardValueCalculator.calculateCardValue(cardTwo,targetTwoStatus,cardTwoOpponentNum)

        var targetOneEffectStat = targetOneStatus.get(cardTwoOpponentStatus)
        var targetTwoEffectStat = targetTwoStatus.get(cardOneOpponentStatus)

        if(checkTarget(CardType.ATTACK,targetOne)) {
            //공격이 상대방 타겟인경우 경우
            if (!evasionRateCalculator.calculateEvasionSuccess(cardTwoSelfValue)) {
                targetTwoEffectStat -= damageCalculator.calculateDamage(cardOneOpponentValue, 0.0, targetTwoStatus)
            }
        }else{
            //공격이 상호작용인 경우 회피가 상대방 타겟
            if(!evasionRateCalculator.calculateEvasionSuccess(cardTwoOpponentNum)){
                targetTwoEffectStat -= cardTwoSelfValue
            }
            targetOneEffectStat -= damageCalculator.calculateDamage(cardTwoOpponentValue,0.0,targetOneStatus)
        }

        targetOneStatus.set(cardTwoOpponentStatus,targetOneEffectStat)
        targetTwoStatus.set(cardOneOpponentStatus,targetTwoEffectStat)

    }
    override fun opponentToOpponent(targetOne: BattleStatus, targetTwo: BattleStatus){
        val attacker = distinctTarget(CardType.ATTACK,targetOne,targetTwo)
        val evader = distinctTarget(CardType.EVASION,targetOne,targetTwo)

        val attackerCard = attacker.card
        val evaderCard = evader.card

        val attackerStatus = attacker.status
        val evaderStatus = evader.status

        val attackNum = attackerCard.effectOpponentNum
        val attackStatus = attackerCard.effectOpponentStat

        val attackCardValue = cardValueCalculator.calculateCardValue(attackerCard,attackerStatus,attackNum)

        val evaderEffectStatus = evaderStatus.get(attackStatus) - damageCalculator.calculateDamage(attackCardValue,0.0,evaderStatus)

        evader.status.set(attackStatus,evaderEffectStatus)
    }
    override fun mutualToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){
        val attacker = distinctTarget(CardType.ATTACK,targetOne,targetTwo)
        val evader = distinctTarget(CardType.EVASION,targetOne,targetTwo)

        val attackerCard = attacker.card
        val evaderCard = evader.card

        val attackerStatus = attacker.status
        val evaderStatus = evader.status

        val attackSelfNum = attackerCard.effectSelfNum
        val attackSelfStatus = attackerCard.effectSelfStat

        val attackOpponentNum = attackerCard.effectOpponentNum
        val attackOpponentStatus = attackerCard.effectOpponentStat

        val evaderSelfNum = evaderCard.effectSelfNum
        val evaderSelfStatus = evaderCard.effectSelfStat

        val evaderOpponentNum = evaderCard.effectOpponentNum
        val evaderOpponentStatus = evaderCard.effectOpponentStat

        val attackSelfValue = cardValueCalculator.calculateCardValue(attackerCard,attackerStatus,attackSelfNum)
        val attackOpponentValue = cardValueCalculator.calculateCardValue(attackerCard,attackerStatus,attackOpponentNum)

        val evaderSelfValue = cardValueCalculator.calculateCardValue(evaderCard,evaderStatus,evaderSelfNum)
        val evaderOpponentValue = cardValueCalculator.calculateCardValue(evaderCard,evaderStatus,evaderOpponentNum)

        var attackerEffectStatus = attackerStatus.get(attackSelfStatus)
        var evaderEffectStatus = evaderStatus.get(evaderSelfStatus)

        if(!evasionRateCalculator.calculateEvasionSuccess(evaderSelfValue)){
            //evader 회피 실패일시
            evaderEffectStatus -= damageCalculator.calculateDamage(attackOpponentValue,0.0,evaderStatus)
        }

        if(!evasionRateCalculator.calculateEvasionSuccess(evaderOpponentValue)){
            //상대방의 회피 버프 실패시
            attackerEffectStatus -= attackSelfValue
        }

        attackerStatus.set(attackSelfStatus,attackerEffectStatus)
        evaderStatus.set(evaderSelfStatus,evaderEffectStatus)

    }

}



