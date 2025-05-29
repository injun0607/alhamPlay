package kr.alham.playground.system.craft

import kr.alham.playground.domain.item.EquipmentRecipe
import kr.alham.playground.domain.item.ItemRarity
import kr.alham.playground.loader.ItemRecipeLoader
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class ItemCraftSystemTest{



    lateinit var itemRecipeLoader: ItemRecipeLoader
    lateinit var recipeMap : Map<ItemRarity,List<EquipmentRecipe>>

    @BeforeEach
    fun setup() {
        itemRecipeLoader = ItemRecipeLoader()
        recipeMap = itemRecipeLoader.loadEquipmentRecipe()
    }

    @Test
    fun itemEqualCheckTest(){

        val equipment = EquipmentRecipe(
            "Test Item",
            mapOf(
                "Test Material" to 1,
                "Test Material2" to 2
            )
        )


        val ingredients = mapOf(
            "Test Material" to 1,
            "Test Material2" to 2
        )

        assertEquals(true, equipment.ingredients == ingredients, "Ingredients should be equal")
    }


    private fun findRecipe(ingredients: Map<String,Int>,itemRecipeMap: Map<ItemRarity, List<EquipmentRecipe>>): EquipmentRecipe? {
        itemRecipeMap.forEach { (rarity, recipes) ->
            recipes.forEach { recipe ->
                if (recipe.ingredients == ingredients) {
                    return recipe
                }
            }
        }
        return null
    }


    @Test
    fun `findRecipeTest`(){

        /**
         * "name": "BeachsandCrabFang",
         *       "ingredients": {
         *         "WetGem": 1,
         *         "BeachSand": 1,
         *         "CrabClaw": 1,
         *         "DeepCoral": 1
         *       }
         *       {
         *       "name": "HellflamesoulTotem",
         *       "ingredients": {
         *         "HellflameSoul": 4
         *       }
         *     }
         */

        val ingredients = mapOf(
            "WetGem" to 1,
            "BeachSand" to 1,
            "CrabClaw" to 1,
            "DeepCoral" to 1
        )

        val ingredients2 = mapOf(
            "HellflameSoul" to 4
        )

        val recipe =findRecipe(ingredients,recipeMap)
        val recipe2 = findRecipe(ingredients2,recipeMap)


        assertEquals("BeachsandCrabFang", recipe?.name, "Recipe name should be BeachsandCrabFang")

    }

}