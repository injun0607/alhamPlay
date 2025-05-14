package kr.alham.playground.domain.card

import jakarta.persistence.*
import kr.alham.playground.domain.common.TargetElementStatus
import kr.alham.playground.domain.common.TargetElementStatusMap
import kr.alham.playground.domain.enums.BattlePhase
import kr.alham.playground.domain.enums.CardAttribute
import kr.alham.playground.domain.enums.CardTarget
import kr.alham.playground.domain.enums.CardType

interface Card{
    var id: Long?
    var uuid: String?
    var battlePhase: BattlePhase
    var name: String
    var description: String
    var cardTarget: CardTarget
    var cardType: CardType
    var cardAttribute: CardAttribute
    var counterCardAttribute: CardAttribute
    var cost: Int
    var effectOpponentNum: Double
    var effectOpponentStat: TargetElementStatus
    var effectOpponentTurn: Int
    var effectSelfNum: Double
    var effectSelfStat: TargetElementStatus
    var effectSelfTurn: Int

    /**
     * 카드의 속성에 따라 대미지를 계산하는 메소드
     */
    fun calculateValue(targetElementStatusMap: TargetElementStatusMap, value: Double): Double
}


@Entity
@Table(name = "al_player_card")
class PlayerCard(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_card_id")
    override var id: Long? = null,
    override var uuid: String? = null,
    override var battlePhase: BattlePhase = BattlePhase.PREPARATION,
    override var name: String = "",
    override var description: String = "",
    override var cardTarget: CardTarget = CardTarget.SELF,
    override var cardType: CardType = CardType.ATTACK,
    override var cardAttribute: CardAttribute = CardAttribute.NONE,
    override var counterCardAttribute: CardAttribute = CardAttribute.NONE,
    override var cost: Int = 0,
    override var effectOpponentNum: Double = 0.0,
    override var effectOpponentStat: TargetElementStatus = TargetElementStatus.HP,
    override var effectOpponentTurn: Int = 0,
    override var effectSelfNum: Double = 0.0,
    override var effectSelfStat: TargetElementStatus = TargetElementStatus.HP,
    override var effectSelfTurn: Int = 0,
): Card {

    override fun calculateValue(targetElementStatusMap: TargetElementStatusMap, value: Double):Double {
        var resultValue = value
        this.cardAttribute.getAffectedStatus().entries.forEach{
            resultValue += targetElementStatusMap.get(it.key) * it.value
        }

        return resultValue
    }


}


@Entity
@Table(name = "al_monster_card")
class MonsterCard(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "monster_card_id")
    override var id: Long? = null,
    override var uuid: String? = null,
    override var battlePhase: BattlePhase = BattlePhase.PREPARATION,
    override var name: String = "",
    override var description: String = "",
    override var cardTarget: CardTarget = CardTarget.SELF,
    override var cardType: CardType = CardType.ATTACK,
    override var cardAttribute: CardAttribute = CardAttribute.NONE,
    override var counterCardAttribute: CardAttribute = CardAttribute.NONE,
    override var cost: Int = 0,
    override var effectOpponentNum: Double = 0.0,
    override var effectOpponentStat: TargetElementStatus = TargetElementStatus.HP,
    override var effectOpponentTurn: Int = 0,
    override var effectSelfNum: Double = 0.0,
    override var effectSelfStat: TargetElementStatus = TargetElementStatus.HP,
    override var effectSelfTurn: Int = 0,
    var battleOrder: Int = 0,

): Card{
    override fun calculateValue(targetElementStatusMap: TargetElementStatusMap, value: Double):Double {
        var resultValue = value
        this.cardAttribute.getAffectedStatus().entries.forEach{
            resultValue += targetElementStatusMap.get(it.key) * it.value
        }

        return resultValue
    }


}