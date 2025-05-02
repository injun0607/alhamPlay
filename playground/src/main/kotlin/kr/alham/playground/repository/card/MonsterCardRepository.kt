package kr.alham.playground.repository.card

import kr.alham.playground.domain.card.MonsterCard
import kr.alham.playground.domain.monster.MonsterCardInfo
import kr.alham.playground.domain.player.PlayerCardInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface MonsterCardRepository: JpaRepository<MonsterCard, Long> {
    @Query("SELECT mci FROM MonsterCardInfo mci JOIN FETCH mci.card c WHERE mci.monster.id = :monsterId")
    fun findCardListByMonsterIdAndCardIdIn(@Param("monsterId") monsterId: Long): List<MonsterCardInfo>
}