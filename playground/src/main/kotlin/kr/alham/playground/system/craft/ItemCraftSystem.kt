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

    fun craftEquipment(equipmentRecipeDTO: EquipmentRecipeDTO){
        val itemRecipeListMap = itemRecipeLoader.loadEquipmentRecipe()
        val ingredients = equipmentRecipeDTO.ingredients.entries
            .associate { it.key.name to it.value } // Map<String, Int> 형태로 변환

        val equipmentRecipe = findRecipe(ingredients,itemRecipeListMap)
    }


    private fun findRecipe(ingredients: Map<String,Int>,itemRecipeMap: Map<ItemRarity, List<EquipmentRecipe>>): EquipmentRecipe? {

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

    private fun makeRandomItem():EquipmentRecipe{
        //평균점수를 확인해서 랜덤아이템 생성

        TODO()

    }



}