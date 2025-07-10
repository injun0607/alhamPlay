package kr.alham.playground.controller.dismantle

import kr.alham.playground.common.utils.mapper.ItemMapper
import kr.alham.playground.dto.inventory.PlayerEquipmentInventoryItemDTO
import kr.alham.playground.dto.inventory.PlayerMaterialInventoryItemDTO
import kr.alham.playground.service.dismantle.DismantleService
import kr.alham.playground.service.inventory.InventoryService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/dismantle")
@CrossOrigin(origins = ["*"])
class DismantleController(
    private val dismantleService: DismantleService,
    private val inventoryService: InventoryService
) {


    @PostMapping("/equipment")
    fun dismantleEquipment(@RequestBody equipmentInventoryItemDTO: PlayerEquipmentInventoryItemDTO): List<PlayerMaterialInventoryItemDTO>{
        //TODO - 유저 인증 정보 받아서 진행
        val playerId = 1L // 예시로 플레이어 ID를 하드코딩
        val savedMaterialList = dismantleService.dismantleEquipment(playerId, equipmentInventoryItemDTO)
        val playerInventory = inventoryService.getMaterialInventoryByPlayerId(playerId)
        return savedMaterialList.map{
            playerInventory.materialItemList.first { materialItem ->
                materialItem.material.name == it
            }.let { materialInventoryItem ->
                ItemMapper.materialInventoryItemToDTO(materialInventoryItem)
            }
        }
    }
}