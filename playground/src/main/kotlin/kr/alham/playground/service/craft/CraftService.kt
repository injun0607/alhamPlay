package kr.alham.playground.service.craft

import kr.alham.playground.dto.craft.EquipmentRecipeDTO
import kr.alham.playground.service.inventory.InventoryService
import kr.alham.playground.system.craft.ItemCraftSystem
import org.springframework.stereotype.Service

@Service
class CraftService(
    private val craftSystem: ItemCraftSystem,
    private val inventoryService: InventoryService,
) {

    fun makeEquipment(playerId: Long ,equipmentRecipeDTO: EquipmentRecipeDTO) {
        //검증은 무조건 해야한다. 해당 유저가 아이템이 있는지 검증은 무조건 하기
        //해당 유저의 인벤토리에서 해당 아이템이 카운트만큼있는지 확인 => equipmentRecipe에있는정보 다불러온다음에 카운트하면될듯?

        craftSystem.craftEquipment(equipmentRecipeDTO)
    }


}