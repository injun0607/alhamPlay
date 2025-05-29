package kr.alham.playground.dto.craft

data class EquipmentRecipeDTO(
    val ingredients: Map<MaterialDTO, Int>,
) {
}