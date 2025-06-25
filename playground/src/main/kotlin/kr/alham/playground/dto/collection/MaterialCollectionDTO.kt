package kr.alham.playground.dto.collection

import kr.alham.playground.domain.area.FieldType
import kr.alham.playground.domain.item.EquipmentType
import kr.alham.playground.domain.item.ItemRarity
import kr.alham.playground.domain.item.ItemType

data class MaterialCollectionDTO(
    val materialId: Long = 0L,
    val name: String = "",
    val description: String = "",
    val itemType: ItemType = ItemType.MATERIAL,
    val itemRarity: ItemRarity = ItemRarity.COMMON,
    val itemImg: String = "",
    val dropArea: FieldType = FieldType.FOREST,
    val discoveredAt: String = "",
    val isCollected: Boolean = false,
) {
}