package kr.alham.playground.loader

import kr.alham.playground.domain.item.ItemRarity
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ItemRecipeLoaderTest{

    @Test
    fun loadEquipmentRecipeTest() {
        val itemRecipeLoader = ItemRecipeLoader()

        val equipmentRecipes = itemRecipeLoader.loadEquipmentRecipe()

        assertNotNull(equipmentRecipes)
        assertTrue(equipmentRecipes.isNotEmpty())

        assertEquals(true, equipmentRecipes.containsKey(ItemRarity.COMMON))
        equipmentRecipes[ItemRarity.COMMON]?.let { recipes ->
            assertTrue(recipes.isNotEmpty())
            assertEquals(50, recipes.size)
        }
        assertEquals(true, equipmentRecipes.containsKey(ItemRarity.UNCOMMON))
        equipmentRecipes[ItemRarity.UNCOMMON]?.let { recipes ->
            assertTrue(recipes.isNotEmpty())
            assertEquals(40, recipes.size)
        }
        assertEquals(true, equipmentRecipes.containsKey(ItemRarity.RARE))
        equipmentRecipes[ItemRarity.RARE]?.let { recipes ->
            assertTrue(recipes.isNotEmpty())
            assertEquals(30, recipes.size)
        }
        assertEquals(true, equipmentRecipes.containsKey(ItemRarity.EPIC))
        equipmentRecipes[ItemRarity.EPIC]?.let { recipes ->
            assertTrue(recipes.isNotEmpty())
            assertEquals(20, recipes.size)
        }
        assertEquals(true, equipmentRecipes.containsKey(ItemRarity.UNIQUE))
        equipmentRecipes[ItemRarity.UNIQUE]?.let { recipes ->
            assertTrue(recipes.isNotEmpty())
            assertEquals(10, recipes.size)
        }
        assertEquals(true, equipmentRecipes.containsKey(ItemRarity.LEGENDARY))
        equipmentRecipes[ItemRarity.LEGENDARY]?.let { recipes ->
            assertTrue(recipes.isNotEmpty())
            assertEquals(5, recipes.size)
        }

    }




}