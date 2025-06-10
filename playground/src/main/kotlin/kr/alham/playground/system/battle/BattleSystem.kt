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
    /**
     * 몬스터 타입에 따른 카드 전투 구성 , 일반 / 엘리트 몬스터 나누고
     */
    fun monsterCardBattleLogic(monster: Monster, monsterCardList: List<MonsterCard>){

    }

    //pseudo code
    /**enegagement Phaze
     * for(user vs monster){
     *    max(user.cardSize, monster.cardSize)
     *    for(i in 0 until max){
     *       user.card[i] , monster.card[i]
     *       카드가 없는경우 효과 X
     *       이떄 중요한건 -> 몬스터 카드의 경우 보스패턴이 있을수도있음 (중요한건 어떤 카드를 뱉어내느냐) 이게 중요 ->
     *       cardEffect(user.card[i], monster.card[i])
     *
     *       checkFinalization(user.state, monster.state)
     *    }
     * }
     *
     *
     */



}