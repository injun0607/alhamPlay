package kr.alham.playground.domain.enums

import kr.alham.playground.domain.common.TargetElement
import kr.alham.playground.domain.common.TargetElementStatus

enum class CardTarget{
    SELF,
    OPPONENT,
    MUTUAL
}


enum class CardType{
    BUFF,
    ATTACK,
    HEAL,
    DEFENCE,
    EVASION,
    DEBUFF,
}


//불속성,베기,찌르기, 물속성 등등 카드의 속성
//해당 속성에 속한 능력치를 추가로 부여받음
enum class CardAttribute(
    val affectedStatusMap: Map<TargetElementStatus,Double>
){
    NONE(mapOf()),
    CUT(mapOf(
        TargetElementStatus.ATK to 1.0,
        TargetElementStatus.STR to 1.0,
    )),
    BLOW(mapOf(
        TargetElementStatus.ATK to 1.0,
        TargetElementStatus.STR to 1.0
    )),
    THRUST(mapOf(
        TargetElementStatus.ATK to 1.0,
        TargetElementStatus.STR to 1.0,
    )),
    PIERCE(mapOf(
        TargetElementStatus.ATK to 1.0,
        TargetElementStatus.STR to 1.0
    )),
    FIRE(mapOf(
        TargetElementStatus.ATK to 1.0,
        TargetElementStatus.INT to 1.0,
    )),
    ICE(mapOf(
        TargetElementStatus.ATK to 1.0,
        TargetElementStatus.INT to 1.0
    )),
    ELECTRIC(mapOf(
        TargetElementStatus.ATK to 1.0,
        TargetElementStatus.INT to 1.0,
    )),
    HOLY(mapOf(
        TargetElementStatus.ATK to 1.0,
        TargetElementStatus.INT to 1.0,
    )),
    MAGIC(mapOf(
        TargetElementStatus.ATK to 1.0,
        TargetElementStatus.INT to 1.0,
    )),
    EXPLODE(mapOf(
        TargetElementStatus.ATK to 1.0,
        TargetElementStatus.INT to 1.0,
    ));

    fun getAffectedStatus(): Map<TargetElementStatus, Double> {
        return affectedStatusMap
    }

}
