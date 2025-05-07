package kr.alham.playground.pattern.cardeffect

import kr.alham.playground.domain.battle.BattleState
import kr.alham.playground.domain.battle.MonsterBattleState
import kr.alham.playground.domain.card.PlayerCard
import kr.alham.playground.domain.common.TargetElementStatus
import kr.alham.playground.domain.common.TargetElementStatusMap
import kr.alham.playground.domain.enums.BattlePhase
import kr.alham.playground.domain.enums.CardTarget
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class BuffCardEffectTestDTO{



    private lateinit var buffCardEffect: BuffCardEffect
    private lateinit var buffPlayerCard: PlayerCard
    private lateinit var buffMutualCard: PlayerCard



    @BeforeEach
    fun setUp() {
        buffCardEffect = BuffCardEffect()


        buffPlayerCard = PlayerCard(
            name = "집중",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "자신에게 민첩 +1",
            cost = 2,
            cardTarget = CardTarget.SELF,
            effectSelfNum = 1.0,
            effectSelfStat = TargetElementStatus.DEX,
        )

        buffMutualCard = PlayerCard(
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
        val selfStatus = TargetElementStatusMap()
        val opponentStatus = TargetElementStatusMap()


        buffCardEffect.applyEffect(buffPlayerCard, selfStatus,opponentStatus)
        assertEquals(selfStatus.get(TargetElementStatus.DEX), 2.0)
    }

    @Test
    fun `mutualBuffCardTest`(){

        val selfStatus = TargetElementStatusMap()
        val opponentStatus = TargetElementStatusMap()

        buffCardEffect.applyEffect(buffMutualCard, selfStatus, opponentStatus)
        assertEquals(selfStatus.get(TargetElementStatus.DEX), 4.0)
        assertEquals(opponentStatus.get(TargetElementStatus.DEX), 2.0)
    }







}