package kr.alham.playground.repository.card

import kr.alham.playground.domain.card.MonsterCard
import kr.alham.playground.domain.monster.MonsterCardInfo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface MonsterCardRepository: JpaRepository<MonsterCard, Long> {

}