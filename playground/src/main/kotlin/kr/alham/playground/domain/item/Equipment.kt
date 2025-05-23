package kr.alham.playground.domain.item

import jakarta.persistence.*
import kr.alham.playground.domain.common.TargetElementStatus

@Entity
@Table(name = "al_equipment")
class Equipment(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long? = null,
    override var name: String = "",
    override var description: String = "",
    override var type: ItemType = ItemType.EQUIPMENT,
    override var itemRarity: ItemRarity = ItemRarity.COMMON,
    var equipmentType: EquipmentType = EquipmentType.WEAPON,
) : Item {


}

enum class EquipmentType {
    WEAPON,
    ARMOR,
    ACCESSORY
}

