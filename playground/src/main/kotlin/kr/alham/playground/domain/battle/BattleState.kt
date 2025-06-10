package kr.alham.playground.domain.battle

import kr.alham.playground.domain.card.Card
import kr.alham.playground.domain.common.TargetElement
import kr.alham.playground.domain.common.TargetElementStatus
import kr.alham.playground.domain.common.TargetElementStatusEmptyMap
import kr.alham.playground.domain.common.TargetElementStatusMap
import kr.alham.playground.domain.enums.BattlePhase
import kr.alham.playground.domain.player.Player
import kr.alham.playground.system.battle.monster.MonsterCardProvider
import kr.alham.playground.system.battle.monster.MonsterCardProviderStrategy

interface BattleState {
    var preparationMonsterBattleStatus: PreparationMonsterBattleStatus
    var engagementMonsterBattleStatus: EngagementMonsterBattleStatus
    var finalizationMonsterBattleStatus: FinalizationBattleStatus
    fun getSelfStatus(battlePhase: BattlePhase): TargetElementStatusMap
    fun getOpponentStatus(battlePhase: BattlePhase): TargetElementStatusMap
    fun getPreparationSelfStatus(): TargetElementStatusMap
    fun getPreparationOpponentStatus(): TargetElementStatusMap
    fun getEngagementSelfStatus(): TargetElementStatusMap
    fun getEngagementOpponentStatus(): TargetElementStatusMap
    fun getFinalizationSelfStatus(): TargetElementStatusMap
    fun getFinalizationOpponentStatus(): TargetElementStatusMap
}



class MonsterBattleState(
    override var preparationMonsterBattleStatus: PreparationMonsterBattleStatus = PreparationMonsterBattleStatus(),
    override var engagementMonsterBattleStatus: EngagementMonsterBattleStatus = EngagementMonsterBattleStatus(),
    override var finalizationMonsterBattleStatus: FinalizationBattleStatus = FinalizationBattleStatus(),
    val monsterCardStrategy: MonsterCardProviderStrategy,
    val player: TargetElement,
    val monster: TargetElement
) : BattleState {

    override fun getSelfStatus(battlePhase: BattlePhase): TargetElementStatusMap {
        return when(battlePhase) {
            BattlePhase.PREPARATION -> preparationMonsterBattleStatus.playerStatus
            BattlePhase.ENGAGEMENT -> engagementMonsterBattleStatus.playerStatus
            BattlePhase.FINALIZATION -> finalizationMonsterBattleStatus.playerStatus
        }
    }

    override fun getOpponentStatus(battlePhase: BattlePhase): TargetElementStatusMap {
        return when(battlePhase){
            BattlePhase.PREPARATION -> preparationMonsterBattleStatus.monsterStatus
            BattlePhase.ENGAGEMENT -> engagementMonsterBattleStatus.monsterStatus
            BattlePhase.FINALIZATION -> finalizationMonsterBattleStatus.monsterStatus
        }
    }

    override fun getPreparationSelfStatus(): TargetElementStatusMap {
        return preparationMonsterBattleStatus.playerStatus
    }

    override fun getPreparationOpponentStatus(): TargetElementStatusMap {
        return preparationMonsterBattleStatus.monsterStatus
    }

    override fun getEngagementSelfStatus(): TargetElementStatusMap {
        return engagementMonsterBattleStatus.playerStatus
    }

    override fun getEngagementOpponentStatus(): TargetElementStatusMap {
        return engagementMonsterBattleStatus.monsterStatus
    }

    override fun getFinalizationSelfStatus(): TargetElementStatusMap {
        return finalizationMonsterBattleStatus.playerStatus
    }

    override fun getFinalizationOpponentStatus(): TargetElementStatusMap {
        return finalizationMonsterBattleStatus.monsterStatus
    }
}
