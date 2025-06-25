package kr.alham.playground.dto.battle

import kr.alham.playground.domain.battle.BattleSession

data class BattleSessionDTO(
    val sessionId: String,
    val playerId: Long,
    val monsterId: Long,
    val monsterName: String,
) {
    companion object {
        fun fromEntity(session: BattleSession): BattleSessionDTO {
            return BattleSessionDTO(
                sessionId = session.sessionId,
                playerId = session.playerId,
                monsterId = session.monsterId,
                monsterName = session.monsterName
            )
        }
    }
}