package kr.alham.playground.repository.collection

import kr.alham.playground.domain.collection.PlayerMaterialCollection
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PlayerMaterialCollectionRepository:JpaRepository<PlayerMaterialCollection,Long> {

    @Query("""
        SELECT p FROM PlayerMaterialCollection p
            JOIN FETCH p.player
            JOIN FETCH p.material
        WHERE p.player.id = :playerId
    """)
    fun getAllMaterialCollectionByPlayerId(playerId: Long): List<PlayerMaterialCollection>

    fun existsPlayerMaterialCollectionByPlayerIdAndMaterialId(
        playerId: Long,
        materialId: Long
    ): Boolean

}