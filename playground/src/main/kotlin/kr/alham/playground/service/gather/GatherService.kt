package kr.alham.playground.service.gather

import kr.alham.playground.dto.craft.MaterialDTO
import kr.alham.playground.dto.gather.GatherMaterialDTO
import kr.alham.playground.service.area.AreaService
import kr.alham.playground.system.gather.GatherSystem
import org.springframework.stereotype.Service

@Service
class GatherService(
    private val gatherSystem: GatherSystem,
    private val areaService: AreaService,
) {

    //해야하는건 -> GatherService ->
    fun gatherMaterial(gatherMaterialDTO: GatherMaterialDTO):MaterialDTO{
        val area = areaService.findAreaById(gatherMaterialDTO.areaId)
        val areaTiles =
        return gatherSystem.fieldGather(area, gatherMaterialDTO.x, gatherMaterialDTO.y)
    }




}