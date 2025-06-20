package kr.alham.playground.repository.inventory

import kr.alham.playground.domain.inventory.EquipmentInventory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface EquipmentInventoryRepository:JpaRepository<EquipmentInventory, Long> {

    @Query("""SELECT ei FROM EquipmentInventory ei 
            JOIN FETCH ei.player 
            LEFT JOIN FETCH ei.equipmentItemList eil 
            LEFT JOIN FETCH eil.equipment 
            WHERE ei.player.id = :playerId""")
    fun findEquipmentInventoryByPlayerId(playerId: Long): EquipmentInventory?
}