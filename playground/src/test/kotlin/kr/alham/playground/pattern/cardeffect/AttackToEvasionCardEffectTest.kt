package kr.alham.playground.pattern.cardeffect

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


class AttackToEvasionCardEffectTest{

    lateinit var attackToAttackCardEffect: AttackToAttackCardEffect
    lateinit var attackToDefenceCardEffect: AttackToDefenceCardEffect
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

        playerCardSelfEvasion = PlayerCard(
            id = 7L,
            name = "방어",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "자신에게 4의 방어력을 줍니다",
            cardTarget = CardTarget.SELF,
            cardType = CardType.EVASION,
            effectSelfNum = 4.0,
            effectSelfStat = TargetElementStatus.HP,
        )

        playerCardOpponentEvasion = PlayerCard(
            id = 8L,
            name="상대방어",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "상대에게 3의 방어력을 줍니다",
            cardTarget = CardTarget.OPPONENT,
            cardType = CardType.EVASION,
            effectOpponentNum = 3.0,
            effectOpponentStat = TargetElementStatus.HP,
        )

        playerCardMutualEvasion = PlayerCard(
            id = 9L,
            name="상호작용 방어",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "나에게 방어5, 상대에게 방어5를 줍니다",
            cardTarget = CardTarget.MUTUAL,
            cardType = CardType.EVASION,
            effectSelfNum = 5.0,
            effectSelfStat = TargetElementStatus.HP,
            effectOpponentNum = 5.0,
            effectOpponentStat = TargetElementStatus.HP,
        )

        monsterCardSelfEvasion = MonsterCard(
            id = 10L,
            name = "몬스터 방어",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "자신에게 5의 방어력을 줍니다",
            cardTarget = CardTarget.SELF,
            cardType = CardType.EVASION,
            effectSelfNum = 5.0,
            effectSelfStat = TargetElementStatus.HP,
        )

        monsterCardOpponentEvasion = MonsterCard(
            id = 11L,
            name = "몬스터 상대방어",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "상대에게 4의 방어력을 줍니다",
            cardTarget = CardTarget.OPPONENT,
            cardType = CardType.EVASION,
            effectOpponentNum = 4.0,
            effectOpponentStat = TargetElementStatus.HP,
        )

        monsterCardOpponentEvasion = MonsterCard(
            id = 12L,
            name = "몬스터 상호작용 방어",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "나에게 방어3, 상대에게 방어3를 줍니다",
            cardTarget = CardTarget.MUTUAL,
            cardType = CardType.EVASION,
            effectSelfNum = 3.0,
            effectSelfStat = TargetElementStatus.HP,
            effectOpponentNum = 3.0,
            effectOpponentStat = TargetElementStatus.HP,
        )



        player = Player()
        monster = Monster()
    }
    
    @Test
    fun `attackToEvasion - selfToSelf`(){




    }


}