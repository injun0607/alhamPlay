package kr.alham.playground.pattern.cardeffect

import kr.alham.playground.domain.battle.MonsterBattleState
import kr.alham.playground.domain.card.Card
import kr.alham.playground.domain.card.PlayerCard
import kr.alham.playground.domain.common.TargetElementStatus
import kr.alham.playground.domain.common.TargetElementStatusMap
import kr.alham.playground.domain.enums.BattlePhase
import kr.alham.playground.domain.enums.CardTarget
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class AttackCardEffectTestDTO{


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
        var cardDTOBash = PlayerCard(
            name = "강타",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "상대방에게 5의 피해를 줍니다.",
            cost = 2,
            cardTarget = CardTarget.OPPONENT,
            effectOpponentNum = 5.0,
            effectOpponentStat = TargetElementStatus.HP,
        )

        val selfStatus = TargetElementStatusMap()
        val opponentStatus = TargetElementStatusMap()

        // Act
        attackCardEffect.applyEffect(cardDTOBash, selfStatus, opponentStatus)

        // Assert
        assertEquals(opponentStatus.get(TargetElementStatus.HP), 5.0)
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