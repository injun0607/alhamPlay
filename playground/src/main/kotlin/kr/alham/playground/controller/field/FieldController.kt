package kr.alham.playground.controller.field

import kr.alham.playground.common.utils.mapper.FieldAreaMapper
import kr.alham.playground.domain.area.FieldType
import kr.alham.playground.domain.inventory.MaterialInventoryItem
import kr.alham.playground.dto.craft.MaterialDTO
import kr.alham.playground.dto.field.FieldAreaDTO
import kr.alham.playground.dto.gather.GatherMaterialDTO
import kr.alham.playground.dto.inventory.PlayerMaterialInventoryItemDTO
import kr.alham.playground.service.area.AreaService
import kr.alham.playground.service.gather.GatherService
import kr.alham.playground.service.inventory.InventoryService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/field")
@CrossOrigin(origins = ["*"])
class FieldController(
    private val areaService: AreaService,
    private val gatherService: GatherService,
    private val inventoryService: InventoryService
) {


    @GetMapping("/{fieldId}")
    fun getFieldArea(
                     @PathVariable fieldId: Long
    ): FieldAreaDTO{
        val fieldArea = areaService.findFieldArea(fieldId)
        return FieldAreaMapper.fieldAreaToDTO(fieldArea)
    }

    @PostMapping("/gather")
    fun gatherItem(@RequestBody gatherMaterialDTO: GatherMaterialDTO): PlayerMaterialInventoryItemDTO {

        //TODO - 유저 인증 정보 받아서 진행
        val playerId = 1L

        val gatherResult = gatherService.gatherMaterial(gatherMaterialDTO)
        val itemInfo = gatherService.getMaterialItemByName(gatherResult.name)



        inventoryService.saveItemToPlayerInventory(
            playerId,
            itemInfo
        )

        return PlayerMaterialInventoryItemDTO(
            id = itemInfo.id!!,
            name = itemInfo.name,
            itemRarity = gatherResult.itemRarity,
            type = itemInfo.type,
            description = itemInfo.description,
            itemOrder = 0
        )
    }


}