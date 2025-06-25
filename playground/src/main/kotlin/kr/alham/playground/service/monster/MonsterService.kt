package kr.alham.playground.service.monster

import kr.alham.playground.domain.card.MonsterCard
import kr.alham.playground.domain.monster.Monster
import kr.alham.playground.domain.monster.MonsterCardInfo
import kr.alham.playground.repository.monster.MonsterCardInfoRepository
import kr.alham.playground.repository.monster.MonsterRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MonsterService(
    private val monsterRepository: MonsterRepository,
    private val monsterCardInfoRepository: MonsterCardInfoRepository,
) {
    fun findMonsterById(monsterId: Long): Monster{
        return monsterRepository.findById(monsterId).orElseThrow {
            IllegalArgumentException("Monster not found with id: $monsterId")
        }
    }

    fun findAllMonsterCardInfoByMonsterId(
        monsterId: Long
    ): List<MonsterCardInfo> {
        return monsterCardInfoRepository.findAllMonsterCardByMonsterId(monsterId)
    }

    @Transactional
    fun saveMonster(monster: Monster): Monster {
        return monsterRepository.save(monster)
    }

    @Transactional
    fun saveMonsterCardInfo(monster: Monster, monsterCard: MonsterCard): MonsterCardInfo {
        val monsterCardInfo = MonsterCardInfo(
            monster = monster,
            card = monsterCard
        )
        return monsterCardInfoRepository.save(monsterCardInfo)
    }

//    @Transactional
//    fun saveMonsterDropItem(monsterId:Long , item: Item){
//        val monster = findMonsterById(monsterId)
//
//        when(item.type) {
//
//            monsterRe
//
//            ItemType.EQUIPMENT -> {
//                val monsterDropInfo = monsterDropInfoRepository.findByMonsterId(monsterId)
//                    ?: throw IllegalArgumentException("Monster drop info not found for monster id: $monsterId")
//                monsterDropInfo.addEquipment(item)
//                monsterDropInfoRepository.save(monsterDropInfo)
//            }
//            ItemType.MATERIAL -> {
//                val monsterDropInfo = monsterDropInfoRepository.findByMonsterId(monsterId)
//                    ?: throw IllegalArgumentException("Monster drop info not found for monster id: $monsterId")
//                monsterDropInfo.addMaterial(item)
//                monsterDropInfoRepository.save(monsterDropInfo)
//            }
//            else -> throw IllegalArgumentException("Unsupported item type: ${item.type}")
//        }
//
//    }



}