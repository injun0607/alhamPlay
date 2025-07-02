package kr.alham.playground.service.dismantle

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
        //해당 장비 인벤토리에 있는지 검수

        val equipment = inventoryService.getEquipmentByName(equipmentInfo.name)
        //유효성 검사 및 삭제 진행
        inventoryService.deletePlayerInventoryEquipmentByInventoryItemId(playerId,equipmentInfo.inventoryItemId, equipmentInfo.id)
        val materialNameList =  dismantleSystem.dismantleEquipment(equipment)

        //TODO - materialNameList 에 있는 재료들 플레이어 인벤토리로 추가 해야한다.


        //


//        inventoryService.
//
//
//
//        return dismantleSystem.dismantleEquipment(equipmentName, itemRarity)
        TODO()
    }
}