package kr.alham.playground.pattern.cardeffect

import kr.alham.playground.domain.battle.BattleState
import kr.alham.playground.domain.card.Card
import kr.alham.playground.domain.enums.CardType
import org.springframework.stereotype.Component

@Component
class BuffCardEffect : TargetBasedCardEffect(){
    override fun supportedType(): CardType = CardType.BUFF

    override fun applyEffectToSelf(card: Card, battleState: BattleState) {
        val effectedStat = card.effectSelfStat
        val buffNum = card.effectSelfNum
        val afterEffectStat = battleState.selfTargetStatus.get(effectedStat) + buffNum
        battleState.selfTargetStatus.set(effectedStat, afterEffectStat)
    }

    override fun applyEffectToOpponent(card: Card, battleState: BattleState) {
        super.applyEffectToOpponent(card, battleState)
    }

    override fun applyEffectToMutual(card: Card, battleState: BattleState) {

        val effectSelfStat = card.effectSelfStat
        val effectBuffNum = card.effectSelfNum
        val afterEffectSelfStat = battleState.selfTargetStatus.get(effectSelfStat) + effectBuffNum
        battleState.selfTargetStatus.set(effectSelfStat, afterEffectSelfStat)

        val effectOpponentStat = card.effectOpponentStat
        val effectOpponentBuffNum = card.effectOpponentNum
        val afterEffectOpponentStat = battleState.opponentTargetStatus.get(effectOpponentStat) + effectOpponentBuffNum
        battleState.opponentTargetStatus.set(effectOpponentStat, afterEffectOpponentStat)

    }

}