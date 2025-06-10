package kr.alham.playground.system.battle.monster

import kr.alham.playground.domain.card.Card
import kr.alham.playground.domain.card.MonsterCard
import kr.alham.playground.domain.common.TargetElementStatus
import kr.alham.playground.domain.common.TargetElementStatusMap
import kr.alham.playground.domain.monster.MonsterType
import org.springframework.stereotype.Component

@Component
class MonsterCardProvider(
    private val strategies: List<MonsterCardProviderStrategy>
) {

    private val strategyMap: Map<MonsterType, MonsterCardProviderStrategy> = strategies
        .associateBy { it.supportedType() }

    fun getMonsterProvider(monsterType: MonsterType): MonsterCardProviderStrategy {
        return strategyMap[monsterType]
            ?: strategyMap[MonsterType.NORMAL]
            ?: throw IllegalArgumentException("지원하지 않는 몬스터 타입입니다: $monsterType")
    }
}


interface MonsterCardProviderStrategy {
    fun supportedType(): MonsterType
    fun getMonsterCard(index: Int, monsterStatusMap:TargetElementStatusMap, monsterCardList: List<Card> ): Card
}