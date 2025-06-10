package kr.alham.playground.system.battle.monster

import kr.alham.playground.domain.card.Card
import kr.alham.playground.domain.card.MonsterCard
import kr.alham.playground.domain.common.TargetElementStatusMap
import kr.alham.playground.domain.monster.MonsterType
import org.springframework.stereotype.Component

@Component
class BossMonsterCardProvider: MonsterCardProviderStrategy {
    override fun supportedType(): MonsterType {
        return MonsterType.BOSS
    }

    override fun getMonsterCard(
        index: Int,
        monsterStatusMap: TargetElementStatusMap,
        monsterCardList: List<Card>
    ): Card {
        /**
         * 추후 몬스터 체력 퍼센테이지에 대한 로직 작성
         */
        if (index < 0 || index >= monsterCardList.size) {
            throw IndexOutOfBoundsException("Index $index is out of bounds for monster card list of size ${monsterCardList.size}")
        }
        return monsterCardList[index]
    }
}