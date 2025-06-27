package kr.alham.playground.controller.craft

import kr.alham.playground.common.utils.mapper.ItemMapper
import kr.alham.playground.dto.craft.EquipmentDTO
import kr.alham.playground.dto.craft.EquipmentRecipeDTO
import kr.alham.playground.dto.craft.IngredientsInfoDTO
import kr.alham.playground.dto.craft.IngredientsInfoDTOList
import kr.alham.playground.dto.inventory.PlayerEquipmentInventoryItemDTO
import kr.alham.playground.service.craft.CraftService
import kr.alham.playground.service.inventory.InventoryService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/craft")
@CrossOrigin(origins = ["*"])
class CraftController(
    private val craftService: CraftService,
    private val inventoryService: InventoryService
) {

    @PostMapping("/equipment")
    fun equipmentCraft(@RequestBody ingredientsInfoDTOList: IngredientsInfoDTOList):PlayerEquipmentInventoryItemDTO{
        val playerId = 1L
        val madeEquipment = craftService.makeEquipment(playerId, ingredientsInfoDTOList)

        val inventoryItem =  inventoryService.getEquipmentInventoryByPlayerId(playerId).equipmentItemList.first{
            it.equipment.id == madeEquipment.id
        }

        return ItemMapper.equipmentInventoryItemToDTO(inventoryItem)
    }

}