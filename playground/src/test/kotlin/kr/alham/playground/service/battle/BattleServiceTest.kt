package kr.alham.playground.service.battle

import kr.alham.playground.domain.battle.MonsterBattleState
import kr.alham.playground.domain.common.TargetElementStatus
import kr.alham.playground.domain.enums.BattlePhase
import kr.alham.playground.domain.enums.CardTarget
import kr.alham.playground.domain.enums.CardType
import kr.alham.playground.domain.monster.Monster
import kr.alham.playground.domain.player.Player
import kr.alham.playground.dto.player.PlayerDTO
import kr.alham.playground.dto.card.CardDTO
import kr.alham.playground.dto.monster.MonsterDTO
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class BattleServiceTest{


//    lateinit var battleService: BattleService

    lateinit var cardConcentrate: CardDTO;
    lateinit var cardBash: CardDTO;
    lateinit var cardCut: CardDTO;
    lateinit var cardShield: CardDTO;
    lateinit var cardEvasion: CardDTO;
    lateinit var player: PlayerDTO;
    lateinit var monster: MonsterDTO;
    @BeforeEach
    fun setUp() {
         cardConcentrate = CardDTO(
            name ="집중",
            battlePhase = BattlePhase.PREPARATION,
            description = "민첩 + 1",
            cardTarget = CardTarget.SELF,
            cardType = CardType.BUFF,
            effectSelfNum = 1,
            effectSelfStat = TargetElementStatus.DEX,
        )

        cardBash = CardDTO(
            name = "강타",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "상대방에게 5의 피해를 줍니다.",
            cost = 2,
            cardTarget = CardTarget.OPPONENT,
            effectOpponentNum = 5,
            effectOpponentStat = TargetElementStatus.HP,
        )

        cardCut = CardDTO(
            name = "베기",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "상대방에게 6의 피해를 줍니다.",
            cost = 2,
            cardTarget = CardTarget.OPPONENT,
            effectOpponentNum = 6,
            effectOpponentStat = TargetElementStatus.HP,
        )

        cardShield = CardDTO(
            name = "방패막기",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "10데미지를 막습니다.",
            cost = 2,
            cardTarget = CardTarget.OPPONENT,
            cardType = CardType.DEFENCE,
            effectSelfNum = 10,
            effectSelfStat = TargetElementStatus.HP,
        )

        cardEvasion = CardDTO(
            name = "회피",
            battlePhase = BattlePhase.FINALIZATION,
            description = "상대방의 마지막 공격을 회피합니다.",
            cardTarget = CardTarget.SELF,
            cardType = CardType.EVASION,
            effectSelfNum = 10,
            effectSelfStat = TargetElementStatus.HP,
        )

        player = PlayerDTO(name = "인준")
        monster = MonsterDTO(name="슬라임")


    }

    @Test
    fun testMonsterBattle(){
        // Given
        //Preparation

        // When
        // Then

        // Arrange


        // Act


        // Assert
        // Add assertions to verify the expected behavior
    }


    @Test
    fun testMonsterBattlePreparation(){
        // Given
        //Preparation
        // When






        // Then




        //userStat을 받아서 -> 해당 Stat을 상승시키면되나?
        val userStat = player.getStatus()




        // Then
//        assertEquals(userStat[cardConcentrate.effectSelfStat], 2.0)

    }
}