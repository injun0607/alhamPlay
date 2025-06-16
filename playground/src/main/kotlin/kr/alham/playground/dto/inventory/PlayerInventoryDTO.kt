package kr.alham.playground.dto.inventory

import kr.alham.playground.domain.item.EquipmentType
import kr.alham.playground.domain.item.ItemRarity
import kr.alham.playground.domain.item.ItemType

data class PlayerInventoryDTO(
    val playerId: Long? = null,
    val playerMaterialInventory: PlayerMaterialInventoryDTO,
    val playerEquipmentInventory: PlayerEquipmentInventoryDTO
    //equipmentInventory
) {}


data class PlayerMaterialInventoryDTO(
    val materialItemList: List<PlayerMaterialInventoryItemDTO> = emptyList()
){
    companion object {
        fun from(materialItemList: List<PlayerMaterialInventoryItemDTO>): PlayerMaterialInventoryDTO {
            return PlayerMaterialInventoryDTO(materialItemList)
        }
    }
}

data class PlayerMaterialInventoryItemDTO(
    val materialId: Long,
    val name: String,
    val description: String,
    val type: ItemType,
    val itemRarity: ItemRarity,
    val itemOrder: Int
)


data class PlayerEquipmentInventoryDTO(
    val equipmentItemList: List<PlayerEquipmentInventoryItemDTO> = emptyList()
){
    companion object {
        fun from(equipmentItemList: List<PlayerEquipmentInventoryItemDTO>): PlayerEquipmentInventoryDTO {
            return PlayerEquipmentInventoryDTO(equipmentItemList)
        }
    }
}

data class PlayerEquipmentInventoryItemDTO(
    val equipmentId: Long,
    val name: String,
    val description: String,
    val type: ItemType,
    val equipmentType: EquipmentType,
    val itemRarity: ItemRarity,
    val itemOrder: Int
)

