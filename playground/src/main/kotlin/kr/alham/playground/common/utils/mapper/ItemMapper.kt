package kr.alham.playground.common.utils.mapper

import kr.alham.playground.domain.inventory.EquipmentInventoryItem
import kr.alham.playground.domain.inventory.MaterialInventoryItem
import kr.alham.playground.dto.inventory.PlayerEquipmentInventoryItemDTO
import kr.alham.playground.dto.inventory.PlayerMaterialInventoryItemDTO


object ItemMapper{

    fun materialInventoryItemToDTO(
        materialInventoryItem: MaterialInventoryItem
    ) : PlayerMaterialInventoryItemDTO{
        val materialId =  requireNotNull(materialInventoryItem.material.id) {
            "Material ID cannot be null"
        }

        val materialInventoryItemId = requireNotNull(materialInventoryItem.id) {
            "Material Inventory Item ID cannot be null"
        }

        return PlayerMaterialInventoryItemDTO(
            id = materialId,
            inventoryItemId = materialInventoryItemId,
            itemOrder = materialInventoryItem.itemOrder,
            name = materialInventoryItem.material.name,
            type = materialInventoryItem.material.type,
            description = materialInventoryItem.material.description,
            itemRarity = materialInventoryItem.material.itemRarity,
            itemImg = materialInventoryItem.material.itemImg,
            quantity = materialInventoryItem.quantity
        )
    }

    fun equipmentInventoryItemToDTO(
        equipmentInventoryItem: EquipmentInventoryItem
    ) : PlayerEquipmentInventoryItemDTO {

        val equipmentId = requireNotNull(equipmentInventoryItem.equipment.id) {
            "Equipment ID cannot be null"
        }

        val equipmentInventoryItemId = requireNotNull(equipmentInventoryItem.id) {
            "Equipment Inventory Item ID cannot be null"
        }

        return PlayerEquipmentInventoryItemDTO(
            id = equipmentId,
            inventoryItemId = equipmentInventoryItemId,
            itemOrder = equipmentInventoryItem.itemOrder,
            name = equipmentInventoryItem.equipment.name,
            type = equipmentInventoryItem.equipment.type,
            description = equipmentInventoryItem.equipment.description,
            itemRarity = equipmentInventoryItem.equipment.itemRarity,
            equipmentType = equipmentInventoryItem.equipment.equipmentType,
            itemImg = equipmentInventoryItem.equipment.itemImg
        )
    }



}