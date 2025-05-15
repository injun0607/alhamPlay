package kr.alham.playground.pattern.cardeffect

import io.mockk.every
import io.mockk.mockk
import kr.alham.playground.domain.battle.BattleStatus
import kr.alham.playground.domain.card.MonsterCard
import kr.alham.playground.domain.card.PlayerCard
import kr.alham.playground.domain.common.TargetElementStatus
import kr.alham.playground.domain.enums.BattlePhase
import kr.alham.playground.domain.enums.CardTarget
import kr.alham.playground.domain.enums.CardType
import kr.alham.playground.domain.monster.Monster
import kr.alham.playground.domain.player.Player
import kr.alham.playground.pattern.calculator.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class AttackToEvasionCardEffectTest{

    lateinit var cardValueCalculator: CardValueCalculator
    lateinit var attackToEvasionCardEffect: AttackToEvasionCardEffect
    lateinit var damageCalculator: DamageCalculator
    lateinit var evasionRateCalculator: EvasionRateCalculator
    lateinit var playerCardBashSelf: PlayerCard
    lateinit var playerCardBashOpponent: PlayerCard
    lateinit var monsterCardCutSelf: MonsterCard
    lateinit var monsterCardCutOpponent: MonsterCard
    lateinit var monsterCardCutMutual: MonsterCard
    lateinit var playerCardBashMutual: PlayerCard
    lateinit var playerCardSelfEvasion: PlayerCard
    lateinit var playerCardOpponentEvasion: PlayerCard
    lateinit var playerCardMutualEvasion: PlayerCard
    lateinit var monsterCardSelfEvasion: MonsterCard
    lateinit var monsterCardOpponentEvasion: MonsterCard
    lateinit var monsterCardMutualEvasion: MonsterCard

    lateinit var player: Player
    lateinit var monster: Monster


    @BeforeEach
    fun setUp(){
        damageCalculator = DamageCalculatorDefault()
        evasionRateCalculator = mockk()
        cardValueCalculator = DefaultCardValueCalculator()
        attackToEvasionCardEffect = AttackToEvasionCardEffect(cardValueCalculator,damageCalculator,evasionRateCalculator)
        monsterCardCutSelf = MonsterCard(
            id=1L,
            name = "베기",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "자신에게 6의 피해를 줍니다",
            cardTarget = CardTarget.SELF,
            cardType = CardType.ATTACK,
            effectSelfNum = 6.0,
            effectSelfStat = TargetElementStatus.HP,
        )

        playerCardBashSelf = PlayerCard(
            id=2L,
            name = "강타",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "자신에게 5의 피해를 줍니다.",
            cost = 2,
            cardTarget = CardTarget.SELF,
            cardType = CardType.ATTACK,
            effectSelfNum = 5.0,
            effectSelfStat = TargetElementStatus.HP,
        )

        playerCardBashOpponent = PlayerCard(
            id=3L,
            name = "강타",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "상대에게 8의 피해를 줍니다.",
            cost = 2,
            cardTarget = CardTarget.OPPONENT,
            cardType = CardType.ATTACK,
            effectOpponentNum = 8.0,
            effectOpponentStat = TargetElementStatus.HP,
        )

        playerCardBashMutual = PlayerCard(
            id=6L,
            name = "강타",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "상대에게 7의 피해, 자신에게 피해 2을 줍니다",
            cost = 2,
            cardTarget = CardTarget.MUTUAL,
            cardType = CardType.ATTACK,
            effectSelfNum = 2.0,
            effectSelfStat = TargetElementStatus.HP,
            effectOpponentNum = 7.0,
            effectOpponentStat = TargetElementStatus.HP,
        )

        monsterCardCutOpponent = MonsterCard(
            id=3L,
            name = "베기",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "상대에게 4의 피해를 줍니다",
            cardTarget = CardTarget.OPPONENT,
            cardType = CardType.ATTACK,
            effectOpponentNum = 4.0,
            effectOpponentStat = TargetElementStatus.HP,
        )

        monsterCardCutMutual = MonsterCard(
            id=4L,
            name = "베기",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "상대에게 3의 피해, 자신에게 피해 1을 줍니다",
            cardTarget = CardTarget.MUTUAL,
            cardType = CardType.ATTACK,
            effectSelfNum = 1.0,
            effectSelfStat = TargetElementStatus.HP,
            effectOpponentNum = 3.0,
            effectOpponentStat = TargetElementStatus.HP,
        )

        playerCardSelfEvasion = PlayerCard(
            id = 7L,
            name = "회피",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "70%의 확률로 상대방의 공격을 회피합니다",
            cardTarget = CardTarget.SELF,
            cardType = CardType.EVASION,
            effectSelfNum = 0.7,
            effectSelfStat = TargetElementStatus.HP,
        )

        playerCardOpponentEvasion = PlayerCard(
            id = 8L,
            name="상대회피",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "상대에게 70%확률로 공격을 회피하게 합니다",
            cardTarget = CardTarget.OPPONENT,
            cardType = CardType.EVASION,
            effectOpponentNum = 0.7,
            effectOpponentStat = TargetElementStatus.HP,
        )

        playerCardMutualEvasion = PlayerCard(
            id = 9L,
            name="상호작용 회피",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "나에게 50%확률로 회피, 상대에게 50%확률로 회피하게합니다.",
            cardTarget = CardTarget.MUTUAL,
            cardType = CardType.EVASION,
            effectSelfNum = 0.5,
            effectSelfStat = TargetElementStatus.HP,
            effectOpponentNum = 0.5,
            effectOpponentStat = TargetElementStatus.HP,
        )

        monsterCardSelfEvasion = MonsterCard(
            id = 10L,
            name = "몬스터 회피",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "60%의 확률로 회피합니다.",
            cardTarget = CardTarget.SELF,
            cardType = CardType.EVASION,
            effectSelfNum = 0.6,
            effectSelfStat = TargetElementStatus.HP,
        )

        monsterCardOpponentEvasion = MonsterCard(
            id = 11L,
            name = "몬스터 상대회피",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "상대에게 40%의 확률로 회피를 줍니다",
            cardTarget = CardTarget.OPPONENT,
            cardType = CardType.EVASION,
            effectOpponentNum = 0.4,
            effectOpponentStat = TargetElementStatus.HP,
        )

        monsterCardMutualEvasion = MonsterCard(
            id = 12L,
            name = "몬스터 상호작용 회피",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "자신에게 30%확률로 회피, 상대에게 30%의 회피확률을 줍니다",
            cardTarget = CardTarget.MUTUAL,
            cardType = CardType.EVASION,
            effectSelfNum = 0.3,
            effectSelfStat = TargetElementStatus.HP,
            effectOpponentNum = 0.3,
            effectOpponentStat = TargetElementStatus.HP,
        )

        player = Player()
        monster = Monster()
    }
    
    @Test
    fun `attackToEvasion - selfToSelf`(){

        val playerBattleStatus = BattleStatus(playerCardBashSelf, player.getStatus())
        val monsterBattleStatus = BattleStatus(monsterCardSelfEvasion, monster.getStatus())

        attackToEvasionCardEffect.applyEffect(playerBattleStatus, monsterBattleStatus)

        assertEquals(5.0, playerBattleStatus.status.get(TargetElementStatus.HP), "자신에게 5의 피해를 줍니다")
        assertEquals(10.0, monsterBattleStatus.status.get(TargetElementStatus.HP), "상대에게 피해를 주지 않습니다")

        val playerBattleStatus2 = BattleStatus(playerCardBashSelf, player.getStatus())
        val monsterBattleStatus2 = BattleStatus(monsterCardSelfEvasion, monster.getStatus())

        attackToEvasionCardEffect.applyEffect(monsterBattleStatus2,playerBattleStatus2)

        assertEquals(5.0, playerBattleStatus2.status.get(TargetElementStatus.HP), "자신에게 5의 피해를 줍니다")
        assertEquals(10.0, monsterBattleStatus2.status.get(TargetElementStatus.HP), "상대에게 피해를 주지 않습니다")


        val playerEvasionStatus = BattleStatus(playerCardSelfEvasion, player.getStatus())
        val monsterSelfAttackStatus = BattleStatus(monsterCardCutSelf, monster.getStatus())

        attackToEvasionCardEffect.applyEffect(playerEvasionStatus, monsterSelfAttackStatus)

        assertEquals(4.0, monsterSelfAttackStatus.status.get(TargetElementStatus.HP), "자신에게 6의 피해를 줍니다")
        assertEquals(10.0, playerEvasionStatus.status.get(TargetElementStatus.HP), "상대에게 피해를 주지 않습니다")


        val playerEvasionStatus2 = BattleStatus(playerCardSelfEvasion, player.getStatus())
        val monsterSelfAttackStatus2 = BattleStatus(monsterCardCutSelf, monster.getStatus())

        attackToEvasionCardEffect.applyEffect( monsterSelfAttackStatus2, playerEvasionStatus2)

        assertEquals(4.0, monsterSelfAttackStatus2.status.get(TargetElementStatus.HP), "자신에게 6의 피해를 줍니다")
        assertEquals(10.0, playerEvasionStatus2.status.get(TargetElementStatus.HP), "상대에게 피해를 주지 않습니다")


    }

    @Test
    fun `attackToEvasion - selfToOpponent - 플레이어 셀프 공격 상대방이 나에게 회피 - 판정성공`() {


        val playerBattleStatus = BattleStatus(playerCardBashSelf, player.getStatus()) //자신에게 5의피해
        val monsterBattleStatus = BattleStatus(monsterCardOpponentEvasion, monster.getStatus()) //상대에게 40%확률 회피

        every { evasionRateCalculator.calculateEvasionSuccess(any()) } returns true
        attackToEvasionCardEffect.applyEffect(playerBattleStatus, monsterBattleStatus)

        assertEquals(10.0, playerBattleStatus.status.get(TargetElementStatus.HP), "몬스터 공격회피처리")
        assertEquals(10.0, monsterBattleStatus.status.get(TargetElementStatus.HP), "상대에게 피해를 주지 않습니다")

    }

    @Test
    fun `attackToEvasion - selfToOpponent - 플레이어 셀프 공격 상대방이 나에게 회피 - 판정실패`() {

        val playerBattleStatus = BattleStatus(playerCardBashSelf, player.getStatus()) //자신에게 5의피해
        val monsterBattleStatus = BattleStatus(monsterCardOpponentEvasion, monster.getStatus()) //상대에게 40%확률 회피

        every { evasionRateCalculator.calculateEvasionSuccess(any()) } returns false
        attackToEvasionCardEffect.applyEffect(playerBattleStatus, monsterBattleStatus)

        assertEquals(5.0, playerBattleStatus.status.get(TargetElementStatus.HP), "몬스터 공격회피처리")
        assertEquals(10.0, monsterBattleStatus.status.get(TargetElementStatus.HP), "상대에게 피해를 주�� 않습니다")

    }

    @Test
    fun `attackToEvasion - selfToOpponent - 몬스터 셀프 공격 상대방이 나에게 회피 - 판정성공`() {

        val playerBattleStatus = BattleStatus(playerCardOpponentEvasion, player.getStatus()) //70%회피
        val monsterBattleStatus = BattleStatus(monsterCardCutSelf, monster.getStatus()) //자신에게 6의피해

        every { evasionRateCalculator.calculateEvasionSuccess(any()) } returns true
        attackToEvasionCardEffect.applyEffect(monsterBattleStatus, playerBattleStatus)

        assertEquals(10.0, playerBattleStatus.status.get(TargetElementStatus.HP), "상대에게 피해를 받지 않습니다")
        assertEquals(10.0, monsterBattleStatus.status.get(TargetElementStatus.HP), "피해 회피")

    }

    @Test
    fun `attackToEvasion - selfToOpponent - 몬스터 셀프 공격 상대방이 나에게 회피 - 판정실패`() {

        val playerBattleStatus = BattleStatus(playerCardOpponentEvasion, player.getStatus()) //70%회피
        val monsterBattleStatus = BattleStatus(monsterCardCutSelf, monster.getStatus()) //자신에게 6의피해

        every { evasionRateCalculator.calculateEvasionSuccess(any()) } returns false
        attackToEvasionCardEffect.applyEffect(monsterBattleStatus, playerBattleStatus)

        assertEquals(10.0, playerBattleStatus.status.get(TargetElementStatus.HP), "상대에게 피해를 받지 않습니다")
        assertEquals(4.0, monsterBattleStatus.status.get(TargetElementStatus.HP), "피해 회피")

    }



}