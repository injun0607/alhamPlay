package kr.alham.playground.repository.inventory

import kr.alham.playground.domain.inventory.MaterialInventory
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MaterialInventoryRepository:JpaRepository<MaterialInventory, Long> {

    @EntityGraph(attributePaths = ["player", "materialItemList", "materialItemList.material"])
    @Query("""SELECT m FROM MaterialInventory m 
            LEFT JOIN FETCH m.player p
            LEFT JOIN FETCH m.materialItemList mli
            LEFT JOIN FETCH mli.material 
            WHERE m.player.id = :playerId order by mli.itemOrder""")
    fun findMaterialInventoryByPlayerId(playerId: Long): MaterialInventory?

}