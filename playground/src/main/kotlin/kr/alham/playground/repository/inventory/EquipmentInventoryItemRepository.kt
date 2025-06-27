package kr.alham.playground.repository.inventory

import kr.alham.playground.domain.inventory.EquipmentInventoryItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface EquipmentInventoryItemRepository:JpaRepository<EquipmentInventoryItem, Long> {

    @Query(
        """
            SELECT e FROM EquipmentInventoryItem e
                JOIN FETCH e.equipment
                JOIN FETCH e.equipmentInventory ei
            WHERE e.equipmentInventory.id = :inventoryItemId"""
    )
    fun getEquipmentInventoryItemListByInventoryId(inventoryItemId: Long): List<EquipmentInventoryItem>


    @Query(
        """
            SELECT e FROM EquipmentInventoryItem e
                JOIN FETCH e.equipment
                JOIN FETCH e.equipmentInventory ei
            WHERE e.id = :id AND e.equipmentInventory.id = :equipmentId"""
    )
    fun getEquipmentInventoryItemsByIdAndEquipmentId(
        id: Long,
        equipmentId: Long
    ): EquipmentInventoryItem?

}