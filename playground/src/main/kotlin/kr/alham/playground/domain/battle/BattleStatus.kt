package kr.alham.playground.domain.battle

import kr.alham.playground.domain.common.DurationStatus
import kr.alham.playground.domain.common.TargetElementStatusMap
import kr.alham.playground.domain.enums.BattlePhase
import kr.alham.playground.domain.card.Card
import kr.alham.playground.domain.common.TargetElement


class BattleStatus(
    val card: Card,
    val status: TargetElementStatusMap,
    val target: TargetElement
){}




class PreparationMonsterBattleStatus(
    val battlePhase: BattlePhase = BattlePhase.PREPARATION,
    var playerStatus: TargetElementStatusMap = TargetElementStatusMap(),
    var monsterStatus: TargetElementStatusMap = TargetElementStatusMap(),
    var playerCardList: List<Card> = listOf(),
    var monsterCardList: List<Card> = listOf(),
){

}

class EngagementMonsterBattleStatus(
    val battlePhase: BattlePhase = BattlePhase.ENGAGEMENT,
    var playerStatus: TargetElementStatusMap = TargetElementStatusMap(),
    var monsterStatus: TargetElementStatusMap = TargetElementStatusMap(),
    val playerDurationStatus: List<DurationStatus> = listOf(),
    val monsterDurationStatus: List<DurationStatus> = listOf(),
    var playerCardList: List<Card> = listOf(),
    var monsterCardList: List<Card> = listOf(),
){

    fun isExistPlayerDurationStatus(): Boolean {
        return playerDurationStatus.isNotEmpty()
    }

    fun isExistMonsterDurationStatus(): Boolean {
        return monsterDurationStatus.isNotEmpty()
    }

}

class FinalizationBattleStatus(
    val battlePhase: BattlePhase = BattlePhase.FINALIZATION,
    var playerStatus: TargetElementStatusMap = TargetElementStatusMap(),
    var monsterStatus: TargetElementStatusMap = TargetElementStatusMap(),
    val playerDurationStatus: List<DurationStatus> = listOf(),
    val monsterDurationStatus: List<DurationStatus> = listOf(),
    var playerCardList: List<Card> = listOf(),
    var monsterCardList: List<Card> = listOf(),
){

    fun isExistPlayerDurationStatus(): Boolean {
        return playerDurationStatus.isNotEmpty()
    }

    fun isExistMonsterDurationStatus(): Boolean {
        return monsterDurationStatus.isNotEmpty()
    }

}