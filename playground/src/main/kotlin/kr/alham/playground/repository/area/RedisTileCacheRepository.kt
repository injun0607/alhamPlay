package kr.alham.playground.repository.area

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import kr.alham.playground.common.RedisKeys
import kr.alham.playground.domain.area.FieldType
import kr.alham.playground.domain.area.Tile
import kr.alham.playground.dto.field.DailyTileInfo
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.time.Duration

@Repository
class RedisTileCacheRepository(
    private val redisTemplate: RedisTemplate<String, String>,
    private val objectMapper: ObjectMapper
) {

    fun savePlayerSelectedField(playerId: Long, fieldType: FieldType) {
        val key = RedisKeys.playerSelectedField(playerId)
        return redisTemplate.opsForValue().set(key, objectMapper.writeValueAsString(fieldType), Duration.ofHours(24))
    }

    fun getPlayerSelectedField(playerId: Long): FieldType? {
        val key = RedisKeys.playerSelectedField(playerId)
        val selectedField = redisTemplate.opsForValue().get(key) ?: return null
        return objectMapper.readValue(selectedField, object : TypeReference<FieldType>() {})
    }

    fun saveTiles(fieldAreaId: Long,tiles: List<Tile>) {
        val key = RedisKeys.fieldAreaTiles(fieldAreaId)
        val json = objectMapper.writeValueAsString(tiles)
        redisTemplate.opsForValue().set(key,json, Duration.ofHours(24))
    }

    fun getTilesByFieldAreaId(fieldAreaId: Long): List<Tile>? {
        val key = RedisKeys.fieldAreaTiles(fieldAreaId)
        val json = redisTemplate.opsForValue().get(key) ?: return null
        return objectMapper.readValue(json, object : TypeReference<List<Tile>>() {})
    }

    fun savePlayerSelectedTile(playerId: Long, dailyTileInfo: DailyTileInfo){
        val key = RedisKeys.playerSelectedTile(playerId)
        val json = objectMapper.writeValueAsString(dailyTileInfo)
        redisTemplate.opsForValue().set(key, json, Duration.ofHours(24))
    }

    fun getPlayerSelectedTile(playerId: Long): DailyTileInfo? {
        val key = RedisKeys.playerSelectedTile(playerId)
        val json = redisTemplate.opsForValue().get(key) ?: return null
        return objectMapper.readValue(json, object : TypeReference<DailyTileInfo>() {})
    }

}