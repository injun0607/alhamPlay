package kr.alham.playground.pattern.cardeffect

import kr.alham.playground.domain.battle.BattleStatus
import kr.alham.playground.domain.card.Card
import kr.alham.playground.domain.card.MonsterCard
import kr.alham.playground.domain.card.PlayerCard
import kr.alham.playground.domain.common.TargetElementStatus
import kr.alham.playground.domain.enums.BattlePhase
import kr.alham.playground.domain.enums.CardTarget
import kr.alham.playground.domain.enums.CardType
import kr.alham.playground.domain.monster.Monster
import kr.alham.playground.domain.player.Player
import kr.alham.playground.pattern.calculator.CardValueCalculator
import kr.alham.playground.pattern.calculator.DamageCalculator
import kr.alham.playground.pattern.calculator.DefaultCardValueCalculator
import kr.alham.playground.pattern.calculator.EvasionRateCalculator
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class DefaultCardEffectTest{

    lateinit var cardValueCalculator: CardValueCalculator
    lateinit var defaultCardEffect: DefaultCardEffect
    lateinit var playerCardBuffSelf: PlayerCard
    lateinit var playerCardBuffOpponent: PlayerCard
    lateinit var playerCardBuffMutual: PlayerCard
    lateinit var monsterHealSelf: MonsterCard
    lateinit var monsterHealOpponent: MonsterCard
    lateinit var monsterHealMutual: MonsterCard
    lateinit var playerCardDebuffSelf: PlayerCard
    lateinit var playerCardDebuffOpponent: PlayerCard
    lateinit var playerCardDebuffMutual: PlayerCard
    lateinit var monsterCardBuffSelf: MonsterCard
    lateinit var monsterCardBuffOpponent: MonsterCard
    lateinit var monsterCardBuffMutual: MonsterCard

    lateinit var player: Player
    lateinit var monster: Monster

    @BeforeEach
    fun setUp(){
        cardValueCalculator = DefaultCardValueCalculator()
        defaultCardEffect = DefaultCardEffect(cardValueCalculator)
        playerCardBuffSelf = PlayerCard(
            1L,
            name="민첩 강화",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "자신의 민첩을 1 강화한다.",
            cost = 2,
            cardTarget = CardTarget.SELF,
            cardType = CardType.BUFF,
            effectSelfNum = 1.0,
            effectSelfStat = TargetElementStatus.DEX,
        )

        playerCardBuffOpponent = PlayerCard(
            2L,
            name="상대 민첩 강화",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "상대방의 민첩을 1 강화한다.",
            cost = 2,
            cardTarget = CardTarget.OPPONENT,
            cardType = CardType.BUFF,
            effectSelfNum = 1.0,
            effectSelfStat = TargetElementStatus.DEX,
        )

        playerCardBuffMutual = PlayerCard(
            3L,
            name="상호작용 민첩 강화",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "자신의 민첩을 2 강화 ,상대방의 민첩을 1 강화한다.",
            cost = 2,
            cardTarget = CardTarget.MUTUAL,
            cardType = CardType.BUFF,
            effectSelfNum = 2.0,
            effectSelfStat = TargetElementStatus.DEX,
            effectOpponentNum = 1.0,
            effectOpponentStat = TargetElementStatus.DEX,
        )

        monsterHealSelf = MonsterCard(
            4L,
            name="몬스터 자신 회복",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "자신의 체력을 10 회복한다.",
            cost = 2,
            cardTarget = CardTarget.SELF,
            cardType = CardType.HEAL,
            effectSelfNum = 10.0,
            effectSelfStat = TargetElementStatus.HP,
        )

        monsterHealOpponent = MonsterCard(
            5L,
            name="몬스터 상대 회복",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "상대방의 체력을 10 회복한다.",
            cost = 2,
            cardTarget = CardTarget.OPPONENT,
            cardType = CardType.HEAL,
            effectSelfNum = 10.0,
            effectSelfStat = TargetElementStatus.HP,
        )

        monsterHealMutual = MonsterCard(
            6L,
            name="몬스터 상호작용 회복",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "자신의 체력을 10 회복, 상대방의 체력을 5 회복한다.",
            cost = 2,
            cardTarget = CardTarget.MUTUAL,
            cardType = CardType.HEAL,
            effectSelfNum = 10.0,
            effectSelfStat = TargetElementStatus.HP,
            effectOpponentNum = 5.0,
            effectOpponentStat = TargetElementStatus.HP,
        )


        playerCardDebuffSelf = PlayerCard(
            7L,
            name="민첩 약화",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "자신의 민첩을 1 약화한다.",
            cost = 2,
            cardTarget = CardTarget.SELF,
            cardType = CardType.DEBUFF,
            effectSelfNum = -1.0,
            effectSelfStat = TargetElementStatus.DEX,
        )

        playerCardDebuffOpponent = PlayerCard(
            8L,
            name="상대 민첩 약화",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "상대방의 민첩을 1 약화한다.",
            cost = 2,
            cardTarget = CardTarget.OPPONENT,
            cardType = CardType.DEBUFF,
            effectSelfNum = -1.0,
            effectSelfStat = TargetElementStatus.DEX,
        )

        playerCardDebuffMutual = PlayerCard(
            9L,
            name="상호작용 민첩 약화",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "자신의 민첩을 1 약화 ,상대방의 민첩을 2 약화한다.",
            cost = 2,
            cardTarget = CardTarget.MUTUAL,
            cardType = CardType.DEBUFF,
            effectSelfNum = -1.0,
            effectSelfStat = TargetElementStatus.DEX,
            effectOpponentNum = -2.0,
            effectOpponentStat = TargetElementStatus.DEX,
        )

        monsterCardBuffSelf = MonsterCard(
            10L,
            name="몬스터 자신 강화",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "자신의 힘을 1 강화한다.",
            cost = 2,
            cardTarget = CardTarget.SELF,
            cardType = CardType.BUFF,
            effectSelfNum = 1.0,
            effectSelfStat = TargetElementStatus.STR,
        )

        monsterCardBuffOpponent = MonsterCard(
            11L,
            name="몬스터 상대 강화",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "상대방의 힘을 1 강화한다.",
            cost = 2,
            cardTarget = CardTarget.OPPONENT,
            cardType = CardType.BUFF,
            effectSelfNum = 1.0,
            effectSelfStat = TargetElementStatus.STR,
        )

        monsterCardBuffMutual = MonsterCard(
            12L,
            name="몬스터 상호작용 강화",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "자신의 힘을 2 강화, 상대방의 힘을 1 강화한다.",
            cost = 2,
            cardTarget = CardTarget.MUTUAL,
            cardType = CardType.BUFF,
            effectSelfNum = 2.0,
            effectSelfStat = TargetElementStatus.STR,
            effectOpponentNum = 1.0,
            effectOpponentStat = TargetElementStatus.STR,
        )

        player = Player()
        monster = Monster()

    }


    @Test
    fun `selfToSelf - 플레이어 덱스, 몬스터 힘강화`(){
        val playerBattleStatus = BattleStatus(playerCardBuffSelf,player.getStatus(),player) // 덱스 1강화
        val monsterBattleStatus = BattleStatus(monsterCardBuffSelf,monster.getStatus(),monster) // 힘 1강화

        defaultCardEffect.applyEffect(playerBattleStatus, monsterBattleStatus)

        assertEquals(2.0, playerBattleStatus.status.get(TargetElementStatus.DEX))
        assertEquals(2.0, monsterBattleStatus.status.get(TargetElementStatus.STR))

    }

    @Test
    fun `selfToSelf - 플레이어 덱스 디버프, 몬스터 자신힐`(){
        val playerBattleStatus = BattleStatus(playerCardDebuffSelf,player.getStatus(),player) // 덱스 1약화
        val monsterBattleStatus = BattleStatus(monsterHealSelf,monster.getStatus(),monster) // 체력 10회복

        defaultCardEffect.applyEffect(playerBattleStatus, monsterBattleStatus)

        assertEquals(0.0, playerBattleStatus.status.get(TargetElementStatus.DEX))
        assertEquals(10.0, monsterBattleStatus.status.get(TargetElementStatus.HP))


    }







}