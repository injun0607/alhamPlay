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

class AttackToDefenceCardEffectTest{

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

        monsterCardMutualShield = MonsterCard(
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
    fun `attackToDefence - SelfToSelf - 한명 자기공격, 한명 자기방어`(){
        val playerBattleStatus = BattleStatus(playerCardSelfShield, player.getStatus()) //방어력 4
        val monsterBattleStatus = BattleStatus(monsterCardCutSelf, monster.getStatus()) //자신에게 6의 피해

        attackToDefenceCardEffect.applyEffect(playerBattleStatus, monsterBattleStatus)

        assertEquals(10.0,playerBattleStatus.status.get(TargetElementStatus.HP),"플레이어 체력 10")
        assertEquals(4.0,monsterBattleStatus.status.get(TargetElementStatus.HP),"몬스터 체력 4")

        val playerBattleStatus2 = BattleStatus(playerCardBashSelf, player.getStatus()) //자신에게 5의피해
        val monsterBattleStatus2 = BattleStatus(monsterCardSelfShield, monster.getStatus()) //방어 5

        attackToDefenceCardEffect.applyEffect(playerBattleStatus2, monsterBattleStatus2)

        assertEquals(5.0,playerBattleStatus2.status.get(TargetElementStatus.HP),"플레이어 체력 5")
        assertEquals(10.0,monsterBattleStatus2.status.get(TargetElementStatus.HP),"몬스터 체력 10")

    }

    @Test
    fun `attackToDefence - selfToOpponent - 한명 방어, 한명 공격`(){
        val playerShieldBattleStatus = BattleStatus(playerCardSelfShield, player.getStatus()) //방어력 4
        val mosnterAttackBattleStatus = BattleStatus(monsterCardCutOpponent, monster.getStatus()) //상대에게 4의 피해

        attackToDefenceCardEffect.applyEffect(mosnterAttackBattleStatus, playerShieldBattleStatus)

        assertEquals(10.0,playerShieldBattleStatus.status.get(TargetElementStatus.HP),"플레이어 체력 10")
        assertEquals(10.0,mosnterAttackBattleStatus.status.get(TargetElementStatus.HP),"몬스터 체력 10")

        val playerAttackBattleStatus = BattleStatus(playerCardBashOpponent, player.getStatus()) //상대에게 8의 피해
        val monsterShieldBattleStatus = BattleStatus(monsterCardSelfShield, monster.getStatus()) //자신에게 5의 방어력

        attackToDefenceCardEffect.applyEffect(monsterShieldBattleStatus,playerAttackBattleStatus)

        assertEquals(10.0,playerAttackBattleStatus.status.get(TargetElementStatus.HP),"플레이어 체력 10")
        assertEquals(7.0,monsterShieldBattleStatus.status.get(TargetElementStatus.HP),"몬스터 체력 7")

    }

    @Test
    fun `attackToDefence - selfToMutual`(){
        val playerShieldBattleStatus = BattleStatus(playerCardSelfShield, player.getStatus()) //방어력 4
        val monsterMutualBattleStatus = BattleStatus(monsterCardCutMutual, monster.getStatus()) //상대에게 3의 피해, 자신에게 1의 피해

        attackToDefenceCardEffect.applyEffect(playerShieldBattleStatus, monsterMutualBattleStatus)

        assertEquals(10.0,playerShieldBattleStatus.status.get(TargetElementStatus.HP),"플레이어 체력 10")
        assertEquals(9.0,monsterMutualBattleStatus.status.get(TargetElementStatus.HP),"몬스터 체력 9")

        val playerShieldBattleStatus2 = BattleStatus(playerCardSelfShield, player.getStatus()) //방어력 4
        val monsterMutualBattleStatus2 = BattleStatus(monsterCardCutMutual, monster.getStatus()) //상대에게 3의 피해, 자신에게 1의 피해

        attackToDefenceCardEffect.applyEffect(monsterMutualBattleStatus2, playerShieldBattleStatus2)
        assertEquals(10.0,playerShieldBattleStatus2.status.get(TargetElementStatus.HP),"플레이어 체력 10")
        assertEquals(9.0,monsterMutualBattleStatus2.status.get(TargetElementStatus.HP),"몬스터 체력 9")


        val playerMutualBattleStatus = BattleStatus(playerCardBashMutual, player.getStatus()) //상대에게 7의 피해, 자신에게 2의 피해
        val monsterShieldBattleStatus = BattleStatus(monsterCardSelfShield, monster.getStatus()) //자신에게 5의 방어력

        attackToDefenceCardEffect.applyEffect(playerMutualBattleStatus, monsterShieldBattleStatus)

        assertEquals(8.0,playerMutualBattleStatus.status.get(TargetElementStatus.HP),"플레이어 체력 8")
        assertEquals(8.0,monsterShieldBattleStatus.status.get(TargetElementStatus.HP),"몬스터 체력 8")

        val playerMutualBattleStatus2 = BattleStatus(playerCardBashMutual, player.getStatus()) //상대에게 7의 피해, 자신에게 2의 피해
        val monsterShieldBattleStatus2 = BattleStatus(monsterCardSelfShield, monster.getStatus()) //자신에게 5의 방어력

        attackToDefenceCardEffect.applyEffect(monsterShieldBattleStatus2, playerMutualBattleStatus2)

        assertEquals(8.0,playerMutualBattleStatus2.status.get(TargetElementStatus.HP),"플레이어 체력 8")
        assertEquals(8.0,monsterShieldBattleStatus2.status.get(TargetElementStatus.HP),"몬스터 체력 8")

        val playerShieldMutualBattleStatus = BattleStatus(playerCardMutualShield, player.getStatus()) //자신에게 방어 5, 상대에게 방어 5
        val monsterAttackSelfBattleStatus = BattleStatus(monsterCardCutSelf, monster.getStatus()) //자신에게 6의 피해

        attackToDefenceCardEffect.applyEffect(playerShieldMutualBattleStatus, monsterAttackSelfBattleStatus)

        assertEquals(10.0,playerShieldMutualBattleStatus.status.get(TargetElementStatus.HP),"플레이어 체력 10")
        assertEquals(9.0,monsterAttackSelfBattleStatus.status.get(TargetElementStatus.HP),"몬스터 체력 9")

    }

    @Test
    fun `attackToDefence - opponentToMutual(상대 VS 상호)`(){

        //플레이어가 상대방어, 몬스터가 상호작용 공격
        val playerOpponentBattleStatus = BattleStatus(playerCardOpponentShield, player.getStatus()) //상대에게 3의 방어
        val monsterMutualBattleStatus = BattleStatus(monsterCardCutMutual, monster.getStatus()) //상대에게 3의 피해, 자신에게 1의 피해

        attackToDefenceCardEffect.applyEffect(playerOpponentBattleStatus, monsterMutualBattleStatus)

        assertEquals(7.0,playerOpponentBattleStatus.status.get(TargetElementStatus.HP),"플레이어 체력 7")
        assertEquals(10.0,monsterMutualBattleStatus.status.get(TargetElementStatus.HP),"몬스터 체력 10")

        val playerOpponentBattleStatus2 = BattleStatus(playerCardOpponentShield, player.getStatus()) //상대에게 3의 방어
        val monsterMutualBattleStatus2 = BattleStatus(monsterCardCutMutual, monster.getStatus()) //상대에게 3의 피해, 자신에게 1의 피해

        attackToDefenceCardEffect.applyEffect(monsterMutualBattleStatus2,playerOpponentBattleStatus2)

        assertEquals(7.0,playerOpponentBattleStatus2.status.get(TargetElementStatus.HP),"플레이어 체력 7")
        assertEquals(10.0,monsterMutualBattleStatus2.status.get(TargetElementStatus.HP),"몬스터 체력 10")

        //몬스터가 상대방어, 플레이어가 상호작용 공격
        val monsterOpponentShield = BattleStatus(monsterCardOpponentShield, monster.getStatus()) //상대에게 4의 방어
        val playerMutualBattleStatus = BattleStatus(playerCardBashMutual, player.getStatus()) //상대에게 7의 피해, 자신에게 2의 피해

        attackToDefenceCardEffect.applyEffect(monsterOpponentShield, playerMutualBattleStatus)

        assertEquals(10.0,playerMutualBattleStatus.status.get(TargetElementStatus.HP),"플레이어 체력 10")
        assertEquals(3.0,monsterOpponentShield.status.get(TargetElementStatus.HP),"몬스터 체력 3")

        val monsterOpponentShield2 = BattleStatus(monsterCardOpponentShield, monster.getStatus()) //상대에게 4의 방어
        val playerMutualBattleStatus2 = BattleStatus(playerCardBashMutual, player.getStatus()) //상대에게 7의 피해, 자신에게 2의 피해

        attackToDefenceCardEffect.applyEffect(playerMutualBattleStatus2,monsterOpponentShield2)

        assertEquals(10.0,playerMutualBattleStatus2.status.get(TargetElementStatus.HP),"플레이어 체력 10")
        assertEquals(3.0,monsterOpponentShield2.status.get(TargetElementStatus.HP),"몬스터 체력 3")

        //플레이어가 상호방어, 몬스터가 공격
        val playerMutualShield = BattleStatus(playerCardMutualShield, player.getStatus()) //자신에게 방어 5, 상대에게 방어 5
        val monsterAttackBattleStatus = BattleStatus(monsterCardCutOpponent, monster.getStatus()) //상대에게 4의 피해

        attackToDefenceCardEffect.applyEffect(playerMutualShield, monsterAttackBattleStatus)

        assertEquals(10.0,playerMutualShield.status.get(TargetElementStatus.HP),"플레이어 체력 10")
        assertEquals(10.0,monsterAttackBattleStatus.status.get(TargetElementStatus.HP),"몬스터 체력 10")

        val playerMutualShield2 = BattleStatus(playerCardMutualShield, player.getStatus()) //자신에게 방어 5, 상대에게 방어 5
        val monsterAttackBattleStatus2 = BattleStatus(monsterCardCutOpponent, monster.getStatus()) //상대에게 4의 피해

        attackToDefenceCardEffect.applyEffect( monsterAttackBattleStatus2,playerMutualShield2)

        assertEquals(10.0,playerMutualShield2.status.get(TargetElementStatus.HP),"플레이어 체력 10")
        assertEquals(10.0,monsterAttackBattleStatus2.status.get(TargetElementStatus.HP),"몬스터 체력 10")

        //몬스터가 상호방어, 플레이어가 공격
        val monsterMutualShield = BattleStatus(monsterCardMutualShield, monster.getStatus()) //자신에게 방어 3, 상대에게 방어 3
        val playerAttackBattleStatus = BattleStatus(playerCardBashOpponent, player.getStatus()) //상대에게 8의 피해

        attackToDefenceCardEffect.applyEffect(monsterMutualShield, playerAttackBattleStatus)

        assertEquals(10.0,playerAttackBattleStatus.status.get(TargetElementStatus.HP),"플레이어 체력 10")
        assertEquals(5.0,monsterMutualShield.status.get(TargetElementStatus.HP),"몬스터 체력 5")

        val monsterMutualShield2 = BattleStatus(monsterCardMutualShield, monster.getStatus()) //자신에게 방어 3, 상대에게 방어 3
        val playerAttackBattleStatus2 = BattleStatus(playerCardBashOpponent, player.getStatus()) //상대에게 8의 피해

        attackToDefenceCardEffect.applyEffect(playerAttackBattleStatus2,monsterMutualShield2)

        assertEquals(10.0,playerAttackBattleStatus2.status.get(TargetElementStatus.HP),"플레이어 체력 10")
        assertEquals(5.0,monsterMutualShield2.status.get(TargetElementStatus.HP),"몬스터 체력 5")

    }

    @Test
    fun `attackToDefence - opponentToOpponent`(){
        val playerBattleStatus = BattleStatus(playerCardBashOpponent, player.getStatus()) //상대에게 8의 피해
        val monsterBattleStatus = BattleStatus(monsterCardOpponentShield, monster.getStatus()) //상대에게 4의 방어

        attackToDefenceCardEffect.applyEffect(playerBattleStatus, monsterBattleStatus)

        assertEquals(10.0,playerBattleStatus.status.get(TargetElementStatus.HP),"플레이어 체력 10")
        assertEquals(2.0,monsterBattleStatus.status.get(TargetElementStatus.HP),"몬스터 체력 2")

        val playerBattleStatus2 = BattleStatus(playerCardBashOpponent, player.getStatus()) //상대에게 8의 피해
        val monsterBattleStatus2 = BattleStatus(monsterCardOpponentShield, monster.getStatus()) //상대에게 4의 방어

        attackToDefenceCardEffect.applyEffect(monsterBattleStatus2,playerBattleStatus2)

        assertEquals(10.0,playerBattleStatus2.status.get(TargetElementStatus.HP),"플레이어 체력 10")
        assertEquals(2.0,monsterBattleStatus2.status.get(TargetElementStatus.HP),"몬스터 체력 2")

    }

    @Test
    fun `attackToDefence - mutualToMutual `(){

        //플레이어 상호실드 - 몬스터 상호공격
        val playerMutualShield = BattleStatus(playerCardMutualShield, player.getStatus()) //자신에게 방어 5, 상대에게 방어 5
        val monsterMutualAttack = BattleStatus(monsterCardCutMutual, monster.getStatus()) //상대에게 3의 피해, 자신에게 1의 피해

        attackToDefenceCardEffect.applyEffect(playerMutualShield, monsterMutualAttack)

        assertEquals(10.0,playerMutualShield.status.get(TargetElementStatus.HP),"플레이어 체력 10")
        assertEquals(10.0,monsterMutualAttack.status.get(TargetElementStatus.HP),"몬스터 체력 10")

        val playerMutualShield2 = BattleStatus(playerCardMutualShield, player.getStatus()) //자신에게 방어 5, 상대에게 방어 5
        val monsterMutualAttack2 = BattleStatus(monsterCardCutMutual, monster.getStatus()) //상대에게 3의 피해, 자신에게 1의 피해

        attackToDefenceCardEffect.applyEffect(monsterMutualAttack2,playerMutualShield2)

        assertEquals(10.0,playerMutualShield2.status.get(TargetElementStatus.HP),"플레이어 체력 10")
        assertEquals(10.0,monsterMutualAttack2.status.get(TargetElementStatus.HP),"몬스터 체력 10")

        //몬스터 상호실드 - 플레이어 상호공격
        val monsterMutualShield = BattleStatus(monsterCardMutualShield, monster.getStatus()) //자신에게 방어 3, 상대에게 방어 3
        val playerMutualAttack = BattleStatus(playerCardBashMutual, player.getStatus()) //상대에게 7의 피해, 자신에게 2의 피해

        attackToDefenceCardEffect.applyEffect(monsterMutualShield, playerMutualAttack)

        assertEquals(10.0,playerMutualAttack.status.get(TargetElementStatus.HP),"플레이어 체력 10")
        assertEquals(6.0,monsterMutualShield.status.get(TargetElementStatus.HP),"몬스터 체력 6")

        val monsterMutualShield2 = BattleStatus(monsterCardMutualShield, monster.getStatus()) //자신에게 방어 3, 상대에게 방어 3
        val playerMutualAttack2 = BattleStatus(playerCardBashMutual, player.getStatus()) //상대에게 7의 피해, 자신에게 2의 피해

        attackToDefenceCardEffect.applyEffect(playerMutualAttack2,monsterMutualShield2)

        assertEquals(10.0,playerMutualAttack2.status.get(TargetElementStatus.HP),"플레이어 체력 10")
        assertEquals(6.0,monsterMutualShield2.status.get(TargetElementStatus.HP),"몬스터 체력 6")




    }


}