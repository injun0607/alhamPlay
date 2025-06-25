package kr.alham.playground.repository.monster

import kr.alham.playground.domain.monster.MonsterDropInfoEquipment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MonsterDropInfoEquipmentRepository:JpaRepository<MonsterDropInfoEquipment, Long> {

    @Query("""
        SELECT m FROM MonsterDropInfoEquipment m
            JOIN FETCH m.monster
            LEFT JOIN FETCH m.monsterDropItemEquipmentList mid
            LEFT JOIN FETCH mid.equipment
        WHERE m.monster.id = :monsterId
    """)
    fun findMonsterDropInfoEquipmentByMonsterId(monsterId: Long): MonsterDropInfoEquipment?

}