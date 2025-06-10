package kr.alham.playground.system.battle.monster

import kr.alham.playground.domain.monster.MonsterType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MonsterCardProviderTest{

    @Autowired
    lateinit var monsterCardProvider: MonsterCardProvider

    @Test
    fun monsterCardProviderTest(){
        val normalProvider = monsterCardProvider.getMonsterProvider(MonsterType.NORMAL)
        assertNotNull(normalProvider)
        assertEquals(MonsterType.NORMAL, normalProvider.supportedType())

        val bossProvider = monsterCardProvider.getMonsterProvider(MonsterType.BOSS)
        assertNotNull(bossProvider)
        assertEquals(MonsterType.BOSS, bossProvider.supportedType())

        assertThrows(IllegalArgumentException::class.java) {
            monsterCardProvider.getMonsterProvider(MonsterType.valueOf("UNKNOWN"))
        }


    }


}