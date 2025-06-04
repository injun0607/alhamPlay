package kr.alham.playground.service.battle

import kr.alham.playground.dto.battle.MonsterBattleDTO
import kr.alham.playground.service.monster.MonsterService
import kr.alham.playground.service.player.PlayerService
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles


@SpringBootTest
@ActiveProfiles("test")
class BattleServiceTest {

    lateinit var playerService: PlayerService
    lateinit var monsterService: MonsterService


    @Test
    fun playBattle(monsterBattleDTO: MonsterBattleDTO){
        //유저가 해당 카드정보를 가지고 있는지 확인
        val playerId = monsterBattleDTO.playerId



        //



    }


}