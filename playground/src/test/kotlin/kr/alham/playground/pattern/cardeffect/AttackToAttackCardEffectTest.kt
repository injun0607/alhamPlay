package kr.alham.playground.pattern.cardeffect

import kr.alham.playground.domain.battle.BattleStatus
import kr.alham.playground.domain.card.MonsterCard
import kr.alham.playground.domain.card.PlayerCard
import kr.alham.playground.domain.common.TargetElementStatus
import kr.alham.playground.domain.enums.BattlePhase
import kr.alham.playground.domain.enums.CardTarget
import kr.alham.playground.domain.enums.CardType
import kr.alham.playground.domain.monster.Monster
import kr.alham.playground.domain.player.Player
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AttackToAttackCardEffectTest{


    lateinit var attackToAttackCardEffect: AttackToAttackCardEffect
    lateinit var attackToDefenceCardEffect: AttackToDefenceCardEffect
    lateinit var playerCardBashSelf: PlayerCard
    lateinit var playerCardBashOpponent: PlayerCard
    lateinit var monsterCardCutSelf: MonsterCard
    lateinit var monsterCardCutOpponent: MonsterCard
    lateinit var monsterCardCutMutual: MonsterCard
    lateinit var playerCardBashMutual: PlayerCard
    lateinit var playerCardSelfShield: PlayerCard
    lateinit var playerCardOpponentShield: PlayerCard
    lateinit var playerCardMutualShield: PlayerCard
    lateinit var monsterCardSelfShield: MonsterCard
    lateinit var monsterCardOpponentShield: MonsterCard
    lateinit var monsterCardMutualShield: MonsterCard



    lateinit var player: Player
    lateinit var monster: Monster



    @BeforeEach
    fun setUp(){
        attackToAttackCardEffect = AttackToAttackCardEffect()
        attackToDefenceCardEffect = AttackToDefenceCardEffect()
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

        playerCardSelfShield = PlayerCard(
            id = 7L,
            name = "방어",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "자신에게 4의 방어력을 줍니다",
            cardTarget = CardTarget.SELF,
            cardType = CardType.DEFENCE,
            effectSelfNum = 4.0,
            effectSelfStat = TargetElementStatus.HP,
        )

        playerCardOpponentShield = PlayerCard(
            id = 8L,
            name="상대방어",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "상대에게 3의 방어력을 줍니다",
            cardTarget = CardTarget.OPPONENT,
            cardType = CardType.DEFENCE,
            effectOpponentNum = 3.0,
            effectOpponentStat = TargetElementStatus.HP,
        )

        playerCardMutualShield = PlayerCard(
            id = 9L,
            name="상호작용 방어",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "나에게 방어5, 상대에게 방어5를 줍니다",
            cardTarget = CardTarget.MUTUAL,
            cardType = CardType.DEFENCE,
            effectSelfNum = 5.0,
            effectSelfStat = TargetElementStatus.HP,
            effectOpponentNum = 5.0,
            effectOpponentStat = TargetElementStatus.HP,
        )

        monsterCardSelfShield = MonsterCard(
            id = 10L,
            name = "몬스터 방어",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "자신에게 5의 방어력을 줍니다",
            cardTarget = CardTarget.SELF,
            cardType = CardType.DEFENCE,
            effectSelfNum = 5.0,
            effectSelfStat = TargetElementStatus.HP,
        )

        monsterCardOpponentShield = MonsterCard(
            id = 11L,
            name = "몬스터 상대방어",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "상대에게 4의 방어력을 줍니다",
            cardTarget = CardTarget.OPPONENT,
            cardType = CardType.DEFENCE,
            effectOpponentNum = 4.0,
            effectOpponentStat = TargetElementStatus.HP,
        )

        monsterCardOpponentShield = MonsterCard(
            id = 12L,
            name = "몬스터 상호작용 방어",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "나에게 방어3, 상대에게 방어3를 줍니다",
            cardTarget = CardTarget.MUTUAL,
            cardType = CardType.DEFENCE,
            effectSelfNum = 3.0,
            effectSelfStat = TargetElementStatus.HP,
            effectOpponentNum = 3.0,
            effectOpponentStat = TargetElementStatus.HP,
        )



        player = Player()
        monster = Monster()
    }


    @Test
    fun `attackToAttack-두카드 모두 self효과`(){

        val playerBattleStatus = BattleStatus(playerCardBashSelf, player.getStatus())
        val monsterBattleStatus = BattleStatus(monsterCardCutSelf, monster.getStatus())

        attackToAttackCardEffect.applyEffect(playerBattleStatus, monsterBattleStatus)

        assertEquals(4.0,monsterBattleStatus.status.get(TargetElementStatus.HP))
        assertEquals(5.0,playerBattleStatus.status.get(TargetElementStatus.HP))

    }

    @Test
    fun `attackToAttack-self to opponent`(){

        val playerBattleStatus = BattleStatus(playerCardBashSelf, player.getStatus())
        val monsterBattleStatus = BattleStatus(monsterCardCutOpponent, monster.getStatus())

        attackToAttackCardEffect.applyEffect(playerBattleStatus, monsterBattleStatus)

        assertEquals(1.0,playerBattleStatus.status.get(TargetElementStatus.HP),"플레이어 체력 1")
        assertEquals(10.0,monsterBattleStatus.status.get(TargetElementStatus.HP),"몬스터체력10")

    }

    @Test
    fun `attackToAttack-self to mutual`(){

        val playerBattleStatus = BattleStatus(playerCardBashSelf, player.getStatus())
        val monsterBattleStatus = BattleStatus(monsterCardCutMutual, monster.getStatus())

        attackToAttackCardEffect.applyEffect(playerBattleStatus, monsterBattleStatus)

        assertEquals(2.0,playerBattleStatus.status.get(TargetElementStatus.HP),"플레이어 체력 2")
        assertEquals(9.0,monsterBattleStatus.status.get(TargetElementStatus.HP),"몬스터체력9")

    }

    @Test
    fun `attackToAttack -opponent to mutual`(){

        val playerBattleStatus = BattleStatus(playerCardBashOpponent, player.getStatus())
        val monsterBattleStatus = BattleStatus(monsterCardCutMutual, monster.getStatus())

        attackToAttackCardEffect.applyEffect(playerBattleStatus, monsterBattleStatus)

        assertEquals(7.0,playerBattleStatus.status.get(TargetElementStatus.HP),"플레이어 체력 7")
        assertEquals(1.0,monsterBattleStatus.status.get(TargetElementStatus.HP),"몬스터체력1")

    }

    @Test
    fun `attackToAttack - opponentToopponent`(){

        val playerBattleStatus = BattleStatus(playerCardBashOpponent, player.getStatus())
        val monsterBattleStatus = BattleStatus(monsterCardCutOpponent, monster.getStatus())

        attackToAttackCardEffect.applyEffect(playerBattleStatus, monsterBattleStatus)

        assertEquals(6.0,playerBattleStatus.status.get(TargetElementStatus.HP),"플레이어 체력 6")
        assertEquals(2.0,monsterBattleStatus.status.get(TargetElementStatus.HP),"몬스터체력2")

    }

    @Test
    fun `attackToAttack - mutualToMutual`(){


        val playerBattleStatus = BattleStatus(playerCardBashMutual, player.getStatus())
        val monsterBattleStatus = BattleStatus(monsterCardCutMutual, monster.getStatus())

        attackToAttackCardEffect.applyEffect(playerBattleStatus, monsterBattleStatus)

        assertEquals(5.0,playerBattleStatus.status.get(TargetElementStatus.HP),"플레이어 체력 5")
        assertEquals(2.0,monsterBattleStatus.status.get(TargetElementStatus.HP),"몬스터체력2")



    }


}