package kr.alham.playground.repository.collection

import kr.alham.playground.domain.collection.PlayerEquipmentCollection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PlayerEquipmentCollectionRepository : JpaRepository<PlayerEquipmentCollection, Long>{

    @Query("""
        SELECT p FROM PlayerEquipmentCollection p
            JOIN FETCH p.player
            JOIN FETCH p.equipment
        WHERE p.player.id = :playerId
    """)
    fun getAllEquipmentCollectionByPlayerId(playerId: Long): List<PlayerEquipmentCollection>

    fun existsPlayerEquipmentCollectionByPlayerIdAndEquipmentId(
        playerId: Long,
        equipmentId: Long
    ): Boolean

    @Query("""
        SELECT p FROM PlayerEquipmentCollection p
            JOIN FETCH p.player
            JOIN FETCH p.equipment
        WHERE p.equipment.id = :equipmentId
    """)
    fun getPlayerEquipmentCollectionByEquipmentId(
        equipmentId: Long
    ): PlayerEquipmentCollection?

}