package kr.alham.playground.service.dismantle

import kr.alham.playground.domain.item.Material
import kr.alham.playground.dto.inventory.PlayerEquipmentInventoryItemDTO
import kr.alham.playground.service.inventory.InventoryService
import kr.alham.playground.system.dismantle.DismantleSystem
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DismantleService(
    private val dismantleSystem: DismantleSystem,
    private val inventoryService: InventoryService,
) {

    @Transactional
    fun dismantleEquipment(playerId: Long,equipmentInfo : PlayerEquipmentInventoryItemDTO): List<String> {
        val equipment = inventoryService.getEquipmentByName(equipmentInfo.name)
        //해당 장비 인벤토리에 있는지 검수
        //유효성 검사 및 삭제 진행
        inventoryService.deletePlayerInventoryEquipmentByInventoryItemId(playerId,equipmentInfo.inventoryItemId, equipmentInfo.id)
        val materialNameList =  dismantleSystem.dismantleEquipment(equipment)
        inventoryService.saveInventoryMaterialByNameList(playerId, materialNameList)

        return materialNameList
    }
}