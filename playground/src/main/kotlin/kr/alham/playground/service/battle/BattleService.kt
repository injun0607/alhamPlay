package kr.alham.playground.service.battle

import kr.alham.playground.domain.battle.*
import kr.alham.playground.domain.card.Card
import kr.alham.playground.domain.card.MonsterCard
import kr.alham.playground.domain.card.PlayerCard
import kr.alham.playground.domain.common.TargetElementStatus
import kr.alham.playground.domain.common.TargetElementStatusMap
import kr.alham.playground.domain.enums.BattlePhase
import kr.alham.playground.domain.enums.CardType
import kr.alham.playground.dto.battle.MonsterBattleDTO
import kr.alham.playground.dto.card.CardIdDTO
import kr.alham.playground.system.cardeffect.CardEffectFactory
import kr.alham.playground.service.monster.MonsterService
import kr.alham.playground.service.player.PlayerService
import kr.alham.playground.system.battle.monster.MonsterCardProvider
import org.springframework.stereotype.Service

@Service
class BattleService(
    private val cardEffectFactory: CardEffectFactory,
    private val playerService: PlayerService,
    private val monsterService: MonsterService,
    private val monsterCardProvider: MonsterCardProvider
) {

    fun monsterBattle(monsterBattleDTO: MonsterBattleDTO) {

        /*
        1. 유저 정보 가져오기
        2. 몬스터 정보 가져오기
        3. 유저 카드정보 유효성체크
        몬스터 정보는 DB에서 직접 전달해주기
        몬스터와 전투에선 유저 카드에 대한 처리만 진행?
         */

        val battleState = initMonsterBattleState(monsterBattleDTO)



//        monsterBattlePreparation();
//        monsterBattleEngagement()
//        monsterBattleFinalization()
    }

    fun playerBattle() {
        TODO("Not yet implemented")
    }


    /**
     * 배틀을 위한 상태 초기화 단계
     * 1. 유저 조회
     * 2. 몬스터 조회
     * 3. 유저 카드 정보 유효성 체크
     * 4. 몬스터 카드 정보 조회
     */

    fun initMonsterBattleState(monsterBattleDTO: MonsterBattleDTO): MonsterBattleState {

        val player = playerService.findPlayerById(monsterBattleDTO.playerId)
        val monster = monsterService.findMonsterById(monsterBattleDTO.monsterId)

        val playerId = requireNotNull(player.id)
        val monsterId = requireNotNull(monster.id)

        val playerPreparationCardIdList = monsterBattleDTO.preparationCard

        //플레이어 준비단계 카드 확인
        if (!playerService.hasPlayerCard(playerId, playerPreparationCardIdList)) {
            throw IllegalArgumentException("Player does not have the required cards for battle. Preparation Card List: $playerPreparationCardIdList")
        }

        //플레이어 진행단계 카드 확인
        val playerEngagementCardIdList = monsterBattleDTO.engagementCardList
        if (!playerService.hasPlayerCard(playerId, playerEngagementCardIdList)) {
            throw IllegalArgumentException("Player does not have the required cards for engagement. Engagement Card List: $playerEngagementCardIdList")
        }

        //플레이어 최종단계 카드 확인
        val playerFinalizationCardIdList = monsterBattleDTO.finalizationCard
        if (!playerService.hasPlayerCard(playerId, playerFinalizationCardIdList)) {
            throw IllegalArgumentException("Player does not have the required cards for finalization. Finalization Card List: $playerFinalizationCardIdList")
        }

        val playerAllCardIdDTO = playerPreparationCardIdList + playerEngagementCardIdList + playerFinalizationCardIdList

        val playerCardList = playerService.findPlayerCardInfoListByPlayerIdAndCardIdList(playerId,playerAllCardIdDTO.map{it.id})

        if(playerCardList.isEmpty()){
            throw IllegalArgumentException("Player does not have any valid cards for battle. Player Card List: $playerCardList")
        }

        val filteredPreparationCardList = playerCardList
            .filter { it.card.battlePhase ==  BattlePhase.PREPARATION }
            .map{ it.card }

        val playerPreparationCardList = orderCardList(playerPreparationCardIdList, filteredPreparationCardList)

        val filteredEngagementCardList = playerCardList
            .filter { it.card.battlePhase == BattlePhase.ENGAGEMENT }
            .map{ it.card }

        val playerEngagementCardList = orderCardList(playerEngagementCardIdList, filteredEngagementCardList)

        val filteredFinalizationCardList = playerCardList
            .filter { it.card.battlePhase == BattlePhase.FINALIZATION }
            .map{ it.card }

        val playerFinalizationCardList = orderCardList(playerFinalizationCardIdList, filteredFinalizationCardList)

        //monsterCard 확인
        val monsterCardList = monsterService.findAllMonsterCardInfoByMonsterId(monsterId)

        val monsterPreparationCardList = monsterCardList
            .filter { it.card.battlePhase == BattlePhase.PREPARATION }
            .map { it.card }
            .sortedBy { it.battleOrder }

        val monsterEngagementCardList = monsterCardList
            .filter { it.card.battlePhase == BattlePhase.ENGAGEMENT }
            .map { it.card }
            .sortedBy { it.battleOrder }

        val monsterFinalizationCardList = monsterCardList
            .filter { it.card.battlePhase == BattlePhase.FINALIZATION }
            .map { it.card }
            .sortedBy { it.battleOrder }


        val preparationMonsterBattleStatus = PreparationMonsterBattleStatus(
            playerStatus = player.getStatus(),
            monsterStatus = monster.getStatus(),
            playerCardList = playerPreparationCardList,
            monsterCardList = monsterPreparationCardList
        )

        val engagementMonsterBattleStatus = EngagementMonsterBattleStatus(
            playerStatus = player.getStatus(),
            monsterStatus = monster.getStatus(),
            playerCardList = playerEngagementCardList,
            monsterCardList = monsterEngagementCardList
        )

        val finalizationMonsterBattleStatus = FinalizationBattleStatus(
            playerStatus = player.getStatus(),
            monsterStatus = monster.getStatus(),
            playerCardList = playerFinalizationCardList,
            monsterCardList = monsterFinalizationCardList
        )

        return MonsterBattleState(
            preparationMonsterBattleStatus = preparationMonsterBattleStatus,
            engagementMonsterBattleStatus = engagementMonsterBattleStatus,
            finalizationMonsterBattleStatus = finalizationMonsterBattleStatus,
            monsterCardStrategy = monsterCardProvider.getMonsterProvider(monster.monsterType),
            player = player,
            monster = monster
        )
    }


    /**
     * 몬스터와 BattlePreparation 단계
     */
    fun monsterBattlePreparation(battleState: MonsterBattleState): MonsterBattleState {
        val player = battleState.player
        val monster = battleState.monster

        val playerStatus = battleState.getPreparationSelfStatus()
        val monsterStatus = battleState.getPreparationOpponentStatus()

        val playerCardList = battleState.preparationMonsterBattleStatus.playerCardList
        val monsterCardList = battleState.preparationMonsterBattleStatus.monsterCardList

        val monsterStrategy = battleState.monsterCardStrategy

        for(i in 0 until 1){
            val playerCard = selectPlayerCard(i, playerCardList)
            val monsterCard = monsterStrategy.getMonsterCard(i, monsterStatus, monsterCardList)

            val cardStrategy =  cardEffectFactory.get(Pair(playerCard.cardType, monsterCard.cardType))
            val playerBattleStatus = BattleStatus(playerCard, playerStatus, player)
            val monsterBattleStatus = BattleStatus(monsterCard, monsterStatus, monster)

            cardStrategy.applyEffect(playerBattleStatus, monsterBattleStatus)
        }

        battleState.engagementMonsterBattleStatus.playerStatus = playerStatus
        battleState.engagementMonsterBattleStatus.monsterStatus = monsterStatus

        return battleState
    }

    /**
     * 몬스터와 BattleEngagement 단계
     */
    private fun monsterBattleEngagement(battleState: MonsterBattleState): MonsterBattleState {

        val player = battleState.player
        val monster = battleState.monster

        val playerStatus = battleState.getEngagementSelfStatus()
        val monsterStatus = battleState.getEngagementOpponentStatus()

        val playerCardList = battleState.engagementMonsterBattleStatus.playerCardList
        val monsterCardList = battleState.engagementMonsterBattleStatus.monsterCardList

        //순서대로 진행(카드개수가 안맞는 경우가 생길수있음)
        val monsterStrategy = battleState.monsterCardStrategy
        for (i in 0 until 5) {

            val playerCard = selectPlayerCard(i, playerCardList)
            val monsterCard = monsterStrategy.getMonsterCard(i, monsterStatus, monsterCardList)

            val cardStrategy = cardEffectFactory.get(Pair(playerCard.cardType, monsterCard.cardType))
            val playerBattleStatus = BattleStatus(playerCard, playerStatus, player)
            val monsterBattleStatus = BattleStatus(monsterCard, monsterStatus, monster)
            cardStrategy.applyEffect(playerBattleStatus, monsterBattleStatus)
            if(checkFinalizationPhase(playerBattleStatus,monsterBattleStatus)){
                break
            }
        }

        return battleState
    }

    private fun orderCardList(orderIdList: List<CardIdDTO>, orderCardList: List<Card>): List<Card>{
         return orderIdList.map { cardIdInfo ->
                 orderCardList.first { it.id == cardIdInfo.id }
         }
    }

    private fun selectPlayerCard(idx: Int,cardList: List<Card>): Card{
        if( idx < 0 || idx >= cardList.size) {
            throw IllegalArgumentException("Invalid card index: $idx. Card list size: ${cardList.size}")
        }
        return cardList[idx]
    }

    private fun checkFinalizationPhase(targetOneBattleStatus : BattleStatus, targetTwoBattleStatus: BattleStatus): Boolean{
        return targetOneBattleStatus.status.get(TargetElementStatus.HP) <= 0.0 ||
            targetTwoBattleStatus.status.get(TargetElementStatus.HP) <= 0.0
    }

}