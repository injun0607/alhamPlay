package kr.alham.playground.repository.card

import kr.alham.playground.domain.card.PlayerCard
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlayerCardRepository: JpaRepository<PlayerCard,Long> {


}