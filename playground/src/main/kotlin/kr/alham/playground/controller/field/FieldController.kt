package kr.alham.playground.controller.field

import kr.alham.playground.common.utils.CommonResponse
import kr.alham.playground.common.utils.mapper.FieldAreaMapper
import kr.alham.playground.common.utils.mapper.ItemMapper
import kr.alham.playground.domain.area.FieldType
import kr.alham.playground.dto.field.FieldAreaDTO
import kr.alham.playground.dto.gather.GatherMaterialDTO
import kr.alham.playground.dto.inventory.PlayerMaterialInventoryItemDTO
import kr.alham.playground.service.area.AreaService
import kr.alham.playground.service.gather.GatherService
import kr.alham.playground.service.inventory.InventoryService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/field")
@CrossOrigin(origins = ["*"])
class FieldController(
    private val areaService: AreaService,
    private val gatherService: GatherService,
    private val inventoryService: InventoryService
) {



    @GetMapping("/select")
    fun getSelectField(): CommonResponse<FieldAreaDTO> {
        //TODO - 유저 인증 정보 받아서 진행
        val playerId = 1L

        //레디스 조회해서 -> 레디스에 값이 없으면 null -> 있으면 해당 필드 영역 조회
        val fieldType = areaService.selectFieldArea(playerId)
            ?: return CommonResponse.of(HttpStatus.OK, "No field area selected", null)

        areaService.findFieldAreaByType(fieldType).let{
            return CommonResponse.of(
                HttpStatus.FOUND,
                data = FieldAreaMapper.fieldAreaToDTO(it)
            )
        }
    }

    @PostMapping("/select")
    fun selectFiled(@RequestBody fieldType: FieldType): FieldAreaDTO {
        //TODO - 유저 인증 정보 받아서 진행
        val playerId = 1L

        //레디스 조회해서 -> 레디스에 값이 없으며 선택진행 // 있으면 되돌리기 ->
        areaService.selectFieldArea(playerId)?.let{
            //레디스에 값이 있으면 해당 필드 영역 조회
            //방어코드 영역
            return FieldAreaMapper.fieldAreaToDTO(areaService.findFieldAreaByType(it))
        } ?: run {
            //레디스에 값이 없으면 선택진행
            areaService.saveSelectedField(playerId, fieldType)
            return FieldAreaMapper.fieldAreaToDTO(areaService.findFieldAreaByType(fieldType))
        }
    }

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



        val inventoryId = inventoryService.saveItemToPlayerInventory(
            playerId,
            itemInfo
        )

        val material = inventoryService.getMaterialInventoryItemListByInventoryId(inventoryId).first{
            it.material.id == itemInfo.id
        }

        return ItemMapper.materialInventoryItemToDTO(material)
    }


}