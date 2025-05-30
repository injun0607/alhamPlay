package kr.alham.playground.dto.craft

import kr.alham.playground.domain.item.ItemRarity
import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test

class EquipmentRecipeDTOTest{



    @Test
    fun `getIngredientsAverageTest`() {
        val material1 = MaterialDTO(itemRarity = ItemRarity.COMMON, name = "Wood") //1
        val material2 = MaterialDTO(itemRarity = ItemRarity.RARE, name = "Iron") //4
        val material3 = MaterialDTO(itemRarity = ItemRarity.EPIC, name = "Diamond") // 8

        val ingredients = mapOf(
            material1 to 4, //2
            material2 to 3, //12
            material3 to 1 //8
        )

        val material4 = MaterialDTO(itemRarity = ItemRarity.UNCOMMON, name = "Wood") //2
        val material5 = MaterialDTO(itemRarity = ItemRarity.RARE, name = "Iron") //4
        val material6 = MaterialDTO(itemRarity = ItemRarity.LEGENDARY, name = "Diamond") // 32

        val ingredients2 = mapOf(
            material4 to 2,
            material5 to 1,
            material6 to 1 //32
        )
        val recipe = EquipmentRecipeDTO(ingredients)
        val recipe2 = EquipmentRecipeDTO(ingredients2)

        // 평균 계산: (1 + 2 + 3) / 3 = 2.0
        assertEquals(3.0, recipe.getIngredientsSumPoint(), "Average of item rarities should be 3.0")
        assertEquals(10.0, recipe2.getIngredientsSumPoint(), "Average of item rarities should be 8.0")
    }

}