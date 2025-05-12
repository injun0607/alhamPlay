package kr.alham.playground.pattern.cardeffect

import kr.alham.playground.domain.battle.BattleStatus
import kr.alham.playground.domain.enums.CardTarget
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

        val cardOneSelfDamage = cardOne.calculateValue(targetOneStatus,targetOneSelfNum)
        val cardTwoSelfDamage = cardTwo.calculateValue(targetTwoStatus,targetTwoSelfNum)
        val cardTwoOpponentDamage = cardTwo.calculateValue(targetTwoStatus,targetTwoOpponentNum)

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
        val cardOneOpponentDamage = cardOne.calculateValue(targetOneStatus,targetOneOpponentNum)
        val cardTwoSelfDamage = cardTwo.calculateValue(targetTwoStatus,targetTwoSelfNum)
        val cardTwoOpponentDamage = cardTwo.calculateValue(targetTwoStatus,targetTwoOpponentNum)

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
        val cardOneOpponentDamage = cardOne.calculateValue(targetOneStatus,targetOneOpponentNum)
        val cardTwoOpponentDamage = cardTwo.calculateValue(targetTwoStatus,targetTwoOpponentNum)

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
        val cardOneSelfDamage = cardOne.calculateValue(targetOneStatus,targetOneSelfNum)
        val cardOneOpponentDamage = cardOne.calculateValue(targetOneStatus,targetOneOpponentNum)

        val cardTwoSelfDamage = cardTwo.calculateValue(targetTwoStatus,targetTwoSelfNum)
        val cardTwoOpponentDamage = cardTwo.calculateValue(targetTwoStatus,targetTwoOpponentNum)

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


        //self가 공격인경우
        if(checkTarget(CardType.ATTACK,targetOne)){
            //방어 계산


        }else{
            //self가 방어인 경우
            val damage = if(cardTwoOpponentValue > cardOneSelfValue) {
                cardTwoOpponentValue - cardOneSelfValue
            }else{
                0.0
            }


        }








    }
    override fun selfToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){

    }
    override fun opponentToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){

    }
    override fun opponentToOpponent(targetOne: BattleStatus, targetTwo: BattleStatus){

    }
    override fun mutualToMutual(targetOne: BattleStatus, targetTwo: BattleStatus){

    }

    private fun distinctTarget(cardType: CardType,targetOne: BattleStatus, targetTwo: BattleStatus): BattleStatus {
        if(targetOne.card.cardType == cardType) {
            return targetOne
        }else{
            return targetTwo
        }
    }

    private fun checkTarget(cardType: CardType, target: BattleStatus): Boolean {
        return target.card.cardType == cardType
    }


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



