package kr.alham.playground.service.battle

import kr.alham.playground.domain.common.TargetElementStatus
import kr.alham.playground.domain.enums.BattlePhase
import kr.alham.playground.domain.enums.CardTarget
import kr.alham.playground.domain.enums.CardType
import kr.alham.playground.domain.monster.Monster
import kr.alham.playground.domain.player.Player
import kr.alham.playground.dto.card.CardDTO
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BattleServiceTest{
    @Test
    fun testMonsterBattle(){
        // Given
        //Preparation
        val cardConcentrate = CardDTO(
            name ="집중",
            battlePhase = BattlePhase.PREPARATION,
            description = "민첩 + 1",
            cardTarget = CardTarget.SELF,
            cardType = CardType.BUFF,
            effectSelfNum = 1,
            effectSelfStat = TargetElementStatus.DEX,
        )

        var cardBash = CardDTO(
            name = "강타",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "상대방에게 5의 피해를 줍니다.",
            cost = 2,
            cardTarget = CardTarget.OPPONENT,
            effectOpponentNum = 5,
            effectOpponentStat = TargetElementStatus.HP,
        )

        var cardCut = CardDTO(
            name = "베기",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "상대방에게 6의 피해를 줍니다.",
            cost = 2,
            cardTarget = CardTarget.OPPONENT,
            effectOpponentNum = 6,
            effectOpponentStat = TargetElementStatus.HP,
        )

        var cardShield = CardDTO(
            name = "방패막기",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "10데미지를 막습니다.",
            cost = 2,
            cardTarget = CardTarget.OPPONENT,
            cardType = CardType.DEFENCE,
            effectSelfNum = 10,
            effectSelfStat = TargetElementStatus.HP,
        )

        var cardEvasion = CardDTO(
            name = "회피",
            battlePhase = BattlePhase.FINALIZATION,
            description = "상대방의 마지막 공격을 회피합니다.",
            cardTarget = CardTarget.SELF,
            cardType = CardType.EVASION,
            effectSelfNum = 10,
            effectSelfStat = TargetElementStatus.HP,
        )

        var player = Player(
            name ="인준스"
        )

        var monster = Monster(
            name = "슬라임"
        )
        //


        // When
        // Then

        // Arrange
        BattleService().playerBattle()


        // Act


        // Assert
        // Add assertions to verify the expected behavior
    }


    @Test
    fun testMonsterBattlePreparation(){
        // Given
        //Preparation
        val cardConcentrate = CardDTO(
            name ="집중",
            battlePhase = BattlePhase.PREPARATION,
            description = "민첩 + 1",
            cardTarget = CardTarget.SELF,
            cardType = CardType.BUFF,
            effectSelfNum = 1,
            effectSelfStat = TargetElementStatus.DEX,
        )

        var cardBash = CardDTO(
            name = "강타",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "상대방에게 5의 피해를 줍니다.",
            cost = 2,
            cardTarget = CardTarget.OPPONENT,
            effectOpponentNum = 5,
            effectOpponentStat = TargetElementStatus.HP,
        )

        var cardCut = CardDTO(
            name = "베기",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "상대방에게 6의 피해를 줍니다.",
            cost = 2,
            cardTarget = CardTarget.OPPONENT,
            effectOpponentNum = 6,
            effectOpponentStat = TargetElementStatus.HP,
        )

        var cardShield = CardDTO(
            name = "방패막기",
            battlePhase = BattlePhase.ENGAGEMENT,
            description = "10데미지를 막습니다.",
            cost = 2,
            cardTarget = CardTarget.OPPONENT,
            cardType = CardType.DEFENCE,
            effectSelfNum = 10,
            effectSelfStat = TargetElementStatus.HP,
        )

        var cardEvasion = CardDTO(
            name = "회피",
            battlePhase = BattlePhase.FINALIZATION,
            description = "상대방의 마지막 공격을 회피합니다.",
            cardTarget = CardTarget.SELF,
            cardType = CardType.EVASION,
            effectSelfNum = 10,
            effectSelfStat = TargetElementStatus.HP,
        )

        var player = Player(
            name ="인준스"
        )

        var monster = Monster(
            name = "슬라임"
        )
        // When

        //userStat을 받아서 -> 해당 Stat을 상승시키면되나?
        val userStat = player.getStatus()



        // Then
        assertEquals(userStat[cardConcentrate.effectSelfStat], 2.0)

    }
}