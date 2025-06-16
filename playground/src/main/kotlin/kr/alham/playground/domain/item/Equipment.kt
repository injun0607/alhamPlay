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
    @Enumerated(EnumType.STRING)
    override var type: ItemType = ItemType.EQUIPMENT,
    @Enumerated(EnumType.STRING)
    override var itemRarity: ItemRarity = ItemRarity.COMMON,
    @Enumerated(EnumType.STRING)
    var equipmentType: EquipmentType = EquipmentType.WEAPON,
) : Item {


}

enum class EquipmentType {
    WEAPON,
    ARMOR,
    ACCESSORY
}

