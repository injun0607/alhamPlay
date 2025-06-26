package kr.alham.playground.controller.craft

import kr.alham.playground.dto.craft.EquipmentDTO
import kr.alham.playground.dto.craft.EquipmentRecipeDTO
import kr.alham.playground.dto.craft.IngredientsInfoDTO
import kr.alham.playground.dto.craft.IngredientsInfoDTOList
import kr.alham.playground.dto.inventory.PlayerEquipmentInventoryItemDTO
import kr.alham.playground.service.craft.CraftService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/craft")
@CrossOrigin(origins = ["*"])
class CraftController(
    private val craftService: CraftService
) {

    @PostMapping("/equipment")
    fun equipmentCraft(@RequestBody ingredientsInfoDTOList: IngredientsInfoDTOList):PlayerEquipmentInventoryItemDTO{
        val playerId = 1L
        return craftService.makeEquipment(playerId, ingredientsInfoDTOList)
    }

}