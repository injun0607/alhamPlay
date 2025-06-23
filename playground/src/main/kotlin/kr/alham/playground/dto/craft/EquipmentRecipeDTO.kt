package kr.alham.playground.dto.craft

data class EquipmentRecipeDTO(
    val ingredients: Map<MaterialDTO, Int>,
) {
    fun getIngredientsSumPoint(): Double {
        if (ingredients.isEmpty()) return 0.0
        // ingredients의 각 MaterialDTO의 itemRarity 값을 가져와서 평균을 계산

        return ingredients.entries.map{(materialDTO, quantity) ->
            materialDTO.itemRarity.value * quantity
        }.sum().toDouble()
    }

    companion object {
        fun fromIngredientsInfo(ingredientsInfoDTOList: IngredientsInfoDTOList): EquipmentRecipeDTO {
            return ingredientsInfoDTOList.ingredients
                .associate { materialInfo ->
                    MaterialDTO(
                        itemRarity = materialInfo.itemRarity,
                        name = materialInfo.name
                    ) to materialInfo.quantity
                }.let { ingredientsMap ->
                    EquipmentRecipeDTO(ingredientsMap)
                }
        }
    }

}