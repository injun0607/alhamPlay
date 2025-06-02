package kr.alham.playground.repository.area

import com.fasterxml.jackson.databind.ObjectMapper
import kr.alham.playground.system.area.AreaSystem
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate

@SpringBootTest
class RedisTileCacheRepositoryTest{

    lateinit var redisTileCacheRepository: RedisTileCacheRepository
    lateinit var objectMapper: ObjectMapper

    @Autowired
    lateinit var areaSystem: AreaSystem

    @Autowired
    lateinit var redisTemplate: RedisTemplate<String, String>

    @BeforeEach
    fun setup() {
        // 초기화 로직
        objectMapper = ObjectMapper()
        redisTileCacheRepository = RedisTileCacheRepository(redisTemplate, objectMapper)
    }


    @Test
    fun saveAndGetTilesTest() {
        // 테스트 로직

        val tiles = areaSystem.initTiles()
        val fieldAreId = 1L

        val savedTiles = redisTileCacheRepository.saveTiles(fieldAreId, tiles)
        val retrievedTiles = redisTileCacheRepository.getTilesByFieldAreaId(fieldAreId)
        assertEquals(25, retrievedTiles?.size)

    }


}