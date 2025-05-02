package kr.alham.playground.service.battle

import kr.alham.playground.domain.battle.BattleState
import kr.alham.playground.domain.battle.MonsterBattleState
import kr.alham.playground.domain.battle.PreparationMonsterBattleStatus
import kr.alham.playground.domain.card.PlayerCard
import kr.alham.playground.domain.enums.BattlePhase
import kr.alham.playground.dto.battle.MonsterBattleDTO
import kr.alham.playground.pattern.cardeffect.CardEffectFactory
import kr.alham.playground.repository.card.MonsterCardRepository
import kr.alham.playground.repository.monster.MonsterRepository
import kr.alham.playground.repository.card.PlayerCardRepository
import kr.alham.playground.repository.player.PlayerRepository
import org.springframework.stereotype.Service

@Service
class BattleService(
    private val cardEffectFactory: CardEffectFactory,
    private val playerCardRepository: PlayerCardRepository,
    private val monsterCardRepository: MonsterCardRepository,
    private val playerRepository: PlayerRepository,
    private val monsterRepository: MonsterRepository
) {

    fun monsterBattle(monsterBattleDTO: MonsterBattleDTO){



        /*
        1. 유저 정보 가져오기
        2. 몬스터 정보 가져오기
        3. 유저 카드정보 유효성체크
        몬스터 정보는 DB에서 직접 전달해주기
        몬스터와 전투에선 유저 카드에 대한 처리만 진행?
         */


        TODO("BattleState를 받아서 사용하는 방식")

//        monsterBattlePreparation();
//        monsterBattleEngagement()
//        monsterBattleFinalization()
    }

    fun playerBattle(){
        TODO("Not yet implemented")
    }


    /**
     * 몬스터와 BattlePreparation 단계
     */

    private fun initMonsterBattleState(monsterBattleDTO: MonsterBattleDTO){
        //TODO - DB조회하기
        val player = playerRepository.findById(monsterBattleDTO.playerId).orElseThrow {
            IllegalArgumentException("Player not found")
        }
        val playerId = requireNotNull(player.id) { "Player ID is null" }

        val monster = monsterRepository.findById(monsterBattleDTO.monsterId).orElseThrow {
            IllegalArgumentException("Monster not found")
        }

        val monsterId = requireNotNull(monster.id) { "Monster ID is null" }

        val battleState = MonsterBattleState(
            preparationMonsterBattleStatus = PreparationMonsterBattleStatus(
                playerStatus = player.getStatus(),
                monsterStatus = monster.getStatus()
            )
        )

        val monsterCardList = monsterCardRepository.findCardListByMonsterIdAndCardIdIn(monsterId)



        //preparationCard정보 가져오기
        val preparationCardIdList = monsterBattleDTO.preparationCard.stream().map { it.id }.toList()
        if(playerCardRepository.validPlayerCardInfo(preparationCardIdList, preparationCardIdList.size, playerId)){
            throw IllegalArgumentException("유효하지 않은 카드입니다." )
        }
        val preparationPlayerCardList = playerCardRepository.findCardListByPlayerIdAndCardIdIn(playerId, preparationCardIdList)


        //순서정렬해야하는데 -> 이거 preparationCardIdlist에 있는 순서로 정렬해야함
        battleState.preparationMonsterBattleStatus.playerCardList = preparationCardIdList.map { cardId ->
            preparationPlayerCardList.first { it.card.id == cardId }.card
        }

        val preparationMonsterBattle = monsterCardList
            .filter{it.card.battlePhase == BattlePhase.PREPARATION}
            .map{ it.card }
            .sortedBy { it.battleOrder }







        //engagementCardList정보 가져오기
        val engagementCardIdList = monsterBattleDTO.engagementCardList.stream().map { it.id }.toList()
        if(playerCardRepository.validPlayerCardInfo(engagementCardIdList,engagementCardIdList.size, playerId)){
            throw IllegalArgumentException("유효하지 않은 카드입니다." )
        }
        val engagementCardList = cardRepository.findCardListByIdIn(engagementCardIdList)

        //finalizationCard정보 가져오기
        val finalizationCardIdList =  monsterBattleDTO.finalizationCard.stream().map { it.id }.toList()
        if(playerCardRepository.validPlayerCardInfo(finalizationCardIdList, finalizationCardIdList.size, playerId)){
            throw IllegalArgumentException("유효하지 않은 카드입니다." )
        }

        val finalizationCardList = cardRepository.findCardListByIdIn(finalizationCardIdList)



    }
    private fun monsterBattlePreparation(monsterBattleState: MonsterBattleState){
        val playerStatus = monsterBattleState.getPreparationPlayerStatus()
        val monsterStatus = monsterBattleState.getPreparationMonsterStatus()


    }

    private fun monsterBattleEngagement(){
        TODO("Not yet implemented")
    }

    private fun monsterBattleFinalization(){
        TODO("Not yet implemented")
    }

    private fun preparationAction(battleState: BattleState): BattleState{



        return battleState
    }


}