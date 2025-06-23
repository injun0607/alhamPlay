package kr.alham.playground.repository.item

import kr.alham.playground.domain.item.Equipment
import org.springframework.data.jpa.repository.JpaRepository

interface EquipmentRepository:JpaRepository<Equipment, Long> {
    // 장비 아이템을 저장하고 불러오는 메소드들을 정의할 수 있습니다.
    // 예: findByName, findByType 등
    fun findEquipmentByName(name: String): Equipment?

}