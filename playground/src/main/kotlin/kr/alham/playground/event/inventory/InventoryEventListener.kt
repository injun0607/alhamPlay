package kr.alham.playground.event.inventory

import kr.alham.playground.dto.inventory.DeleteEquipmentInventoryItemEvent
import kr.alham.playground.service.inventory.InventoryService
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class InventoryEventListener(
    private val inventoryService: InventoryService
) {
    // 장비 아이템 삭제 이벤트 리스너
    @EventListener
    fun handleDeleteEquipmentInventoryItemEvent(event: DeleteEquipmentInventoryItemEvent) {
        // 플레이어의 인벤토리에서 장비 아이템을 삭제
        inventoryService.deletePlayerInventoryEquipmentByInventoryItemId(
            playerId = event.playerId,
            inventoryItemId = event.inventoryItemId,
            equipmentId = event.equipmentId
        )
    }

}