package kr.alham.playground.repository.player

import kr.alham.playground.domain.player.Player
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PlayerRepository: JpaRepository<Player, Long> {



}