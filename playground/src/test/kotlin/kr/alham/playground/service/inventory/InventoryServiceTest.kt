package kr.alham.playground.service.inventory

import kr.alham.playground.domain.inventory.EquipmentInventory
import kr.alham.playground.domain.inventory.EquipmentInventoryItem
import kr.alham.playground.domain.inventory.MaterialInventory
import kr.alham.playground.domain.inventory.MaterialInventoryItem
import kr.alham.playground.domain.item.Equipment
import kr.alham.playground.domain.item.Material
import kr.alham.playground.domain.player.Player
import kr.alham.playground.repository.inventory.EquipmentInventoryItemRepository
import kr.alham.playground.repository.inventory.EquipmentInventoryRepository
import kr.alham.playground.repository.inventory.MaterialInventoryItemRepository
import kr.alham.playground.repository.inventory.MaterialInventoryRepository
import kr.alham.playground.repository.item.EquipmentRepository
import kr.alham.playground.repository.item.MaterialRepository
import kr.alham.playground.service.player.PlayerService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class InventoryServiceTest {


    @Autowired
    lateinit var inventoryService: InventoryService

    @Autowired
    lateinit var equipmentInventoryRepository: EquipmentInventoryRepository

    @Autowired
    lateinit var materialInventoryRepository: MaterialInventoryRepository

    @Autowired
    lateinit var playerService: PlayerService

    @Autowired
    lateinit var equipmentRepository: EquipmentRepository

    @Autowired
    lateinit var materialRepository: MaterialRepository

    @Autowired
    lateinit var materialInventoryItemRepository: MaterialInventoryItemRepository

    @Autowired
    lateinit var equipmentInventoryItemRepository: EquipmentInventoryItemRepository

    var equipmentOne : Equipment = Equipment(
        name = "TestEnvironmentTestEquipment",
        description = "Test Equipment Description"
    )
    var equipmentTwo : Equipment = Equipment(
        name = "TestEnvironmentTestEquipment2",
        description = "Test Equipment Description 2"
    )
    var equipmentThree : Equipment = Equipment(
        name = "TestEnvironmentTestEquipment3",
        description = "Test Equipment Description 3"
    )

    var materialOne: Material = Material(
        name = "TestEnvironmentTestMaterial",
        description = "Test Material Description"
    )

    var materialTwo: Material = Material(
        name = "TestEnvironmentTestMaterial2",
        description = "Test Material Description 2"
    )

    var materialThree: Material = Material(
        name = "TestEnvironmentTestMaterial3",
        description = "Test Material Description 3"
    )







    @Test
    fun getPlayerInventoryTest(){
        //player가 인벤토리 아이템을 가져와야함
        initPlayerAndInventory()

        inventoryService.saveItemToPlayerInventory(1L, equipmentOne)
        inventoryService.saveItemToPlayerInventory(1L, equipmentTwo)
        inventoryService.saveItemToPlayerInventory(1L, equipmentThree)

        inventoryService.saveItemToPlayerInventory(1L, materialOne)
        inventoryService.saveItemToPlayerInventory(1L, materialTwo)
        inventoryService.saveItemToPlayerInventory(1L, materialThree)

        println("인벤토리확인")
        val inventory = inventoryService.getPlayerInventory(1L)
        println("인벤토리확인 끝")

        assertEquals(3,inventory.playerMaterialInventory.materialItemList.size)
        assertEquals(3,inventory.playerEquipmentInventory.equipmentItemList.size)

    }

    @Test
    fun saveItemToPlayerInventory() {
        //받은 아이템 디비에 저장
//        inventoryService.saveItemToPlayerInventory(playerId, itemId)

    }

    @Test
    fun `saveAndFindEquipmentInventoryTest - 장비아이템 리스트 잘불러오는지 확인`() {
        initPlayerAndInventory()
        val inventory = equipmentInventoryRepository.findEquipmentInventoryByPlayerId(1L)
        assertNotNull(inventory)
        assertEquals(1L, inventory?.player?.id)
        assertEquals(0, inventory?.equipmentItemList?.size)

        EquipmentInventoryItem.create(
            inventory!!,
            equipmentOne
        )

        //저장후에 inventory에 아이템이 잘 들어갔는지 확인
        equipmentInventoryRepository.save(inventory)

        //잘불러와지나 확인
        println("인벤토리확인")
        equipmentInventoryRepository.findEquipmentInventoryByPlayerId(1L)
        assertEquals(1, inventory.equipmentItemList.size)
        assertEquals("TestEnvironmentTestEquipment", inventory.equipmentItemList[0].equipment.name)

        EquipmentInventoryItem.create(
            inventory,
            equipmentTwo
        )

        equipmentInventoryRepository.save(inventory)

        assertEquals(2, inventory.equipmentItemList.size)
        assertEquals("TestEnvironmentTestEquipment2", inventory.equipmentItemList[1].equipment.name)

    }

    @Test
    fun saveAndFindMaterialInventoryTest() {
        initPlayerAndInventory()
        val materialInventory = materialInventoryRepository.findMaterialInventoryByPlayerId(1L)

        assertNotNull(materialInventory)
        assertEquals(1L, materialInventory?.player?.id)
        assertEquals(0, materialInventory?.materialItemList?.size)


        MaterialInventoryItem.create(
            materialInventory!!,
            materialOne
        )

        //저장후에 inventory에 아이템이 잘 들어갔는지 확인
        materialInventoryRepository.save(materialInventory)

        //잘불러와지나 확인
        println("인벤토리확인")
        materialInventoryRepository.findMaterialInventoryByPlayerId(1L)
        assertEquals(1, materialInventory.materialItemList.size)
        assertEquals("TestEnvironmentTestMaterial", materialInventory.materialItemList[0].material.name)

        MaterialInventoryItem.create(
            materialInventory,
            materialTwo
        )

        materialInventoryRepository.save(materialInventory)

        assertEquals(2, materialInventory.materialItemList.size)
        assertEquals("TestEnvironmentTestMaterial2", materialInventory.materialItemList[1].material.name)


    }

    @Test
    fun `saveItemToPlayerInventory - 부트 테스트`(){
        initPlayerAndInventory()

        //플레이어 아이템 저장
        inventoryService.saveItemToPlayerInventory(1L,equipmentOne)
        inventoryService.saveItemToPlayerInventory(1L,materialOne)


        //플레이어 인벤토리 조회
        val playerEquipmentInventory = inventoryService.getEquipmentInventoryByPlayerId(1L)
        val playerMaterialInventory = inventoryService.getMaterialInventoryByPlayerId(1L)

        //아이템 있는지 확인
        assertEquals(1, playerEquipmentInventory.equipmentItemList.size)
        assertEquals(1, playerMaterialInventory.materialItemList.size)

        // 아이템 이름 확인
        assertEquals("TestEnvironmentTestEquipment", playerEquipmentInventory.equipmentItemList[0].equipment.name)
        assertEquals("TestEnvironmentTestMaterial", playerMaterialInventory.materialItemList[0].material.name)


    }

    @Test
    fun getMaterialInventoryItemListByInventoryIdTest(){
        initPlayerAndInventory()

        inventoryService.saveItemToPlayerInventory(1L, equipmentOne)
        inventoryService.saveItemToPlayerInventory(1L, equipmentTwo)
        inventoryService.saveItemToPlayerInventory(1L, equipmentThree)

        val inventoryItemList =  equipmentInventoryItemRepository.getEquipmentInventoryItemListByInventoryId(1L)

        assertNotNull(inventoryItemList)
        assertEquals(3, inventoryItemList.size)

        assertEquals("TestEnvironmentTestEquipment", inventoryItemList[0].equipment.name)
        assertEquals("TestEnvironmentTestEquipment2", inventoryItemList[1].equipment.name)
        assertEquals("TestEnvironmentTestEquipment3", inventoryItemList[2].equipment.name)
    }



    private fun initPlayerAndInventory() {
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

}