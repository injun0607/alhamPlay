package kr.alham.playground.system.craft

import kr.alham.playground.common.utils.logger
import kr.alham.playground.domain.item.EquipmentRecipe
import kr.alham.playground.domain.item.ItemRarity
import kr.alham.playground.dto.craft.EquipmentRecipeDTO
import kr.alham.playground.loader.ItemRecipeLoader
import org.springframework.stereotype.Component

@Component
class ItemCraftSystem (
    private val itemRecipeLoader: ItemRecipeLoader
){

    private val log = logger()
    //레시피 받고 -> 해당 레시피 기준으로 -> 레시피 탐색후 있으면 레시피 아이템 뱉고, 없으면 평균점수에 맞는 아이템 뱉기

    fun craftEquipment(equipmentRecipeDTO: EquipmentRecipeDTO): EquipmentRecipe {
        val itemRecipeListMap = itemRecipeLoader.loadEquipmentRecipe()
        val ingredients = equipmentRecipeDTO.ingredients.entries
            .associate { it.key.name to it.value } // Map<String, Int> 형태로 변환

        return findEquipmentRecipe(ingredients,itemRecipeListMap)
            ?: makeRandomEquipment(equipmentRecipeDTO.getIngredientsSumPoint(),itemRecipeListMap) //레시피가 없으면 랜덤아이템 생성
    }


    private fun findEquipmentRecipe(ingredients: Map<String,Int>, itemRecipeMap: Map<ItemRarity, List<EquipmentRecipe>>): EquipmentRecipe? {

        itemRecipeMap.forEach { (rarity, recipes) ->
            recipes.forEach { recipe ->
                if (recipe.ingredients == ingredients) {
                    log.info("Found recipe for rarity: $rarity, recipe: $recipe")
                    return recipe
                }
            }
        }

        return null

    }

    private fun makeRandomEquipment(sumItemPoint: Double, itemRecipeListMap: Map<ItemRarity,List<EquipmentRecipe>>):EquipmentRecipe {
        //평균점수를 확인해서 랜덤아이템 생성
        when(sumItemPoint){
            in 0.0..4.0 -> {
                return itemRecipeListMap[ItemRarity.COMMON]?.random()
                    ?: throw IllegalArgumentException("No common items found")
            }
            in 4.0..8.0 -> {
                return itemRecipeListMap[ItemRarity.UNCOMMON]?.random()
                    ?: throw IllegalArgumentException("No uncommon items found")
            }
            in 8.0..16.0 -> {
                return itemRecipeListMap[ItemRarity.RARE]?.random()
                    ?: throw IllegalArgumentException("No rare items found")
            }
            in 16.0..32.0 -> {
                return itemRecipeListMap[ItemRarity.EPIC]?.random()
                    ?: throw IllegalArgumentException("No epic items found")
            }
            in 32.0..96.0 -> {
                return itemRecipeListMap[ItemRarity.UNIQUE]?.random()
                    ?: throw IllegalArgumentException("No unique items found")
            }in 96.0..128.0 -> {
                return itemRecipeListMap[ItemRarity.LEGENDARY]?.random()
                    ?: throw IllegalArgumentException("No legendary items found")
            }
            else -> {
                return itemRecipeListMap[ItemRarity.COMMON]?.random()
                    ?: throw IllegalArgumentException("Else Case No Common items found")
            }
        }

    }


}