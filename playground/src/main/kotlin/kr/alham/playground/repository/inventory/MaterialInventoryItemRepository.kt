package kr.alham.playground.repository.inventory

import kr.alham.playground.domain.inventory.MaterialInventoryItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface MaterialInventoryItemRepository: JpaRepository<MaterialInventoryItem, Long> {

    @Query(
        """
            SELECT m FROM MaterialInventoryItem m
                JOIN FETCH m.material
                JOIN FETCH m.materialInventory mi
            WHERE m.materialInventory.id = :inventoryItemId"""
    )
    fun getMaterialInventoryItemListByInventoryId(inventoryItemId: Long): List<MaterialInventoryItem>
}