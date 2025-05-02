package kr.alham.playground.repository.monster

import kr.alham.playground.domain.monster.Monster
import org.springframework.data.jpa.repository.JpaRepository

interface MonsterRepository: JpaRepository<Monster, Long> {
}