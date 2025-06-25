package kr.alham.playground.repository.battle

import kr.alham.playground.common.RedisKeys
import kr.alham.playground.domain.battle.BattleSession
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class BattleSessionRedisRepository(
    private val battleSessionRedisTemplate: RedisTemplate<String, BattleSession>
) {

    fun saveBattleSession(battleSession: BattleSession){
        val key = RedisKeys.battleSession(battleSession.sessionId)
        battleSessionRedisTemplate.opsForValue().set(key, battleSession)
    }

    fun getBattleSession(sessionId: String): BattleSession? {
        val key = RedisKeys.battleSession(sessionId)
        return battleSessionRedisTemplate.opsForValue().get(key)
    }


}