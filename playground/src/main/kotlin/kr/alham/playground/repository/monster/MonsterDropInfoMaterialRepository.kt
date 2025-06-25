package kr.alham.playground.repository.monster

import kr.alham.playground.domain.monster.MonsterDropInfoMaterial
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface MonsterDropInfoMaterialRepository:JpaRepository<MonsterDropInfoMaterial, Long> {

    @Query("""
        SELECT m FROM MonsterDropInfoMaterial m
            JOIN FETCH m.monster
            LEFT JOIN FETCH m.monsterDropItemMaterialList mid
            LEFT JOIN FETCH mid.material
        WHERE m.monster.id = :monsterId
    """)
    fun findMonsterDropInfoMaterialByMonsterId(monsterId: Long): MonsterDropInfoMaterial?
}