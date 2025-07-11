package kr.alham.playground.dto.inventory

data class DeleteEquipmentInventoryItemEvent(
    val playerId: Long,
    val inventoryItemId: Long,
    val equipmentId: Long
)
