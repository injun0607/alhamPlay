package kr.alham.playground.pattern.cardeffect

import kr.alham.playground.domain.battle.BattleState
import kr.alham.playground.dto.card.CardDTO
import kr.alham.playground.domain.enums.CardType
import org.springframework.stereotype.Component

/**
 * 공격 카드 효과
 * 1.
 * 2.
 * 3.
 */
@Component
class AttackCardEffect() :TargetBasedCardEffect(){
    override fun supportedType(): CardType = CardType.ATTACK
    override fun applyEffectToSelf(cardDTO: CardDTO, battleState: BattleState) {
        println("applyEffectToSelf")
    }

    override fun applyEffectToOpponent(cardDTO: CardDTO, battleState: BattleState) {
        val effectedStat = cardDTO.effectOpponentStat
        val damage = cardDTO.effectOpponentNum

        val afterEffectStat = battleState.engagementMonsterBattleStatus.get(effectedStat) - damage
        battleState.opponentTargetStatus.set(effectedStat, afterEffectStat)
    }

    override fun applyEffectToMutual(cardDTO: CardDTO, battleState: BattleState) {
        println("applyEffectToMutual")
    }
}