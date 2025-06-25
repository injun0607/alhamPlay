package kr.alham.playground.repository.battle

import kr.alham.playground.domain.battle.BattleSession
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.random.Random

@SpringBootTest
class BattleSessionRedisRepositoryTest{


    @Autowired
    lateinit var battleSessionRedisRepository: BattleSessionRedisRepository

    @Test
    fun `saveAndGetBattleSessionRedisTest`(){

        val battleSession = BattleSession(
            sessionId = "testSession",
            playerId = 1L,
            monsterId = 2L,
            monsterName = "Test Monster",
        )

        battleSessionRedisRepository.saveBattleSession(battleSession)

        val retrievedSession = battleSessionRedisRepository.getBattleSession(battleSession.sessionId)

        assertEquals(battleSession.sessionId, retrievedSession?.sessionId)
        assertEquals(battleSession.playerId, retrievedSession?.playerId)
        assertEquals(battleSession.monsterId, retrievedSession?.monsterId)
        assertEquals(battleSession.monsterName, retrievedSession?.monsterName)

    }


}