package kr.alham.playground.controller

import jakarta.annotation.PostConstruct
import kr.alham.playground.domain.inventory.EquipmentInventory
import kr.alham.playground.domain.inventory.MaterialInventory
import kr.alham.playground.domain.item.Equipment
import kr.alham.playground.domain.item.Material
import kr.alham.playground.domain.player.Player
import kr.alham.playground.repository.inventory.EquipmentInventoryItemRepository
import kr.alham.playground.repository.inventory.EquipmentInventoryRepository
import kr.alham.playground.repository.inventory.MaterialInventoryItemRepository
import kr.alham.playground.repository.inventory.MaterialInventoryRepository
import kr.alham.playground.repository.item.EquipmentRepository
import kr.alham.playground.repository.item.MaterialRepository
import kr.alham.playground.service.inventory.InventoryService
import kr.alham.playground.service.player.PlayerService
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Transactional
class TestController(
    private val inventoryService: InventoryService,
    private val playerService: PlayerService,
    private val equipmentInventoryRepository: EquipmentInventoryRepository,
    private val materialInventoryRepository: MaterialInventoryRepository,
    private val materialInventoryItemRepository: MaterialInventoryItemRepository,
    private val equipmentInventoryItemRepository: EquipmentInventoryItemRepository,
    private val equipmentRepository: EquipmentRepository,
    private val materialRepository: MaterialRepository
) {



    @PostConstruct
    fun init(){

        var equipmentOne : Equipment = Equipment(
            name = "TestEquipment",
            description = "Test Equipment Description"
        )
        var equipmentTwo : Equipment = Equipment(
            name = "TestEquipment2",
            description = "Test Equipment Description 2"
        )
        var equipmentThree : Equipment = Equipment(
            name = "TestEquipment3",
            description = "Test Equipment Description 3"
        )

        var materialOne: Material = Material(
            name = "TestMaterial",
            description = "Test Material Description"
        )

        var materialTwo: Material = Material(
            name = "TestMaterial2",
            description = "Test Material Description 2"
        )

        var materialThree: Material = Material(
            name = "TestMaterial3",
            description = "Test Material Description 3"
        )

        val player = playerService.savePlayer(
            Player(
                name = "TestPlayer",
            )
        )

        equipmentInventoryRepository.save(
            EquipmentInventory(
                player = player
            )
        )

        materialInventoryRepository.save(
            MaterialInventory(
                player = player
            )
        )

        equipmentRepository.save(
            equipmentOne
        )

        equipmentRepository.save(
            equipmentTwo
        )

        equipmentRepository.save(
            equipmentThree
        )

        materialRepository.save(
            materialOne
        )
        materialRepository.save(
            materialTwo
        )
        materialRepository.save(
            materialThree
        )


    }

    @GetMapping("/test")
    fun inventoryTest(): Material {

        val material = materialRepository.findById(1L).orElseThrow()
        println(inventoryService.getMaterialInventoryByPlayerId(1L).materialItemList.size)
        inventoryService.saveItemToPlayerInventory(1L,material)
        println(inventoryService.getMaterialInventoryByPlayerId(1L).materialItemList.size)
        return inventoryService.getMaterialInventoryByPlayerId(1L).materialItemList[0].material
    }

    @GetMapping("/test/material")
    fun materialTest(): Material {
        return inventoryService.getMaterialInventoryByPlayerId(1L).materialItemList[0].material
    }


    @GetMapping("/test/inven")
    fun invenTest() {

        val material = equipmentRepository.findById(1L).orElseThrow()
        val material2 = equipmentRepository.findById(2L).orElseThrow()
        val material3 = equipmentRepository.findById(3L).orElseThrow()

        inventoryService.saveItemToPlayerInventory(1L,material)
        inventoryService.saveItemToPlayerInventory(1L,material2)
        inventoryService.saveItemToPlayerInventory(1L,material3)

        println("인벤토리 시작")
        inventoryService.getPlayerInventory(1L)
        println("인벤토리 끝")
    }

}