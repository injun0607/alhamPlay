package kr.alham.playground.system.battle.monster

import kr.alham.playground.domain.card.Card
import kr.alham.playground.domain.card.MonsterCard
import kr.alham.playground.domain.common.TargetElementStatusMap
import kr.alham.playground.domain.monster.MonsterType
import org.springframework.stereotype.Component

@Component
class NormalMonsterCardProvider: MonsterCardProviderStrategy {
    override fun supportedType(): MonsterType {
        return MonsterType.NORMAL
    }

    override fun getMonsterCard(
        index: Int,
        monsterStatusMap: TargetElementStatusMap,
        monsterCardList: List<Card>
    ): Card {
        if (index < 0 || index >= monsterCardList.size) {
            throw IndexOutOfBoundsException("Index $index is out of bounds for monster card list of size ${monsterCardList.size}")
        }
        return monsterCardList[index]
    }
}