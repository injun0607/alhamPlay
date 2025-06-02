package kr.alham.playground.common

object RedisKeys{
    fun filedAreaTiles(fieldAreaId: Long): String {
        return "fieldArea:tiles:$fieldAreaId"
    }
}