package kr.alham.playground.service.inventory

import kr.alham.playground.domain.inventory.EquipmentInventory
import kr.alham.playground.domain.inventory.EquipmentInventoryItem
import kr.alham.playground.domain.inventory.MaterialInventory
import kr.alham.playground.domain.inventory.MaterialInventoryItem
import kr.alham.playground.domain.item.Equipment
import kr.alham.playground.domain.item.Item
import kr.alham.playground.domain.item.ItemType
import kr.alham.playground.domain.item.Material
import kr.alham.playground.dto.inventory.PlayerInventoryDTO
import kr.alham.playground.repository.inventory.EquipmentItemInventoryRepository
import kr.alham.playground.repository.inventory.MaterialItemInventoryRepository
import kr.alham.playground.repository.item.EquipmentRepository
import kr.alham.playground.repository.item.MaterialRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class InventoryService(
    private val materialItemInventoryRepository: MaterialItemInventoryRepository,
    private val equipmentItemInventoryRepository: EquipmentItemInventoryRepository,
    private val equipmentRepository: EquipmentRepository,
    private val materialRepository: MaterialRepository,
) {

    fun getPlayerInventory(playerId: Long): PlayerInventoryDTO{
        TODO()
    }

    //플레이어 인벤토리에 신규 아이템을 저장하는 로직
    @Transactional
    fun saveItemToPlayerInventory(playerId: Long, itemInfo: Item) {
        // 플레이어의 인벤토리에 아이템을 저장하는 로직
        // 예: 데이터베이스에 아이템 정보를 저장

        val itemId = requireNotNull(itemInfo.id) { "Item ID must not be null" }

        when(itemInfo.type){
            ItemType.EQUIPMENT -> {
                // 장비 아이템을 인벤토리에 저장하는 로직
                val equipmentInventory = getEquipmentInventoryByPlayerId(playerId)
                val equipment = getEquipmentById(itemId)
                EquipmentInventoryItem.create(equipmentInventory,equipment)
            }
            ItemType.MATERIAL -> {
                // 재료 아이템을 인벤토리에 저장하는 로직
                // materialItemInventoryRepository.save(itemInfo)
                val materialInventory = getMaterialInventoryByPlayerId(playerId)
                val material = getMaterialById(itemId)
                MaterialInventoryItem.create(materialInventory, material)
            }
        }

    }

    fun getEquipmentInventoryByPlayerId(playerId: Long): EquipmentInventory {
        // 플레이어의 장비 인벤토리를 가져오는 로직
        return equipmentItemInventoryRepository.findEquipmentInventoryByPlayerId(playerId)
            ?: throw IllegalArgumentException("Equipment inventory not found for player ID: $playerId")
    }

    fun getEquipmentById(equipmentId: Long): Equipment {
        // 장비 아이템을 ID로 가져오는 로직
        return equipmentRepository.findById(equipmentId)
            .orElseThrow { IllegalArgumentException("Equipment not found with ID: $equipmentId") }
    }

    fun getMaterialInventoryByPlayerId(playerId: Long): MaterialInventory {
        // 플레이어의 재료 인벤토리를 가져오는 로직
        return materialItemInventoryRepository.findMaterialInventoryByPlayerId(playerId)
            ?: throw IllegalArgumentException("Material inventory not found for player ID: $playerId")
    }

    fun getMaterialById(materialId: Long): Material {
        // 재료 아이템을 ID로 가져오는 로직
        return materialRepository.findById(materialId)
            .orElseThrow { IllegalArgumentException("Material not found with ID: $materialId") }
    }


}