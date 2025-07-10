package kr.alham.playground.service.inventory

import kr.alham.playground.common.utils.mapper.ItemMapper
import kr.alham.playground.domain.inventory.EquipmentInventory
import kr.alham.playground.domain.inventory.EquipmentInventoryItem
import kr.alham.playground.domain.inventory.MaterialInventory
import kr.alham.playground.domain.inventory.MaterialInventoryItem
import kr.alham.playground.domain.item.*
import kr.alham.playground.dto.craft.EquipmentRecipeDTO
import kr.alham.playground.dto.craft.IngredientsInfoDTOList
import kr.alham.playground.dto.inventory.PlayerEquipmentInventoryDTO
import kr.alham.playground.dto.inventory.PlayerInventoryDTO
import kr.alham.playground.dto.inventory.PlayerMaterialInventoryDTO
import kr.alham.playground.repository.inventory.EquipmentInventoryItemRepository
import kr.alham.playground.repository.inventory.EquipmentInventoryRepository
import kr.alham.playground.repository.inventory.MaterialInventoryItemRepository
import kr.alham.playground.repository.inventory.MaterialInventoryRepository
import kr.alham.playground.repository.item.EquipmentRepository
import kr.alham.playground.repository.item.MaterialRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.math.log

@Service
class InventoryService(
    private val materialItemInventoryRepository: MaterialInventoryRepository,
    private val materialInventoryItemRepository: MaterialInventoryItemRepository,
    private val equipmentInventoryRepository: EquipmentInventoryRepository,
    private val equipmentInventoryItemRepository: EquipmentInventoryItemRepository,
    private val equipmentRepository: EquipmentRepository,
    private val materialRepository: MaterialRepository,
) {

    fun getPlayerInventory(playerId: Long): PlayerInventoryDTO{
        println("getPlayerInventory called with playerId: $playerId")
        val equipmentInventory =  getEquipmentInventoryByPlayerId(playerId)
        println("Equipment Inventory check finished")
        val materialInventory = getMaterialInventoryByPlayerId(playerId)
        println("Material Inventory check finished")
        val equipmentInventoryId = requireNotNull(equipmentInventory.id) { "Equipment inventory ID must not be null" }
        val materialInventoryId = requireNotNull(materialInventory.id) { "Material inventory ID must not be null" }

        println("Equipment Inventory Check")
        val equipmentInventoryItemList = getEquipmentInventoryItemListByInventoryId(equipmentInventoryId)
        val materialInventoryItemList = getMaterialInventoryItemListByInventoryId(materialInventoryId)

        println("Equipment Inventory Check Finished")

        val equipmentInventoryItemListDTO = equipmentInventoryItemList.map {
            ItemMapper.equipmentInventoryItemToDTO(it)
        }

        val materialInventoryItemListDTO = materialInventoryItemList.map {
            ItemMapper.materialInventoryItemToDTO(it)
        }

        return PlayerInventoryDTO(
            playerId = playerId,
            playerMaterialInventory = PlayerMaterialInventoryDTO.from(materialInventoryItemListDTO),
            playerEquipmentInventory = PlayerEquipmentInventoryDTO.from(equipmentInventoryItemListDTO)
        )
    }

    //플레이어 인벤토리에 신규 아이템을 저장하는 로직
    /**
     * return playerInventoryId
     */
    @Transactional
    fun saveItemToPlayerInventory(playerId: Long, itemInfo: Item): Long {
        // 플레이어의 인벤토리에 아이템을 저장하는 로직
        // 예: 데이터베이스에 아이템 정보를 저장

        val itemId = requireNotNull(itemInfo.id) { "Item ID must not be null" }

        var result = 0L;

        when(itemInfo.type){
            ItemType.EQUIPMENT -> {
                // 장비 아이템을 인벤토리에 저장하는 로직
                val equipmentInventory = getEquipmentInventoryByPlayerId(playerId)
                val itemOrder = equipmentInventory.equipmentItemList.size + 1
                val equipment = getEquipmentById(itemId)
                EquipmentInventoryItem.create(equipmentInventory,equipment,itemOrder)
                result = equipmentInventory.id ?: throw IllegalArgumentException("Equipment inventory ID must not be null")
            }
            ItemType.MATERIAL -> {
                // 재료 아이템을 인벤토리에 저장하는 로직
                // materialItemInventoryRepository.save(itemInfo)
                val materialInventory = getMaterialInventoryByPlayerId(playerId)
                val itemOrder = materialInventory.materialItemList.size + 1
                val material = getMaterialById(itemId)
                //있으면 quantity를 증가시키고 없으면 새로 생성
                val existingItem = materialInventory.materialItemList.find { it.material.id == material.id }
                if (existingItem != null) {
                    existingItem.quantity += 1
                }else{
                    MaterialInventoryItem.create(materialInventory, material, itemOrder)
                }

                result = materialInventory.id ?: throw IllegalArgumentException("Material inventory ID must not be null")
            }
        }

        return result
    }

    @Transactional
    fun saveInventoryMaterialByNameList(playerId: Long ,nameList :List<String>): List<Material> {

        // 플레이어의 인벤토리에 재료 아이템을 이름으로 저장하는 로직
        val materialInventory = getMaterialInventoryByPlayerId(playerId)
        val materialList = getMaterialByNameList(nameList)

        materialList.forEach { material ->
            //있으면 quantity를 증가시키고 없으면 새로 생성
            val existingItem = materialInventory.materialItemList.find { it.material.id == material.id }
            if (existingItem != null) {
                existingItem.quantity += 1
            } else {
                val itemOrder = materialInventory.materialItemList.size + 1
                MaterialInventoryItem.create(materialInventory, material, itemOrder)
            }
        }

        return materialList
    }


    @Transactional
    fun deleteMaterialItemByRecipe(playerId: Long, ingredientsInfoDTOList: IngredientsInfoDTOList) {
        // 플레이어의 인벤토리에서 아이템을 삭제하는 로직
        val playerMaterialInventory = getMaterialInventoryByPlayerId(playerId)
        //검증하고 -> 삭제를 같이 하자
        //레시피의 아이템과 개수를 같이 확인
        val ingredients = ingredientsInfoDTOList.ingredients
        val materialItemList = playerMaterialInventory.materialItemList


        ingredients.forEach { ingredient ->
            val materialItem = requireNotNull(materialItemList.find{it.id == ingredient.inventoryItemId})
            check(ingredient.quantity <= materialItem.quantity) {
                "Not enough material: ${ingredient.name} for player ID: $playerId"
            }

        }

        ingredients.forEach { ingredient ->
            val materialItem = requireNotNull(materialItemList.find { it.id == ingredient.inventoryItemId})
            materialItem.quantity -= ingredient.quantity
            if(materialItem.quantity == 0 ){
                materialItemList.remove(materialItem)
            }
        }
    }

    @Transactional
    fun deletePlayerInventoryEquipmentByInventoryItemId(playerId: Long,inventoryItemId: Long, equipmentId: Long){
        val equipmentInventory =  getEquipmentInventoryByPlayerId(playerId)
        val inventoryItem =  equipmentInventory.equipmentItemList.find { it.id == inventoryItemId }
            ?: throw IllegalArgumentException("Equipment inventory item not found with ID: $inventoryItemId")

        if(inventoryItem.equipment.id == equipmentId) {
            //장비 아이템이 일치하면 삭제
            equipmentInventory.equipmentItemList.remove(inventoryItem)
        }else{
            throw IllegalArgumentException("Equipment ID does not match for inventory item ID: $inventoryItemId")
        }
    }


    fun getPlayerEquipmentInventoryItemByEquipmentId(playerId: Long, equipmentId: Long): EquipmentInventoryItem {
        // 플레이어의 장비 인벤토리에서 특정 장비 아이템을 가져오는 로직
        return equipmentInventoryItemRepository.getEquipmentInventoryItemsByIdAndEquipmentId(
            id = playerId,
            equipmentId = equipmentId
        )?: throw IllegalArgumentException("Equipment inventory item not found for player ID: $playerId and equipment ID: $equipmentId")
    }

    fun getEquipmentInventoryByPlayerId(playerId: Long): EquipmentInventory {
        // 플레이어의 장비 인벤토리를 가져오는 로직
        return equipmentInventoryRepository.findEquipmentInventoryByPlayerId(playerId)
            ?: throw IllegalArgumentException("Equipment inventory not found for player ID: $playerId")
    }

    fun getEquipmentById(equipmentId: Long): Equipment {
        // 장비 아이템을 ID로 가져오는 로직
        return equipmentRepository.findById(equipmentId)
            .orElseThrow { IllegalArgumentException("Equipment not found with ID: $equipmentId") }
    }

    fun getMaterialInventoryByPlayerId(playerId: Long): MaterialInventory {
        // 플레이어의 재료 인벤토리를 가져오는 로직
        return materialItemInventoryRepository.findMaterialInventoryByPlayerId(playerId)
            ?: throw IllegalArgumentException("Material inventory not found for player ID: $playerId")
    }

    fun getMaterialById(materialId: Long): Material {
        // 재료 아이템을 ID로 가져오는 로직
        return materialRepository.findById(materialId)
            .orElseThrow { IllegalArgumentException("Material not found with ID: $materialId") }
    }

    fun getMaterialInventoryItemListByInventoryId(inventoryItemId: Long): List<MaterialInventoryItem> {
        return materialInventoryItemRepository.getMaterialInventoryItemListByInventoryId(inventoryItemId)
    }

    fun getEquipmentInventoryItemListByInventoryId(inventoryItemId: Long): List<EquipmentInventoryItem> {
        return equipmentInventoryItemRepository.getEquipmentInventoryItemListByInventoryId(inventoryItemId)
    }

    fun getEquipmentByName(equipmentName: String): Equipment {
        // 장비 아이템을 이름으로 가져오는 로직
        return equipmentRepository.findEquipmentByName(equipmentName)
            ?: throw IllegalArgumentException("Equipment not found with name: $equipmentName")
    }

    fun getMaterialByNameList(materialNameList: List<String>): List<Material> {
        // 재료 아이템을 이름으로 가져오는 로직
        return materialRepository.findByNameIn(materialNameList)
    }




}