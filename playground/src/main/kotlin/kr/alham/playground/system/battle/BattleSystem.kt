package kr.alham.playground.system.battle

import kr.alham.playground.domain.card.MonsterCard
import kr.alham.playground.domain.monster.Monster
import kr.alham.playground.system.cardeffect.CardEffectFactory
import org.springframework.stereotype.Component

@Component
class BattleSystem(
    private val cardEffectFactory: CardEffectFactory
) {
    //몬스터 카드 배틀 로직
    fun monsterCardBattleLogic(monster: Monster, monsterCardList: List<MonsterCard>){

    }




}