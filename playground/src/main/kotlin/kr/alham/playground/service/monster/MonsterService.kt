package kr.alham.playground.service.monster

import kr.alham.playground.domain.monster.Monster
import kr.alham.playground.repository.monster.MonsterRepository
import org.springframework.stereotype.Service

@Service
class MonsterService(
    private val monsterRepository: MonsterRepository
) {
    fun findMonsterById(monsterId: Long): Monster{
        return monsterRepository.findById(monsterId).orElseThrow {
            IllegalArgumentException("Monster not found with id: $monsterId")
        }
    }
}