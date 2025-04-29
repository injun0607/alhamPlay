package kr.alham.playground.domain.equipment

import kr.alham.playground.domain.enums.CardAttribute
import kr.alham.playground.domain.enums.EquipmentType

class Equipment(
    val id: String? = null,
    val name: String = "",
    val description: String = "",
    val type: EquipmentType = EquipmentType.WEAPON,
    val advantageCardAttribute: CardAttribute = CardAttribute.NONE,
    val disadvantageCardAttribute: CardAttribute = CardAttribute.NONE,
    val advantageCardNum: Int = 0,
    val disadvantageCardNum: Int = 0,
) {


}