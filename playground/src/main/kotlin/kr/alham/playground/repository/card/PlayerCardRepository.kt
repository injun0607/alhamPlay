package kr.alham.playground.repository.card

import kr.alham.playground.domain.card.PlayerCard
import kr.alham.playground.domain.player.Player
import kr.alham.playground.domain.player.PlayerCardInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PlayerCardRepository: JpaRepository<PlayerCardInfo,Long> {

    @Query("""
        SELECT COUNT (pci) = :size 
            FROM PlayerCardInfo pci 
            WHERE pci.player.id = :playerId AND pci.card.id IN :cardIdList
        """)
    fun validPlayerCardInfo(@Param("cardIdList") cardIdList: List<Long>,
                            @Param("size") size: Int,
                            @Param("playerId") playerId:Long
    ): Boolean

    @Query("SELECT pci FROM PlayerCardInfo pci JOIN FETCH pci.card c WHERE pci.player.id = :playerId AND c.id IN :cardIdList")
    fun findCardListByPlayerIdAndCardIdIn(@Param("playerId") playerId: Long, cardIdList: List<Long>): List<PlayerCardInfo>


}