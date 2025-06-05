package kr.alham.playground.service.player

import kr.alham.playground.domain.card.PlayerCard
import kr.alham.playground.domain.player.Player
import kr.alham.playground.domain.player.PlayerCardInfo
import kr.alham.playground.dto.card.CardIdDTO
import kr.alham.playground.repository.player.PlayerCardInfoRepository
import kr.alham.playground.repository.player.PlayerRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PlayerService(
    private val playerRepository: PlayerRepository,
    private val playerCardInfoRepository: PlayerCardInfoRepository
) {
    fun findPlayerById(playerId: Long):Player {
        return playerRepository.findById(playerId).orElseThrow{
            IllegalArgumentException("Player not found with id: $playerId")
        }
    }

    fun findPlayerInfoByPlayerId(
        playerId: Long
    ): List<PlayerCardInfo> {
        return playerCardInfoRepository.findPlayerCardByPlayerId(playerId)
    }

    @Transactional
    fun savePlayer(player: Player): Player {
        return playerRepository.save(player)
    }

    @Transactional
    fun savePlayerCardInfo(player:Player, playerCard: PlayerCard): PlayerCardInfo {
        val playerCardInfo = PlayerCardInfo(
            player = player,
            card = playerCard
        )

        return playerCardInfoRepository.save(playerCardInfo)
    }

    /**유저가 카드정보를 가지고있는지 확인
     *
     */
    fun hasPlayerCard(
        playerId: Long,
        playerCardIdDTOList : List<CardIdDTO>,
    ):Boolean{

        val cardIdList = playerCardIdDTOList.map { it.id }.distinct()
        val playerCardList = playerCardInfoRepository.findPlayerCardListByCardIdList(playerId,cardIdList)
        return cardIdList.size == playerCardList.size
    }


}