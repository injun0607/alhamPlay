package kr.alham.playground.system.dismantle

import kr.alham.playground.domain.item.Equipment
import kr.alham.playground.domain.item.EquipmentRecipe
import kr.alham.playground.loader.ItemRecipeLoader
import org.springframework.stereotype.Component

@Component
class DismantleSystem(
    private val itemRecipeLoader: ItemRecipeLoader
) {

    fun dismantleEquipment(equipment: Equipment): List<String>{
        val itemRecipeList = itemRecipeLoader.loadEquipmentRecipe()

        val equipmentRecipe = itemRecipeList[equipment.itemRarity]?.find{it.name == equipment.name}
            ?: throw IllegalArgumentException("No recipe found for equipment: ${equipment.name}")

        val ingredients = equipmentRecipe.ingredients.toMutableMap()
        val availableKeys = ingredients.filterValues { it > 0 }.keys.toMutableList()

        val materialNameList: MutableList<String> = mutableListOf()

        //재료 두개만 추출
        repeat(2){
            if(availableKeys.isEmpty()){
                throw IllegalArgumentException("No available ingredients to dismantle for equipment: ${equipment.name}")
            }

            val randomKey = availableKeys.random()
            val amount = ingredients[randomKey] ?: 0

            if( amount > 0) {
                materialNameList.add(randomKey)
                ingredients[randomKey] = amount - 1
                if(ingredients[randomKey] == 0){
                    availableKeys.remove(randomKey) //재료가 0이 되면 제거
                }
            }
        }

        return materialNameList
    }


}