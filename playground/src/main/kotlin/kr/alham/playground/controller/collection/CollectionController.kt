package kr.alham.playground.controller.collection

import kr.alham.playground.dto.collection.EquipmentCollectionDTO
import kr.alham.playground.dto.collection.MaterialCollectionDTO
import kr.alham.playground.dto.collection.PlayerCollectionDTO
import kr.alham.playground.dto.inventory.PlayerEquipmentInventoryItemDTO
import kr.alham.playground.service.collection.CollectionService
import kr.alham.playground.service.inventory.InventoryService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/collection")
@CrossOrigin(origins = ["*"])
class CollectionController(
    private val collectionService: CollectionService,
    private val inventoryService: InventoryService
) {

    @GetMapping
    fun getCollection(): PlayerCollectionDTO{
        val playerid = 1L // 예시로 플레이어 ID를 하드코딩

        return collectionService.getPlayerCollection(playerid)
    }

    @GetMapping("/materials")
    fun getMaterialCollection(): List<MaterialCollectionDTO> {
        val playerId = 1L // 예시로 플레이어 ID를 하드코딩
        return collectionService.getMaterialCollection(playerId)
    }

    @GetMapping("/equipments")
    fun getEquipmentCollection(): List<EquipmentCollectionDTO> {
        val playerId = 1L // 예시로 플레이어 ID를 하드코딩
        return collectionService.getEquipmentCollection(playerId)
    }

    @PostMapping("/register/equipment")
    fun registerCollection(@RequestBody equipmentInventoryItemDTO: PlayerEquipmentInventoryItemDTO): Boolean {
        val playerId = 1L // 예시로 플레이어 ID를 하드코딩

        val item = inventoryService.getEquipmentById(equipmentInventoryItemDTO.id)

        return collectionService.registerCollection(playerId, equipmentInventoryItemDTO.inventoryItemId, item)
    }



}