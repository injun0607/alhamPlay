package kr.alham.playground.service.card

import kr.alham.playground.domain.card.MonsterCard
import kr.alham.playground.domain.card.PlayerCard
import kr.alham.playground.repository.card.MonsterCardRepository
import kr.alham.playground.repository.card.PlayerCardRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CardService(
    private val playerCardRepository: PlayerCardRepository,
    private val monsterCardRepository: MonsterCardRepository
) {

    @Transactional
    fun savePlayerCard(playerCard: PlayerCard): PlayerCard {
        return playerCardRepository.save(playerCard)
    }

    @Transactional
    fun saveMonsterCard(monsterCard: MonsterCard): MonsterCard {
        return monsterCardRepository.save(monsterCard)
    }

    fun findPlayerCardById(playerCardId: Long): PlayerCard {
        return playerCardRepository.findById(playerCardId)
            .orElseThrow{ IllegalArgumentException("PlayerCard with id $playerCardId not found") }
    }

    fun findMonsterCardById(monsterCardId: Long): MonsterCard {
        return monsterCardRepository.findById(monsterCardId)
            .orElseThrow{ IllegalArgumentException("MonsterCard with id $monsterCardId not found") }
    }

}