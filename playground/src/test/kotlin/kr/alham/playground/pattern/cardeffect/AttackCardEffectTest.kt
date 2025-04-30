package kr.alham.playground.pattern.cardeffect

import kr.alham.playground.domain.battle.MonsterBattleState
import kr.alham.playground.domain.card.Card
import kr.alham.playground.domain.common.TargetElementStatus
import kr.alham.playground.domain.common.TargetElementStatusMap
import kr.alham.playground.domain.enums.BattlePhase
import kr.alham.playground.domain.enums.CardTarget
import kr.alham.playground.domain.enums.CardType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class AttackCardEffectTest{


//    private lateinit var attackCardEffect: AttackCardEffect
//    private lateinit var battleState: BattleState
//    private lateinit var card: Card

//    @BeforeEach
//    fun setUp() {
//        attackCardEffect = AttackCardEffect()
//    }

    @Test
    fun `applyEffectToOpponent should reduce opponent's stat correctly`() {

        val attackCardEffect = AttackCardEffect()

        // Arrange
        var cardBash = Card(
            name = "강타",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "상대방에게 5의 피해를 줍니다.",
            cost = 2,
            cardTarget = CardTarget.OPPONENT,
            effectOpponentNum = 5.0,
            effectOpponentStat = TargetElementStatus.HP,
        )

        var battleState = MonsterBattleState(
            selfTargetStatus = TargetElementStatusMap(),
            opponentTargetStatus = TargetElementStatusMap()
        )

        // Act
        attackCardEffect.applyEffect(cardBash, battleState)

        // Assert
        assertEquals(battleState.opponentTargetStatus.get(TargetElementStatus.HP), 5.0)
    }

//    @Test
//    fun `supportedType should return ATTACK`() {
//        // Act
//        val result = attackCardEffect.supportedType()
//
//        // Assert
//        assertEquals(CardType.ATTACK, result)
//    }
}