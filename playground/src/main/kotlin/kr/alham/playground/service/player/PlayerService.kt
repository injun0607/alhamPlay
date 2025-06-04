package kr.alham.playground.service.player

import kr.alham.playground.domain.player.Player
import kr.alham.playground.repository.player.PlayerRepository
import org.springframework.stereotype.Service

@Service
class PlayerService(
    private val playerRepository: PlayerRepository
) {
    fun findPlayerById(playerId: Long):Player {
        return playerRepository.findById(playerId).orElseThrow{
            IllegalArgumentException("Player not found with id: $playerId")
        }
    }

}