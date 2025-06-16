package kr.alham.playground.service.drop

import kr.alham.playground.domain.item.Item
import kr.alham.playground.domain.item.ItemType
import kr.alham.playground.dto.drop.MonsterDropResultDTO
import kr.alham.playground.repository.item.EquipmentRepository
import kr.alham.playground.repository.item.MaterialRepository
import kr.alham.playground.service.inventory.InventoryService
import kr.alham.playground.system.drop.DropSystem
import org.springframework.stereotype.Component

@Component
class DropService(
    private val dropSystem: DropSystem,
    private val inventoryService: InventoryService,
    private val equipmentRepository: EquipmentRepository,
    private val materialRepository: MaterialRepository,
) {

    fun getDropItem(playerId : Long ,monsterId: Long): MonsterDropResultDTO {

        val dropItemDTO = dropSystem.dropItem(monsterId)

        //드롭아이템 저장 로직 추가 필요
        val dropItem:Item = when(dropItemDTO.itemType){
            // 장비 아이템인 경우
            ItemType.EQUIPMENT -> {
                equipmentRepository.findById(dropItemDTO.itemId).orElseThrow {
                    NoSuchElementException("장비 아이템을 찾을 수 없습니다.")
                }
            }
            // 재료 아이템인 경우
            ItemType.MATERIAL -> {
                materialRepository.findById(dropItemDTO.itemId).orElseThrow {
                    NoSuchElementException("재료 아이템을 찾을 수 없습니다.")
                }
            }
        }

        //인벤토리에 아이템 저장
        inventoryService.saveItemToPlayerInventory(playerId, dropItem)

        return MonsterDropResultDTO(
            name = dropItem.name,
            description = dropItem.description,
            itemRarity = dropItem.itemRarity,
            itemType = dropItem.type
        )
    }
}