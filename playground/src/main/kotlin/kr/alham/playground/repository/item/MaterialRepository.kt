package kr.alham.playground.repository.item

import kr.alham.playground.domain.item.Material
import org.springframework.data.jpa.repository.JpaRepository

interface MaterialRepository:JpaRepository<Material, Long> {

}