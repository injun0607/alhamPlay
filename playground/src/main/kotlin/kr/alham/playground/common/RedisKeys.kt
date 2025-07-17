package kr.alham.playground.common

object RedisKeys{

    fun playerSelectedField(playerId: Long): String {
        return "player:$playerId:selectedField"
    }

    fun fieldAreaTiles(fieldAreaId: Long): String {
        return "fieldArea:tiles:$fieldAreaId"
    }

    fun battleSession(sessionId: String): String {
        return "battleSession:$sessionId"
    }

    fun playerSelectedTile(playerId: Long): String {
        return "player:$playerId:selectedTile"
    }
}