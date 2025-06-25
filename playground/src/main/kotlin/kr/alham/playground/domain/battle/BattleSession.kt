package kr.alham.playground.domain.battle

import java.time.LocalDateTime

data class BattleSession(
    val sessionId: String,
    val playerId: Long,
    val monsterId: Long,
    val monsterName: String,
    val status: BattleSessionStatus = BattleSessionStatus.READY,
    val createdAt: LocalDateTime = LocalDateTime.now(),
){

    enum class BattleSessionStatus {
        READY,
        CARD_SELECTION,
        IN_PROGRESS,
        FINISHED,
    }
}

