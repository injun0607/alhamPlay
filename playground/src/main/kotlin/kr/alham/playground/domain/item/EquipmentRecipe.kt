package kr.alham.playground.domain.item

data class EquipmentRecipe(
    val name: String,
    val ingredients: Map<String, Int>,
) {
}