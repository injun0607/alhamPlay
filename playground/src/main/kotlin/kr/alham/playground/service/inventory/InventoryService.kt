package kr.alham.playground.service.inventory

import kr.alham.playground.domain.inventory.EquipmentInventory
import kr.alham.playground.domain.item.Item
import kr.alham.playground.domain.item.ItemType
import kr.alham.playground.repository.inventory.EquipmentItemInventoryRepository
import kr.alham.playground.repository.inventory.MaterialItemInventoryRepository
import org.springframework.stereotype.Service

@Service
class InventoryService(
    private val materialItemInventoryRepository: MaterialItemInventoryRepository,
    private val equipmentItemInventoryRepository: EquipmentItemInventoryRepository,
) {



    fun getPlayerInventory(playerId: Long){

    }

    fun saveItemToPlayerInventory(playerId: Long, itemInfo: Item) {
        // 플레이어의 인벤토리에 아이템을 저장하는 로직
        // 예: 데이터베이스에 아이템 정보를 저장
        when(itemInfo.type){
            ItemType.EQUIPMENT -> {
                // 장비 아이템을 인벤토리에 저장하는 로직
                equipmentItemInventoryRepository.findEquipmentInventoryByPlayerId(playerId)
                    ?: throw IllegalArgumentException("Equipment inventory not found for player ID: $playerId")
            }
            ItemType.MATERIAL -> {
                // 재료 아이템을 인벤토리에 저장하는 로직
                // materialItemInventoryRepository.save(itemInfo)
            }
            else -> {
                throw IllegalArgumentException("Unsupported item type: ${itemInfo.type}")
            }
        }

    }

    fun getEquipmentInventoryByPlayerId(playerId: Long): EquipmentInventory {
        // 플레이어의 장비 인벤토리를 가져오는 로직
        return equipmentItemInventoryRepository.findEquipmentInventoryByPlayerId(playerId)
            ?: throw IllegalArgumentException("Equipment inventory not found for player ID: $playerId")
    }

    fun saveEquipmentInventory(equipmentInventory: EquipmentInventory) {

        // 장비 인벤토리를 저장하는 로직
        equipmentItemInventoryRepository.save(equipmentInventory)

    }

}