package kr.alham.playground.service.battle

import kr.alham.playground.domain.common.TargetElement
import kr.alham.playground.domain.enums.CardTarget
import kr.alham.playground.dto.battle.MonsterBattleDTO
import kr.alham.playground.dto.card.CardDTO
import org.springframework.stereotype.Service

@Service
class BattleService {

    fun monsterBattle(){
//        monsterBattlePreparation(1L,1L);
//        monsterBattleEngagement()
//        monsterBattleFinalization()
    }

    fun playerBattle(){
        TODO("Not yet implemented")
    }


    private fun monsterBattlePreparation(monsterBattleDTO: MonsterBattleDTO){
        TODO("Not yet implemented")
    }

    private fun monsterBattleEngagement(){
        TODO("Not yet implemented")
    }

    private fun monsterBattleFinalization(){
        TODO("Not yet implemented")
    }

    private fun preparationAction(cardDTO: CardDTO){

    }

    private fun effectAction(selfTarget: TargetElement, opponentTarget: TargetElement, cardDTO: CardDTO){

        when(cardDTO.cardTarget){
            CardTarget.SELF -> {

            }
            CardTarget.OPPONENT -> {

            }
            CardTarget.MUTUAL -> {

            }
        }
    }

}