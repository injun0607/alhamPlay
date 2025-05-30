package kr.alham.playground.domain.item

data class EquipmentRecipe(
    val id: Long? = null,
    val name: String,
    val ingredients: Map<String, Int>,
) {
}