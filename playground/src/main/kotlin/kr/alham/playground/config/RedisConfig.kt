package kr.alham.playground.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.cache.CacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig {

    @Bean
    fun redisTemplate(redisConnectionFactory: RedisConnectionFactory): RedisTemplate<String,String>{
        val template = RedisTemplate<String,String>()
        template.connectionFactory = redisConnectionFactory
        template.keySerializer = StringRedisSerializer()
        template.valueSerializer = StringRedisSerializer()
        return template
    }

//    @Bean
//    fun redisCacheManager(redisConnectionFactory: RedisConnectionFactory): CacheManager {
//        val redisSerializer = Jackson2JsonRedisSerializer(ObjectMapper().apply {
//            registerModule(KotlinModule.Builder().build())
//            configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
//        },Any().javaClass)
//
//
//        val config = RedisCacheConfiguration.defaultCacheConfig()
//            .serializeValuesWith(
//                RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer)
//            )
//
//        return RedisCacheManager.builder(redisConnectionFactory)
//            .cacheDefaults(config)
//            .build()
//    }


}