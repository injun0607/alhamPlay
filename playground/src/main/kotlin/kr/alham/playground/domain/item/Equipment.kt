package kr.alham.playground.domain.item

import jakarta.persistence.*
import kr.alham.playground.domain.common.TargetElementStatus

@Entity
@Table(name = "al_equipment")
class Equipment(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Long? = null,
    override val name: String = "",
    override val description: String = "",
    override val type: ItemType = ItemType.EQUIPMENT,
    override val itemRarity: ItemRarity = ItemRarity.COMMON,
    val equipmentType: EquipmentType = EquipmentType.WEAPON,
) : Item {


}

enum class EquipmentType {
    WEAPON,
    ARMOR,
    ACCESSORY
}

