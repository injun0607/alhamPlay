package kr.alham.playground.dto.craft

import kr.alham.playground.domain.item.Equipment
import kr.alham.playground.domain.item.EquipmentType
import kr.alham.playground.domain.item.ItemRarity

data class EquipmentDTO(
    val name: String = "",
    val itemRarity: ItemRarity = ItemRarity.COMMON,
    val description: String = "",
    val equipmentType: EquipmentType = EquipmentType.WEAPON, // 기본값을 WEAPON으로 설정
) {
    companion object {
        fun fromEntity(equipment: Equipment): EquipmentDTO {
            return EquipmentDTO(
                name = equipment.name,
                itemRarity = equipment.itemRarity,
                description = equipment.description,
                equipmentType = equipment.equipmentType
            )
        }
    }
}