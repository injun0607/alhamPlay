package kr.alham.playground.service.dismantle

import kr.alham.playground.service.inventory.InventoryService
import kr.alham.playground.system.dismantle.DismantleSystem
import org.springframework.stereotype.Service

@Service
class DismantleService(
    private val dismantleSystem: DismantleSystem,
    private val inventoryService: InventoryService,
) {

    fun dismantleEquipment(playerId: Long,equipmentId: Long): List<String> {
        //해당 장비 인벤토리에 있는지 검수
//        inventoryService.
//
//
//
//        return dismantleSystem.dismantleEquipment(equipmentName, itemRarity)
        TODO()
    }
}