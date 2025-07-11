package kr.alham.playground.dto.collection

import kr.alham.playground.domain.enums.CollectionLevelEnums
import kr.alham.playground.domain.item.EquipmentType
import kr.alham.playground.domain.item.ItemRarity
import kr.alham.playground.domain.item.ItemType

data class EquipmentCollectionDTO(

    val equipmentId: Long = 0L,
    val name: String = "",
    val description: String = "",
    val itemType: ItemType = ItemType.EQUIPMENT,
    val equipmentType: EquipmentType = EquipmentType.WEAPON,
    val itemRarity: ItemRarity = ItemRarity.COMMON,
    val itemImg: String = "",
    val level: CollectionLevelEnums = CollectionLevelEnums.LEVEL1,
    val quantity: Int = 1,
    val discoveredAt: String = "",
    val isCollected: Boolean = false,
) {

}