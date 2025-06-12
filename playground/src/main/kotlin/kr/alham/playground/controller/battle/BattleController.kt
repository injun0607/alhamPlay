package kr.alham.playground.controller.battle

import kr.alham.playground.domain.enums.BattleResult
import kr.alham.playground.dto.battle.MonsterBattleDTO
import kr.alham.playground.service.battle.BattleService
import kr.alham.playground.service.drop.DropService
import org.springframework.web.bind.annotation.RestController

@RestController("/api/battle")
class BattleController(
    private val battleService: BattleService,
    private val dropService: DropService
) {

    fun playBattle(monsterBattleDTO: MonsterBattleDTO){
        /**
         * 전투 시작 -> 전투 진행 -> 전투 결과에 따라 결투 생성
         */

        val battleResult = battleService.monsterBattle(monsterBattleDTO)
        if(battleResult == BattleResult.WIN){

        }


    }

}