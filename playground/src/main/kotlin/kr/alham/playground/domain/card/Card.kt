package kr.alham.playground.domain.card

import kr.alham.playground.domain.common.TargetElementStatus
import kr.alham.playground.domain.enums.BattlePhase
import kr.alham.playground.domain.enums.CardAttribute
import kr.alham.playground.domain.enums.CardTarget
import kr.alham.playground.domain.enums.CardType

class Card(
    var id: Long? = null,
    var uuid: String? = null,
    var battlePhase: BattlePhase = BattlePhase.PREPARATION,
    var name: String = "",
    var description: String = "",
    var skillTarget: CardTarget = CardTarget.SELF,
    var skillType: CardType = CardType.ATTACK,
    var skillAttribute: CardAttribute = CardAttribute.NONE,
    var counterSkillAttribute: CardAttribute = CardAttribute.NONE,
    var cost: Int = 0,
    var effectOpponentNum: Int = 0,
    var effectOpponentStat: TargetElementStatus = TargetElementStatus.HP,
    var effectOpponentTurn: Int = 0,
    var effectSelfNum: Int = 0,
    var effectSelfStat: TargetElementStatus = TargetElementStatus.HP,
    var effectSelfTurn: Int = 0,
) {

}