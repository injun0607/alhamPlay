package kr.alham.playground.service.battle

import kr.alham.playground.domain.battle.BattleState
import kr.alham.playground.domain.battle.MonsterBattleState
import kr.alham.playground.domain.battle.PreparationMonsterBattleStatus
import kr.alham.playground.domain.enums.BattlePhase
import kr.alham.playground.domain.player.PlayerCardInfo
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

    public fun initMonsterBattleState(monsterBattleDTO: MonsterBattleDTO): BattleState{
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


        /*
        1. preparationStatus 세팅
         */

        //preparationCard정보 가져오기
        val preparationCardIdList = monsterBattleDTO.preparationCard.stream().map { it.id }.toList()
        if(!playerCardRepository.validPlayerCardInfo(preparationCardIdList, preparationCardIdList.size, playerId)){
            throw IllegalArgumentException("유효하지 않은 카드입니다." )
        }
        val preparationPlayerCardInfoList: List<PlayerCardInfo> = playerCardRepository.findCardListByPlayerIdAndCardIdIn(playerId, preparationCardIdList)

        preparationPlayerCardInfoList.map{it.card}

        //순서정렬해야하는데 -> 이거 preparationCardIdlist에 있는 순서로 정렬해야함
        battleState.preparationMonsterBattleStatus.playerCardList = preparationCardIdList.map { cardId ->
            preparationPlayerCardInfoList.first { it.card.id == cardId }.card
        }

        battleState.preparationMonsterBattleStatus.monsterCardList = monsterCardList
            .filter{it.card.battlePhase == BattlePhase.PREPARATION}
            .map{ it.card }
            .sortedBy { it.battleOrder }




        /*
        2. engagementStatus 세팅
         */
        //engagementCardList정보 가져오기
        val engagementCardIdList = monsterBattleDTO.engagementCardList.stream().map { it.id }.toList()
        if(!playerCardRepository.validPlayerCardInfo(engagementCardIdList,engagementCardIdList.size, playerId)){
            throw IllegalArgumentException("유효하지 않은 카드입니다." )
        }
        val engagementPlayerCardList: List<PlayerCardInfo> = playerCardRepository.findCardListByPlayerIdAndCardIdIn(playerId,engagementCardIdList)

        //순서대로 정룔
        battleState.engagementMonsterBattleStatus.playerCardList = engagementCardIdList.map{ cardId ->
            engagementPlayerCardList.first { it.card.id == cardId }.card
        }

        battleState.engagementMonsterBattleStatus.monsterCardList = monsterCardList
            .filter{it.card.battlePhase == BattlePhase.ENGAGEMENT}
            .map{ it.card }
            .sortedBy { it.battleOrder }


        /*
        3. finalizationStatus 세팅
         */
        //finalizationCard정보 가져오기
        val finalizationCardIdList =  monsterBattleDTO.finalizationCard.stream().map { it.id }.toList()
        if(!playerCardRepository.validPlayerCardInfo(finalizationCardIdList, finalizationCardIdList.size, playerId)){
            throw IllegalArgumentException("유효하지 않은 카드입니다." )
        }

        val finalizationPlayerCardList: List<PlayerCardInfo> = playerCardRepository.findCardListByPlayerIdAndCardIdIn(playerId,finalizationCardIdList)

        //순서대로 정렬
        battleState.finalizationMonsterBattleStatus.playerCardList = finalizationCardIdList.map{ cardId ->
            finalizationPlayerCardList.first { it.card.id == cardId }.card
        }

        battleState.finalizationMonsterBattleStatus.monsterCardList = monsterCardList
            .filter{it.card.battlePhase == BattlePhase.FINALIZATION}
            .map{ it.card }
            .sortedBy { it.battleOrder }

        return battleState
    }

    /**
     * 몬스터와 BattlePreparation 단계
     */
    fun monsterBattlePreparation(battleState: BattleState): BattleState{
        val playerStatus = battleState.getPreparationSelfStatus()
        val monsterStatus = battleState.getPreparationOpponentStatus()

        val playerCardList = battleState.preparationMonsterBattleStatus.playerCardList
        val monsterCardList = battleState.preparationMonsterBattleStatus.monsterCardList

        //플레이어 먼저 -> 몬스터 순서
        playerCardList.forEach{ card ->
            cardEffectFactory.get(card.cardType).applyEffect(card,playerStatus, monsterStatus)
        }

        monsterCardList.forEach { card ->
            cardEffectFactory.get(card.cardType).applyEffect(card, monsterStatus, playerStatus)
        }

        return battleState
    }

    /**
     * 몬스터와 BattleEngagement 단계
     */
    private fun monsterBattleEngagement(battleState: BattleState): BattleState{
        val playerStatue = battleState.getEngagementSelfStatus()




        TODO("Not yet implemented")
    }

    /**
     * 몬스터와 BattleFinalization 단계
     */
    private fun monsterBattleFinalization(monsterBattleState: MonsterBattleState){
        TODO("Not yet implemented")
    }

    private fun preparationAction(battleState: BattleState): BattleState{



        return battleState
    }


}