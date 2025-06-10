package kr.alham.playground.system.cardeffect

import kr.alham.playground.domain.battle.BattleStatus
import kr.alham.playground.domain.enums.CardTarget
import kr.alham.playground.domain.enums.CardType
import kr.alham.playground.system.calculator.CardValueCalculator
import kr.alham.playground.system.calculator.DamageCalculator
import org.springframework.stereotype.Component

/**
 * 추후에 등록하자
 */

@Component
class AloneCardEffect(
    private val damageCalculator: DamageCalculator,
    private val cardValueCalculator: CardValueCalculator
):CardEffectStrategy {
    override fun supportedType(): Pair<CardType, CardType>? = Pair(CardType.NONE, CardType.NONE)

    override fun applyEffect(targetOne: BattleStatus, targetTwo: BattleStatus) {
        val sourceTarget = if(targetOne.card.cardType != CardType.NONE) targetOne else targetTwo
        val affectedTarget = if(targetOne.card.cardType == CardType.NONE) targetOne else targetTwo

        val sourceTargetCard = sourceTarget.card

        val sourceTargetStatus = sourceTarget.status
        val affectedTargetStatus = affectedTarget.status

        //self
        when(sourceTargetCard.cardTarget){
            CardTarget.SELF ->{
                //자신에게 공격시에는 데미지 감소식 적용 X
                val effectValue = cardValueCalculator.calculateCardValue(sourceTargetCard,sourceTargetStatus,sourceTargetCard.effectSelfNum)
                val sourceTargetEffectStat = sourceTargetStatus.get(sourceTargetCard.effectSelfStat) - effectValue
                sourceTargetStatus.set(sourceTargetCard.effectSelfStat, sourceTargetEffectStat)
            }
            CardTarget.OPPONENT -> {
                val effectValue = cardValueCalculator.calculateCardValue(sourceTargetCard, sourceTargetStatus, sourceTargetCard.effectOpponentNum)
                if(sourceTargetCard.cardType == CardType.ATTACK){
                    val damage = damageCalculator.calculateDamage(effectValue,0.0, affectedTargetStatus)
                    affectedTargetStatus.set(sourceTargetCard.effectOpponentStat,(affectedTargetStatus.get(sourceTargetCard.effectOpponentStat) - damage))
                }else{
                    affectedTargetStatus.set(sourceTargetCard.effectOpponentStat, (affectedTargetStatus.get(sourceTargetCard.effectOpponentStat) + effectValue))
                }
            }
            CardTarget.MUTUAL -> {
                val effectSelfValue = cardValueCalculator.calculateCardValue(sourceTargetCard, sourceTargetStatus, sourceTargetCard.effectSelfNum)
                val effectOpponentValue = cardValueCalculator.calculateCardValue(sourceTargetCard, sourceTargetStatus, sourceTargetCard.effectOpponentNum)

                if(sourceTargetCard.cardType == CardType.ATTACK){
                    val damage = damageCalculator.calculateDamage(effectOpponentValue, 0.0, affectedTargetStatus)
                    affectedTargetStatus.set(sourceTargetCard.effectSelfStat, (affectedTargetStatus.get(sourceTargetCard.effectSelfStat) - effectSelfValue))
                    affectedTargetStatus.set(sourceTargetCard.effectOpponentStat, (affectedTargetStatus.get(sourceTargetCard.effectOpponentStat) - damage))
                }else{
                    affectedTargetStatus.set(sourceTargetCard.effectSelfStat, (affectedTargetStatus.get(sourceTargetCard.effectSelfStat) + effectSelfValue))
                    affectedTargetStatus.set(sourceTargetCard.effectOpponentStat, (affectedTargetStatus.get(sourceTargetCard.effectOpponentStat) + effectOpponentValue))
                }
            }

        }

    }
}