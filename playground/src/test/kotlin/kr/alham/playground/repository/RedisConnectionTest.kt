package kr.alham.playground.repository

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate
import kotlin.test.Test
import kotlin.test.assertEquals

@SpringBootTest
class RedisConnectionTest {

    @Autowired
    lateinit var redisTemplate: RedisTemplate<String, String>


    @Test
    fun redisConnectTest(){
        val key: String = "test:key"
        val value: String = "Hello, Redis!"

        redisTemplate.opsForValue().set(key, value)
        val retrievedValue: String? = redisTemplate.opsForValue().get(key)

        println("Retrieved value from Redis: $retrievedValue")
        assertEquals(value, retrievedValue)
    }




}