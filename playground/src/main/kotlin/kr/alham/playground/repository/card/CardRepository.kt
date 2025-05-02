package kr.alham.playground.repository.card

import kr.alham.playground.domain.card.Card
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CardRepository: JpaRepository<Card, Long> {

    fun findCardListByIdIn(idList: List<Long>): List<Card>

}