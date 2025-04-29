package kr.alham.playground.pattern.cardeffect

import kr.alham.playground.domain.battle.BattleState
import kr.alham.playground.domain.card.Card
import kr.alham.playground.domain.common.TargetElement

interface CardEffectStrategy {
    fun applyEffect(card: Card,battleState: BattleState): BattleState
}

