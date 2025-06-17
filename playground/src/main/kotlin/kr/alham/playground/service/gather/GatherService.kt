package kr.alham.playground.service.gather

import kr.alham.playground.domain.item.Material
import kr.alham.playground.dto.craft.MaterialDTO
import kr.alham.playground.dto.gather.GatherMaterialDTO
import kr.alham.playground.repository.item.MaterialRepository
import kr.alham.playground.service.area.AreaService
import kr.alham.playground.system.gather.GatherSystem
import org.springframework.stereotype.Service

@Service
class GatherService(
    private val gatherSystem: GatherSystem,
    private val areaService: AreaService,
    private val materialRepository: MaterialRepository
) {

    //해야하는건 -> GatherService ->
    fun gatherMaterial(gatherMaterialDTO: GatherMaterialDTO):MaterialDTO{
        val area = areaService.findAreaById(gatherMaterialDTO.areaId)
        return gatherSystem.fieldGather(area, gatherMaterialDTO.x, gatherMaterialDTO.y)
    }

    fun getMaterialItemByName(materialName: String): Material{
        return materialRepository.findByName(materialName)
            ?: throw NoSuchElementException("재료 아이템을 찾을 수 없습니다: $materialName")
    }

}