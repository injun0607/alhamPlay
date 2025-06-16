package kr.alham.playground.repository.inventory

import kr.alham.playground.domain.inventory.MaterialInventory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MaterialInventoryRepository:JpaRepository<MaterialInventory, Long> {

    @Query("SELECT mi FROM MaterialInventory mi JOIN FETCH mi.player LEFT JOIN FETCH mi.materialItemList WHERE mi.player.id = :playerId")
    fun findMaterialInventoryByPlayerId(playerId: Long): MaterialInventory?

}