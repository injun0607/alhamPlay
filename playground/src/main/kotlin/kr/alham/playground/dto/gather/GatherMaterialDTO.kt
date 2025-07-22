package kr.alham.playground.dto.gather

class GatherMaterialDTO(
    val areaId: Long,
    val x: Int,
    val y: Int,
) {
    companion object {
        fun of(areaId: Long, x: Int, y: Int): GatherMaterialDTO {
            return GatherMaterialDTO(areaId, x, y)
        }
    }
}