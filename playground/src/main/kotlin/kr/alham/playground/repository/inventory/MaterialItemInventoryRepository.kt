package kr.alham.playground.repository.inventory

import kr.alham.playground.domain.inventory.MaterialInventory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MaterialItemInventoryRepository:JpaRepository<MaterialInventory, Long> {

}