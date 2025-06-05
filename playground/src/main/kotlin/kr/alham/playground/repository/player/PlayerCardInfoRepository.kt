package kr.alham.playground.repository.player

import kr.alham.playground.domain.player.PlayerCardInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface PlayerCardInfoRepository: JpaRepository<PlayerCardInfo, Long> {

    @Query(""" SELECT pci FROM PlayerCardInfo pci JOIN FETCH pci.card JOIN FETCH pci.player WHERE pci.player.id = :playerId """)
    fun findPlayerCardByPlayerId(playerId: Long): List<PlayerCardInfo>

    @Query(""" 
            SELECT pci FROM PlayerCardInfo pci 
                JOIN FETCH pci.player 
                JOIN FETCH pci.card 
            WHERE pci.player.id = :playerId AND pci.card.id IN :cardIdList """)
    fun findPlayerCardListByCardIdList(playerId: Long ,cardIdList: List<Long>): List<PlayerCardInfo>



}