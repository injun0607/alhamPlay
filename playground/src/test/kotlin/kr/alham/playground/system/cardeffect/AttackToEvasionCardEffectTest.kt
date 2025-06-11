package kr.alham.playground.system.cardeffect

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
import kr.alham.playground.system.calculator.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


class AttackToEvasionCardEffectTest {

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
    fun setUp() {
        damageCalculator = DamageCalculatorDefault()
        evasionRateCalculator = mockk()
        cardValueCalculator = DefaultCardValueCalculator()
        attackToEvasionCardEffect =
            AttackToEvasionCardEffect(cardValueCalculator, damageCalculator, evasionRateCalculator)
        monsterCardCutSelf = MonsterCard(
            id = 1L,
            name = "베기",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "자신에게 6의 피해를 줍니다",
            cardTarget = CardTarget.SELF,
            cardType = CardType.ATTACK,
            effectSelfNum = 6.0,
            effectSelfStat = TargetElementStatus.HP,
        )

        playerCardBashSelf = PlayerCard(
            id = 2L,
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
            id = 3L,
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
            id = 6L,
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
            id = 3L,
            name = "베기",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "상대에게 4의 피해를 줍니다",
            cardTarget = CardTarget.OPPONENT,
            cardType = CardType.ATTACK,
            effectOpponentNum = 4.0,
            effectOpponentStat = TargetElementStatus.HP,
        )

        monsterCardCutMutual = MonsterCard(
            id = 4L,
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
            name = "상대회피",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "상대에게 70%확률로 공격을 회피하게 합니다",
            cardTarget = CardTarget.OPPONENT,
            cardType = CardType.EVASION,
            effectOpponentNum = 0.7,
            effectOpponentStat = TargetElementStatus.HP,
        )

        playerCardMutualEvasion = PlayerCard(
            id = 9L,
            name = "상호작용 회피",
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
    fun `attackToEvasion - selfToSelf`() {

        val playerBattleStatus = BattleStatus(playerCardBashSelf, player.getStatus(),player)
        val monsterBattleStatus = BattleStatus(monsterCardSelfEvasion, monster.getStatus(),monster)

        attackToEvasionCardEffect.applyEffect(playerBattleStatus, monsterBattleStatus)

        assertEquals(5.0, playerBattleStatus.status.get(TargetElementStatus.HP), "자신에게 5의 피해를 줍니다")
        assertEquals(10.0, monsterBattleStatus.status.get(TargetElementStatus.HP), "상대에게 피해를 주지 않습니다")

        val playerBattleStatus2 = BattleStatus(playerCardBashSelf, player.getStatus(),player)
        val monsterBattleStatus2 = BattleStatus(monsterCardSelfEvasion, monster.getStatus(),monster)

        attackToEvasionCardEffect.applyEffect(monsterBattleStatus2, playerBattleStatus2)

        assertEquals(5.0, playerBattleStatus2.status.get(TargetElementStatus.HP), "자신에게 5의 피해를 줍니다")
        assertEquals(10.0, monsterBattleStatus2.status.get(TargetElementStatus.HP), "상대에게 피해를 주지 않습니다")


        val playerEvasionStatus = BattleStatus(playerCardSelfEvasion, player.getStatus(),player)
        val monsterSelfAttackStatus = BattleStatus(monsterCardCutSelf, monster.getStatus(),monster)

        attackToEvasionCardEffect.applyEffect(playerEvasionStatus, monsterSelfAttackStatus)

        assertEquals(4.0, monsterSelfAttackStatus.status.get(TargetElementStatus.HP), "자신에게 6의 피해를 줍니다")
        assertEquals(10.0, playerEvasionStatus.status.get(TargetElementStatus.HP), "상대에게 피해를 주지 않습니다")


        val playerEvasionStatus2 = BattleStatus(playerCardSelfEvasion, player.getStatus(),player)
        val monsterSelfAttackStatus2 = BattleStatus(monsterCardCutSelf, monster.getStatus(),monster)

        attackToEvasionCardEffect.applyEffect(monsterSelfAttackStatus2, playerEvasionStatus2)

        assertEquals(4.0, monsterSelfAttackStatus2.status.get(TargetElementStatus.HP), "자신에게 6의 피해를 줍니다")
        assertEquals(10.0, playerEvasionStatus2.status.get(TargetElementStatus.HP), "상대에게 피해를 주지 않습니다")


    }

    @Test
    fun `attackToEvasion - selfToOpponent - 플레이어 셀프 공격 상대방이 나에게 회피 - 판정성공`() {


        val playerBattleStatus = BattleStatus(playerCardBashSelf, player.getStatus(),player) //자신에게 5의피해
        val monsterBattleStatus = BattleStatus(monsterCardOpponentEvasion, monster.getStatus(),monster) //상대에게 40%확률 회피

        every { evasionRateCalculator.calculateEvasionSuccess(any()) } returns true
        attackToEvasionCardEffect.applyEffect(playerBattleStatus, monsterBattleStatus)

        assertEquals(10.0, playerBattleStatus.status.get(TargetElementStatus.HP), "몬스터 공격회피처리")
        assertEquals(10.0, monsterBattleStatus.status.get(TargetElementStatus.HP), "상대에게 피해를 주지 않습니다")

    }

    @Test
    fun `attackToEvasion - selfToOpponent - 플레이어 셀프 공격 상대방이 나에게 회피 - 판정실패`() {

        val playerBattleStatus = BattleStatus(playerCardBashSelf, player.getStatus(),player) //자신에게 5의피해
        val monsterBattleStatus = BattleStatus(monsterCardOpponentEvasion, monster.getStatus(),monster) //상대에게 40%확률 회피

        every { evasionRateCalculator.calculateEvasionSuccess(any()) } returns false
        attackToEvasionCardEffect.applyEffect(playerBattleStatus, monsterBattleStatus)

        assertEquals(5.0, playerBattleStatus.status.get(TargetElementStatus.HP), "몬스터 공격회피처리")
        assertEquals(10.0, monsterBattleStatus.status.get(TargetElementStatus.HP), "상대에게 피해를 주�� 않습니다")

    }

    @Test
    fun `attackToEvasion - selfToOpponent - 몬스터 셀프 공격 상대방이 나에게 회피 - 판정성공`() {

        val playerBattleStatus = BattleStatus(playerCardOpponentEvasion, player.getStatus(),player) //70%회피
        val monsterBattleStatus = BattleStatus(monsterCardCutSelf, monster.getStatus(),monster) //자신에게 6의피해

        every { evasionRateCalculator.calculateEvasionSuccess(any()) } returns true
        attackToEvasionCardEffect.applyEffect(monsterBattleStatus, playerBattleStatus)

        assertEquals(10.0, playerBattleStatus.status.get(TargetElementStatus.HP), "상대에게 피해를 받지 않습니다")
        assertEquals(10.0, monsterBattleStatus.status.get(TargetElementStatus.HP), "피해 회피")

    }

    @Test
    fun `attackToEvasion - selfToOpponent - 몬스터 셀프 공격 상대방이 나에게 회피 - 판정실패`() {

        val playerBattleStatus = BattleStatus(playerCardOpponentEvasion, player.getStatus(),player) //70%회피
        val monsterBattleStatus = BattleStatus(monsterCardCutSelf, monster.getStatus(),monster) //자신에게 6의피해

        every { evasionRateCalculator.calculateEvasionSuccess(any()) } returns false
        attackToEvasionCardEffect.applyEffect(monsterBattleStatus, playerBattleStatus)

        assertEquals(10.0, playerBattleStatus.status.get(TargetElementStatus.HP), "상대에게 피해를 받지 않습니다")
        assertEquals(4.0, monsterBattleStatus.status.get(TargetElementStatus.HP), "피해 회피")

    }


    @Test
    fun `attackToEvasion - selfToOpponent - 몬스터 상대 공격 상대방이 자신에게 회피 - 판정성공`() {

        val playerStatus = BattleStatus(playerCardSelfEvasion, player.getStatus(),player) //70%회피
        val monsterStatus = BattleStatus(monsterCardCutOpponent, monster.getStatus(),monster) //상대에게 4의피해


        every { evasionRateCalculator.calculateEvasionSuccess(any()) } returns true

        attackToEvasionCardEffect.applyEffect(playerStatus, monsterStatus)

        assertEquals(10.0, playerStatus.status.get(TargetElementStatus.HP), "상대에게 피해를 받지 않습니다")
        assertEquals(10.0, monsterStatus.status.get(TargetElementStatus.HP), "피해 X")

    }


    @Test
    fun `attackToEvasion - selfToOpponent - 몬스터 상대 공격 상대방이 자신에게 회피 - 판정실패`() {

        val playerStatus = BattleStatus(playerCardSelfEvasion, player.getStatus(),player) //70%회피
        val monsterStatus = BattleStatus(monsterCardCutOpponent, monster.getStatus(),monster) //상대에게 4의피해


        every { evasionRateCalculator.calculateEvasionSuccess(any()) } returns false

        attackToEvasionCardEffect.applyEffect(playerStatus, monsterStatus)

        assertEquals(6.0, playerStatus.status.get(TargetElementStatus.HP), "플레이어 체력 6")
        assertEquals(10.0, monsterStatus.status.get(TargetElementStatus.HP), "피해 X")

    }

    @Test
    fun `attackToEvasion - selfToOpponent - 플레이어 상대 공격 몬스터 자신에게 회피 - 판정실패`() {

        val playerStatus = BattleStatus(playerCardBashOpponent, player.getStatus(),player) //상대에게 8의 데미지
        val monsterStatus = BattleStatus(monsterCardSelfEvasion, monster.getStatus(),monster) //


        every { evasionRateCalculator.calculateEvasionSuccess(any()) } returns false

        attackToEvasionCardEffect.applyEffect(playerStatus, monsterStatus)

        assertEquals(10.0, playerStatus.status.get(TargetElementStatus.HP), "플레이어 체력 10")
        assertEquals(2.0, monsterStatus.status.get(TargetElementStatus.HP), "몬스터 체력 2")

    }

    @Test
    fun `attackToEvasion - selfToOpponent -플레이어 상대 공격 몬스터 자신에게 회피 - 판정성공`() {

        val playerStatus = BattleStatus(playerCardBashOpponent, player.getStatus(),player) //8의피해
        val monsterStatus = BattleStatus(monsterCardSelfEvasion, monster.getStatus(),monster)


        every { evasionRateCalculator.calculateEvasionSuccess(any()) } returns true

        attackToEvasionCardEffect.applyEffect(playerStatus, monsterStatus)

        assertEquals(10.0, playerStatus.status.get(TargetElementStatus.HP), "플레이어 체력 10")
        assertEquals(10.0, monsterStatus.status.get(TargetElementStatus.HP), "피해 X")

    }

    @Test
    fun `attattackToEvasion - selfToMutual - 플레이어 공격카드 상호작용 , 몬스터 자신에게 회피(성공)`(){
        val playerStatus = BattleStatus(playerCardBashMutual, player.getStatus(),player) //상대에게 7의 피해, 자신에게 2의 피해
        val monsterBattleStatus = BattleStatus(monsterCardSelfEvasion, monster.getStatus(),monster)

        every { evasionRateCalculator.calculateEvasionSuccess(any()) } returns true

        attackToEvasionCardEffect.applyEffect(playerStatus, monsterBattleStatus)
        assertEquals(8.0, playerStatus.status.get(TargetElementStatus.HP), "플레이어체력 8")
        assertEquals(10.0, monsterBattleStatus.status.get(TargetElementStatus.HP), "몬스터 체력 10")

    }

    @Test
    fun `attattackToEvasion - selfToMutual - 플레이어 공격카드 상호작용 , 몬스터 자신에게 회피(실패)`(){
        val playerStatus = BattleStatus(playerCardBashMutual, player.getStatus(),player) //상대에게 7의 피해, 자신에게 2의 피해
        val monsterBattleStatus = BattleStatus(monsterCardSelfEvasion, monster.getStatus(),monster) //상대에게 40%확률 회피

        every { evasionRateCalculator.calculateEvasionSuccess(any()) } returns false

        attackToEvasionCardEffect.applyEffect(playerStatus, monsterBattleStatus)
        assertEquals(8.0, playerStatus.status.get(TargetElementStatus.HP), "플레이어 체력 8")
        assertEquals(3.0, monsterBattleStatus.status.get(TargetElementStatus.HP), "몬스터 체력 3")
    }


    @Test
    fun `attattackToEvasion - selfToMutual - 몬스터 공격카드 상호작용 , 플레이어 자신에게 회피(성공)`(){
        val playerStatus = BattleStatus(playerCardSelfEvasion, player.getStatus(),player)
        val monsterBattleStatus = BattleStatus(monsterCardCutMutual, monster.getStatus(),monster) //상대에게 3, 자신에게 1의 피해

        every { evasionRateCalculator.calculateEvasionSuccess(any()) } returns true

        attackToEvasionCardEffect.applyEffect(playerStatus, monsterBattleStatus)
        assertEquals(10.0, playerStatus.status.get(TargetElementStatus.HP), "플레이어체력 10")
        assertEquals(9.0, monsterBattleStatus.status.get(TargetElementStatus.HP), "몬스터 체력 9")

    }

    @Test
    fun `attattackToEvasion - selfToMutual - 몬스터 공격카드 상호작용 ,  플레이어 자신에게 회피(실패)`(){
        val playerStatus = BattleStatus(playerCardSelfEvasion, player.getStatus(),player) //
        val monsterBattleStatus = BattleStatus(monsterCardCutMutual, monster.getStatus(),monster) //상대에게 3, 자신에게 1의 피해

        every { evasionRateCalculator.calculateEvasionSuccess(any()) } returns false

        attackToEvasionCardEffect.applyEffect(playerStatus, monsterBattleStatus)
        assertEquals(7.0, playerStatus.status.get(TargetElementStatus.HP), "플레이어 체력 7")
        assertEquals(9.0, monsterBattleStatus.status.get(TargetElementStatus.HP), "몬스터 체력 9")
    }

    @Test
    fun `attattackToEvasion - opponentToMutual - 플레이어 상대 공격카드 , 몬스터 상호 회피(실패)`(){
        val playerStatus = BattleStatus(playerCardBashOpponent, player.getStatus(),player) //상대에게 8의 피홰
        val monsterBattleStatus = BattleStatus(monsterCardMutualEvasion, monster.getStatus(),monster) //상대에게 30%확률 회피

        every { evasionRateCalculator.calculateEvasionSuccess(any()) } returns false

        attackToEvasionCardEffect.applyEffect(playerStatus, monsterBattleStatus)
        assertEquals(10.0, playerStatus.status.get(TargetElementStatus.HP), "플레이어체력 10")
        assertEquals(2.0, monsterBattleStatus.status.get(TargetElementStatus.HP), "몬스터 체력 2")
    }

    @Test
    fun `attattackToEvasion - opponentToMutual - 플레이어 상대 공격카드 , 몬스터 상호 회피(성공)`(){
        val playerStatus = BattleStatus(playerCardBashMutual, player.getStatus(),player) //상대에게 8의 피홰
        val monsterBattleStatus = BattleStatus(monsterCardMutualEvasion, monster.getStatus(),monster)

        every { evasionRateCalculator.calculateEvasionSuccess(any()) } returns true

        attackToEvasionCardEffect.applyEffect(playerStatus, monsterBattleStatus)
        assertEquals(10.0, playerStatus.status.get(TargetElementStatus.HP), "플레이어체력 10")
        assertEquals(10.0, monsterBattleStatus.status.get(TargetElementStatus.HP), "몬스터 체력 10")
    }

    @Test
    fun `attattackToEvasion - opponentToMutual - 플레이어 상호작용회피 , 몬스터 상대 공격(회피 실패)`(){
        val playerStatus = BattleStatus(playerCardMutualEvasion, player.getStatus(),player) //
        val monsterBattleStatus = BattleStatus(monsterCardCutOpponent, monster.getStatus(),monster) //상대에게 4의 피해

        every { evasionRateCalculator.calculateEvasionSuccess(any()) } returns false

        attackToEvasionCardEffect.applyEffect(playerStatus, monsterBattleStatus)
        assertEquals(6.0, playerStatus.status.get(TargetElementStatus.HP), "플레이어체력 6")
        assertEquals(10.0, monsterBattleStatus.status.get(TargetElementStatus.HP), "몬스터 체력 10")
    }

    @Test
    fun `attattackToEvasion - opponentToMutual - 플레이어 상호작용회피 , 몬스터 상대 공격(회피 성공)`(){
        val playerStatus = BattleStatus(playerCardMutualEvasion, player.getStatus(),player) //
        val monsterBattleStatus = BattleStatus(monsterCardCutOpponent, monster.getStatus(),monster) //상대에게 4의 피해

        every { evasionRateCalculator.calculateEvasionSuccess(any()) } returns true

        attackToEvasionCardEffect.applyEffect(playerStatus, monsterBattleStatus)
        assertEquals(10.0, playerStatus.status.get(TargetElementStatus.HP), "플레이어체력 10")
        assertEquals(10.0, monsterBattleStatus.status.get(TargetElementStatus.HP), "몬스터 체력 10")
    }

    @Test
    fun `attackToEvasion - opponentToOpponent - 플레이어 공격 , 몬스터 상대 회피`(){
        val playerStatus = BattleStatus(playerCardBashOpponent, player.getStatus(),player) //상대에게 8의 피해
        val monsterBattleStatus = BattleStatus(monsterCardOpponentEvasion, monster.getStatus(),monster) //상대에게 40%확률 회피

        attackToEvasionCardEffect.applyEffect(playerStatus, monsterBattleStatus)


        assertEquals(10.0, playerStatus.status.get(TargetElementStatus.HP), "플레이어 체력 10")
        assertEquals(2.0, monsterBattleStatus.status.get(TargetElementStatus.HP), "몬스터 체력 2")
    }

    @Test
    fun `attackToEvasion - opponentToOpponent - 몬스터 공격 , 플레이어 상대 회피`(){
        val playerStatus = BattleStatus(playerCardOpponentEvasion, player.getStatus(),player)
        val monsterBattleStatus = BattleStatus(monsterCardCutOpponent, monster.getStatus(),monster) //상대에게 4의 피해

        attackToEvasionCardEffect.applyEffect(playerStatus, monsterBattleStatus)


        assertEquals(6.0, playerStatus.status.get(TargetElementStatus.HP), "플레이어 체력 6")
        assertEquals(10.0, monsterBattleStatus.status.get(TargetElementStatus.HP), "몬스터 체력 10")
    }


    @Test
    fun `attackToEvasion - mutualToMutual - 몬스터 상호공격, 플레이어 상호 회피 - 모두 회피성공`(){
        val playerStatus = BattleStatus(playerCardMutualEvasion, player.getStatus(),player) //상대에게 50%확률 회피
        val monsterBattleStatus = BattleStatus(monsterCardCutMutual, monster.getStatus(),monster) //상대에게 30%확률 회피

        every { evasionRateCalculator.calculateEvasionSuccess(any()) } returns true
        attackToEvasionCardEffect.applyEffect(playerStatus, monsterBattleStatus)

        assertEquals(10.0, playerStatus.status.get(TargetElementStatus.HP), "플레이어 체력 10")
        assertEquals(10.0, monsterBattleStatus.status.get(TargetElementStatus.HP), "몬스터 체력 10")
    }

    @Test
    fun `attackToEvasion - mutualToMutual - 몬스터 상호공격, 플레이어 상호 회피 - 모두 회피실패`(){
        val playerStatus = BattleStatus(playerCardMutualEvasion, player.getStatus(),player) //상대에게 50%확률 회피
        val monsterBattleStatus = BattleStatus(monsterCardCutMutual, monster.getStatus(),monster) //상대에게 30%확률 회피

        every { evasionRateCalculator.calculateEvasionSuccess(any()) } returns false
        attackToEvasionCardEffect.applyEffect(playerStatus, monsterBattleStatus)

        assertEquals(7.0, playerStatus.status.get(TargetElementStatus.HP), "플레이어 체력 7")
        assertEquals(9.0, monsterBattleStatus.status.get(TargetElementStatus.HP), "몬스터 체력 9")
    }

    @Test
    fun `attackToEvasion - mutualToMutual - 몬스터 상호회피, 플레이어 상호 공격 - 모두 회피성공`(){
        val playerStatus = BattleStatus(playerCardBashMutual, player.getStatus(),player) //상대에게 7 , 자신에게 2
        val monsterBattleStatus = BattleStatus(monsterCardCutMutual, monster.getStatus(),monster) //상대에게 30%확률 회피

        every { evasionRateCalculator.calculateEvasionSuccess(any()) } returns true
        attackToEvasionCardEffect.applyEffect(playerStatus, monsterBattleStatus)

        assertEquals(10.0, playerStatus.status.get(TargetElementStatus.HP), "플레이어 체력 10")
        assertEquals(10.0, monsterBattleStatus.status.get(TargetElementStatus.HP), "몬스터 체력 10")
    }

    @Test
    fun `attackToEvasion - mutualToMutual - 몬스터 상호회피, 플레이어 상호 공격 - 모두 회피실패`(){
        val playerStatus = BattleStatus(playerCardMutualEvasion, player.getStatus(),player) //상대에게 50%확률 회피
        val monsterBattleStatus = BattleStatus(monsterCardCutMutual, monster.getStatus(),monster) //상대에게 30%확률 회피

        every { evasionRateCalculator.calculateEvasionSuccess(any()) } returns false
        attackToEvasionCardEffect.applyEffect(playerStatus, monsterBattleStatus)

        assertEquals(7.0, playerStatus.status.get(TargetElementStatus.HP), "플레이어 체력 7")
        assertEquals(9.0, monsterBattleStatus.status.get(TargetElementStatus.HP), "몬스터 체력 9")
    }



}