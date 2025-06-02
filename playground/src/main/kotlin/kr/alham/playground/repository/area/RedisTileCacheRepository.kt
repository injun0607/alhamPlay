package kr.alham.playground.repository.area

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import kr.alham.playground.common.RedisKeys
import kr.alham.playground.domain.area.Tile
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.time.Duration

@Repository
class RedisTileCacheRepository(
    private val redisTemplate: RedisTemplate<String, String>,
    private val objectMapper: ObjectMapper
) {
    fun saveTiles(fieldAreaId: Long,tiles: List<Tile>) {
        val key = RedisKeys.filedAreaTiles(fieldAreaId)
        val json = objectMapper.writeValueAsString(tiles)
        redisTemplate.opsForValue().set(key,json, Duration.ofHours(24))
    }

    fun getTilesByFieldAreaId(fieldAreaId: Long): List<Tile>? {
        val key = RedisKeys.filedAreaTiles(fieldAreaId)
        val json = redisTemplate.opsForValue().get(key) ?: return null
        return objectMapper.readValue(json, object : TypeReference<List<Tile>>() {})
    }

    /**
     * val key = "fieldArea:tiles:$fieldAreaId"
     *         val json = objectMapper.writeValueAsString(tiles)
     *         redisTemplate.opsForValue().set(key, json, Duration.ofHours(24))
     */
}