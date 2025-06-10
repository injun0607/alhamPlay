package kr.alham.playground.repository.monster

import kr.alham.playground.domain.monster.MonsterCardInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MonsterCardInfoRepository: JpaRepository<MonsterCardInfo, Long> {

    @Query("SELECT mci FROM MonsterCardInfo mci JOIN FETCH mci.card JOIN FETCH mci.monster WHERE mci.monster.id = :monsterId")
    fun findAllMonsterCardByMonsterId(monsterId: Long): List<MonsterCardInfo>
}