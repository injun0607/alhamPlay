package kr.alham.playground.pattern.cardeffect

import kr.alham.playground.domain.battle.BattleState
import kr.alham.playground.domain.battle.MonsterBattleState
import kr.alham.playground.domain.card.Card
import kr.alham.playground.domain.common.TargetElementStatus
import kr.alham.playground.domain.common.TargetElementStatusMap
import kr.alham.playground.domain.enums.BattlePhase
import kr.alham.playground.domain.enums.CardTarget
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class BuffCardEffectTest{



    private lateinit var buffCardEffect: BuffCardEffect
    private lateinit var battleState: BattleState
    private lateinit var buffCardSelf: Card
    private lateinit var buffCardMutual: Card

    @BeforeEach
    fun setUp() {
        buffCardEffect = BuffCardEffect()
        battleState = MonsterBattleState(
            selfTargetStatus = TargetElementStatusMap(),
            opponentTargetStatus = TargetElementStatusMap()
        )

        buffCardSelf = Card(
            name = "집중",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "자신에게 민첩 +1",
            cost = 2,
            cardTarget = CardTarget.SELF,
            effectSelfNum = 1.0,
            effectSelfStat = TargetElementStatus.DEX,
        )

        buffCardMutual = Card(
            name = "집중",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "자신에게 민첩 + 3 , 상대에게 민첩 + 1",
            cost = 2,
            cardTarget = CardTarget.MUTUAL,
            effectSelfNum = 3.0,
            effectSelfStat = TargetElementStatus.DEX,
            effectOpponentStat = TargetElementStatus.DEX,
            effectOpponentNum = 1.0,
        )


    }


    @Test
    fun `selfBuffCardTest`() {
        buffCardEffect.applyEffect(buffCardSelf, battleState)
        assertEquals(battleState.selfTargetStatus.get(TargetElementStatus.DEX), 2.0)
    }

    @Test
    fun `mutualBuffCardTest`(){
        buffCardEffect.applyEffect(buffCardMutual, battleState)
        assertEquals(battleState.selfTargetStatus.get(TargetElementStatus.DEX), 4.0)
        assertEquals(battleState.opponentTargetStatus.get(TargetElementStatus.DEX), 2.0)

    }







}