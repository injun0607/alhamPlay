package kr.alham.playground.domain.battle

import kr.alham.playground.domain.common.TargetElement
import kr.alham.playground.domain.common.TargetElementStatus
import kr.alham.playground.domain.common.TargetElementStatusEmptyMap
import kr.alham.playground.domain.common.TargetElementStatusMap
import kr.alham.playground.domain.enums.BattlePhase

interface BattleState{
    var selfTargetStatus: TargetElementStatusMap
    var opponentTargetStatus: TargetElementStatusMap
    val preparationSelfStatus: TargetElementStatusEmptyMap
    val engagementSelfStatus: TargetElementStatusEmptyMap
    val finalizationSelfStatus: TargetElementStatusEmptyMap
    val preparationOpponentStatus: TargetElementStatusEmptyMap
    val engagementOpponentStatus: TargetElementStatusEmptyMap
    val finalizationOpponentStatus: TargetElementStatusEmptyMap

    fun getSelfTargetStatusValue(targetElementStatus: TargetElementStatus): Double
    fun getOpponentTargetStatusValue(targetElementStatus: TargetElementStatus): Double


    fun isExistPreparationSelfStatus(): Boolean
    fun isExistEngagementSelfStatus(): Boolean
    fun isExistFinalizationSelfStatus(): Boolean
    fun isExistPreparationOpponentStatus(): Boolean
    fun isExistEngagementOpponentStatus(): Boolean
    fun isExistFinalizationOpponentStatus(): Boolean

}

class MonsterBattleState(
    override var selfTargetStatus: TargetElementStatusMap,
    override var opponentTargetStatus: TargetElementStatusMap,
    override var preparationSelfStatus: TargetElementStatusEmptyMap = TargetElementStatusEmptyMap(),
    override var engagementSelfStatus: TargetElementStatusEmptyMap = TargetElementStatusEmptyMap(),
    override var finalizationSelfStatus: TargetElementStatusEmptyMap = TargetElementStatusEmptyMap(),
    override var preparationOpponentStatus: TargetElementStatusEmptyMap = TargetElementStatusEmptyMap(),
    override var engagementOpponentStatus: TargetElementStatusEmptyMap = TargetElementStatusEmptyMap(),
    override var finalizationOpponentStatus: TargetElementStatusEmptyMap = TargetElementStatusEmptyMap(),
) : BattleState {

    override fun getSelfTargetStatusValue(targetElementStatus: TargetElementStatus): Double {
        return selfTargetStatus.get(targetElementStatus)
    }

    override fun getOpponentTargetStatusValue(targetElementStatus: TargetElementStatus): Double {
        return opponentTargetStatus.get(targetElementStatus)
    }

    override fun isExistPreparationSelfStatus(): Boolean {
        return !preparationSelfStatus.isEmpty()
    }

    override fun isExistEngagementSelfStatus(): Boolean {
        return !engagementSelfStatus.isEmpty()
    }

    override fun isExistFinalizationSelfStatus(): Boolean {
        return !finalizationSelfStatus.isEmpty()
    }

    override fun isExistPreparationOpponentStatus(): Boolean {
        return !preparationOpponentStatus.isEmpty()
    }

    override fun isExistEngagementOpponentStatus(): Boolean {
        return !engagementOpponentStatus.isEmpty()
    }

    override fun isExistFinalizationOpponentStatus(): Boolean {
        return !finalizationOpponentStatus.isEmpty()
    }
}
