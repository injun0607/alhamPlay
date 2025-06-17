package kr.alham.playground.loader

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import kr.alham.playground.common.RECIPE_JSON_PATH
import kr.alham.playground.domain.item.EquipmentRecipe
import kr.alham.playground.domain.item.ItemRarity
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component

@Component
class ItemRecipeLoader {


    private val mapper = jacksonObjectMapper()

    private val recipePath = RECIPE_JSON_PATH

    @Cacheable("equipmentRecipe")
    fun loadEquipmentRecipe(): Map<ItemRarity,List<EquipmentRecipe>> {
        val fileName = recipePath + "EQUIPMENT_RECIPE.JSON"
        val inputStream = javaClass.getResourceAsStream(fileName)
            ?: throw IllegalArgumentException("Drop table not found for area: ${fileName}")

        val recipeList: Map<ItemRarity,List<EquipmentRecipe>> = mapper.readValue(inputStream)

        requireNotNull(recipeList[ItemRarity.COMMON]) {
            "Common item recipe not found"
        }

        requireNotNull(recipeList[ItemRarity.UNCOMMON]) {
            "Uncommon item recipe not found"
        }

        requireNotNull(recipeList[ItemRarity.RARE]) {
            "Rare item recipe not found"
        }

        requireNotNull(recipeList[ItemRarity.EPIC]) {
            "Epic item recipe not found"
        }

        requireNotNull(recipeList[ItemRarity.UNIQUE]) {
            "Unique item recipe not found"
        }

        requireNotNull(recipeList[ItemRarity.LEGENDARY]) {
            "Legendary item recipe not found"
        }

        return recipeList
    }



}