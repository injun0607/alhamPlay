package kr.alham.playground.domain.battle

import kr.alham.playground.domain.card.Card
import kr.alham.playground.domain.common.TargetElement
import kr.alham.playground.domain.common.TargetElementStatus
import kr.alham.playground.domain.common.TargetElementStatusEmptyMap
import kr.alham.playground.domain.common.TargetElementStatusMap
import kr.alham.playground.domain.enums.BattlePhase

interface BattleState {
    var preparationMonsterBattleStatus: PreparationMonsterBattleStatus
    var engagementMonsterBattleStatus: EngagementMonsterBattleStatus
    var finalizationMonsterBattleStatus: FinalizationBattleStatus
    fun getStatus(card: Card)
}



class MonsterBattleState(
    override var preparationMonsterBattleStatus: PreparationMonsterBattleStatus = PreparationMonsterBattleStatus(),
    override var engagementMonsterBattleStatus: EngagementMonsterBattleStatus = EngagementMonsterBattleStatus(),
    override var finalizationMonsterBattleStatus: FinalizationBattleStatus = FinalizationBattleStatus(),
) : BattleState {

    override fun getStatus(card: Card) {

    }
    fun getPreparationPlayerStatus(): TargetElementStatusMap{
        return preparationMonsterBattleStatus.playerStatus
    }

    fun getPreparationMonsterStatus(): TargetElementStatusMap{
        return preparationMonsterBattleStatus.monsterStatus
    }


    fun getEngagementPlayerStatus(): List<TargetElementStatusMap>{
        return engagementMonsterBattleStatus.playerStatus
    }

    fun getEngagementMonsterStatus(): List<TargetElementStatusMap>{
        return engagementMonsterBattleStatus.monsterStatus
    }


    fun getFinalizationPlayerStatus(): TargetElementStatusMap{
        return finalizationMonsterBattleStatus.playerStatus
    }

    fun getFinalizationMonsterStatus(): TargetElementStatusMap{
        return finalizationMonsterBattleStatus.monsterStatus
    }



}
