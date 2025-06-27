package kr.alham.playground.service.craft

import kr.alham.playground.domain.item.Equipment
import kr.alham.playground.dto.craft.EquipmentRecipeDTO
import kr.alham.playground.dto.craft.IngredientsInfoDTOList
import kr.alham.playground.dto.inventory.PlayerEquipmentInventoryItemDTO
import kr.alham.playground.service.inventory.InventoryService
import kr.alham.playground.system.craft.ItemCraftSystem
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CraftService(
    private val craftSystem: ItemCraftSystem,
    private val inventoryService: InventoryService,
) {


    @Transactional
    fun makeEquipment(playerId: Long,ingredientsInfoDTOList: IngredientsInfoDTOList): Equipment {
        //검증은 무조건 해야한다. 해당 유저가 아이템이 있는지 검증은 무조건 하기
        val equipmentRecipeDTO = EquipmentRecipeDTO.fromIngredientsInfo(ingredientsInfoDTOList)
        val recipe = craftSystem.craftEquipment(equipmentRecipeDTO)
        val equipment = inventoryService.getEquipmentByName(recipe.name)

        //기존 재료 검증 및 삭제
        inventoryService.deleteMaterialItemByRecipe(playerId,ingredientsInfoDTOList)

        //장비아이템 저장
        val inventoryId = inventoryService.saveItemToPlayerInventory(
            playerId,
            equipment
        )

        return equipment
    }


}