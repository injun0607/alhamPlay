package kr.alham.playground.pattern.cardeffect

import kr.alham.playground.domain.battle.BattleState
import kr.alham.playground.domain.card.CardDTO
import kr.alham.playground.domain.enums.CardType
import org.springframework.stereotype.Component

@Component
class BuffCardEffect : TargetBasedCardEffect(){
    override fun supportedType(): CardType = CardType.BUFF

    override fun applyEffectToSelf(cardDTO: CardDTO, battleState: BattleState) {
        val effectedStat = cardDTO.effectSelfStat
        val buffNum = cardDTO.effectSelfNum
        val afterEffectStat = battleState.selfTargetStatus.get(effectedStat) + buffNum
        battleState.selfTargetStatus.set(effectedStat, afterEffectStat)
    }

    override fun applyEffectToOpponent(cardDTO: CardDTO, battleState: BattleState) {
        super.applyEffectToOpponent(cardDTO, battleState)
    }

    override fun applyEffectToMutual(cardDTO: CardDTO, battleState: BattleState) {

        val effectSelfStat = cardDTO.effectSelfStat
        val effectBuffNum = cardDTO.effectSelfNum
        val afterEffectSelfStat = battleState.selfTargetStatus.get(effectSelfStat) + effectBuffNum
        battleState.selfTargetStatus.set(effectSelfStat, afterEffectSelfStat)

        val effectOpponentStat = cardDTO.effectOpponentStat
        val effectOpponentBuffNum = cardDTO.effectOpponentNum
        val afterEffectOpponentStat = battleState.opponentTargetStatus.get(effectOpponentStat) + effectOpponentBuffNum
        battleState.opponentTargetStatus.set(effectOpponentStat, afterEffectOpponentStat)

    }

}