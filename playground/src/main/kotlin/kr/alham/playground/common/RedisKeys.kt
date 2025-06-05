package kr.alham.playground.common

object RedisKeys{
    fun fieldAreaTiles(fieldAreaId: Long): String {
        return "fieldArea:tiles:$fieldAreaId"
    }
}