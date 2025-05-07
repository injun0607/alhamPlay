package kr.alham.playground.pattern.cardeffect

import kr.alham.playground.domain.card.Card
import kr.alham.playground.domain.common.TargetElementStatusMap
import kr.alham.playground.domain.enums.CardType
import org.springframework.stereotype.Component

@Component
class BuffCardEffect : TargetBasedCardEffect(){
    override fun supportedType(): CardType = CardType.BUFF

    override fun applyEffectToSelf(card: Card, selfStatus: TargetElementStatusMap) {
        val effectedStat = card.effectSelfStat
        val buffNum = card.effectSelfNum
        val afterEffectStat = selfStatus.get(effectedStat) + buffNum
        selfStatus.set(effectedStat, afterEffectStat)
    }

    override fun applyEffectToOpponent(card: Card, opponentStatus: TargetElementStatusMap) {
        super.applyEffectToOpponent(card, opponentStatus)
    }

    override fun applyEffectToMutual(card: Card, selfStatus: TargetElementStatusMap, opponentStatus: TargetElementStatusMap) {

        val effectSelfStat = card.effectSelfStat
        val effectBuffNum = card.effectSelfNum
        val afterEffectSelfStat = selfStatus.get(effectSelfStat) + effectBuffNum
        selfStatus.set(effectSelfStat, afterEffectSelfStat)

        val effectOpponentStat = card.effectOpponentStat
        val effectOpponentBuffNum = card.effectOpponentNum
        val afterEffectOpponentStat = opponentStatus.get(effectOpponentStat) + effectOpponentBuffNum
        opponentStatus.set(effectOpponentStat, afterEffectOpponentStat)

    }

}