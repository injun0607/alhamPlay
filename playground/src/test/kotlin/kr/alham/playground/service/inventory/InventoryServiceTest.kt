package kr.alham.playground.service.inventory

import jakarta.persistence.EntityManager
import kr.alham.playground.domain.inventory.EquipmentInventory
import kr.alham.playground.domain.inventory.EquipmentInventoryItem
import kr.alham.playground.domain.inventory.MaterialInventory
import kr.alham.playground.domain.inventory.MaterialInventoryItem
import kr.alham.playground.domain.item.Equipment
import kr.alham.playground.domain.item.Item
import kr.alham.playground.domain.item.Material
import kr.alham.playground.domain.player.Player
import kr.alham.playground.repository.inventory.EquipmentItemInventoryRepository
import kr.alham.playground.repository.inventory.MaterialItemInventoryRepository
import kr.alham.playground.repository.item.EquipmentRepository
import kr.alham.playground.repository.item.MaterialRepository
import kr.alham.playground.service.player.PlayerService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class InventoryServiceTest {


    @Autowired
    lateinit var inventoryService: InventoryService

    @Autowired
    lateinit var equipmentItemInventoryRepository: EquipmentItemInventoryRepository

    @Autowired
    lateinit var materialItemInventoryRepository: MaterialItemInventoryRepository

    @Autowired
    lateinit var playerService: PlayerService

    @Autowired
    lateinit var equipmentRepository: EquipmentRepository

    @Autowired
    lateinit var materialRepository: MaterialRepository

    @Autowired
    lateinit var entityManager: EntityManager

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







    @Test
    fun getPlayerInventoryTest(){
        //player가 인벤토리 아이템을 가져와야함


    }

    @Test
    fun saveItemToPlayerInventory() {
        //받은 아이템 디비에 저장
//        inventoryService.saveItemToPlayerInventory(playerId, itemId)

    }

    @Test
    fun `saveAndFindEquipmentInventoryTest - 장비아이템 리스트 잘불러오는지 확인`() {
        initPlayerAndInventory()
        val inventory = equipmentItemInventoryRepository.findEquipmentInventoryByPlayerId(1L)
        assertNotNull(inventory)
        assertEquals(1L, inventory?.player?.id)
        assertEquals(0, inventory?.equipmentItemList?.size)

        EquipmentInventoryItem.create(
            inventory!!,
            equipmentOne
        )

        //저장후에 inventory에 아이템이 잘 들어갔는지 확인
        equipmentItemInventoryRepository.save(inventory)

        //잘불러와지나 확인
        println("인벤토리확인")
        equipmentItemInventoryRepository.findEquipmentInventoryByPlayerId(1L)
        assertEquals(1, inventory.equipmentItemList.size)
        assertEquals("TestEquipment", inventory.equipmentItemList[0].equipment.name)

        EquipmentInventoryItem.create(
            inventory,
            equipmentTwo
        )

        equipmentItemInventoryRepository.save(inventory)

        assertEquals(2, inventory.equipmentItemList.size)
        assertEquals("TestEquipment2", inventory.equipmentItemList[1].equipment.name)

    }

    @Test
    fun saveAndFindMaterialInventoryTest() {
        initPlayerAndInventory()
        val materialInventory = materialItemInventoryRepository.findMaterialInventoryByPlayerId(1L)

        assertNotNull(materialInventory)
        assertEquals(1L, materialInventory?.player?.id)
        assertEquals(0, materialInventory?.materialItemList?.size)


        MaterialInventoryItem.create(
            materialInventory!!,
            materialOne
        )

        //저장후에 inventory에 아이템이 잘 들어갔는지 확인
        materialItemInventoryRepository.save(materialInventory)

        //잘불러와지나 확인
        println("인벤토리확인")
        materialItemInventoryRepository.findMaterialInventoryByPlayerId(1L)
        assertEquals(1, materialInventory.materialItemList.size)
        assertEquals("TestMaterial", materialInventory.materialItemList[0].material.name)

        MaterialInventoryItem.create(
            materialInventory,
            materialTwo
        )

        materialItemInventoryRepository.save(materialInventory)

        assertEquals(2, materialInventory.materialItemList.size)
        assertEquals("TestMaterial2", materialInventory.materialItemList[1].material.name)


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
        assertEquals("TestEquipment", playerEquipmentInventory.equipmentItemList[0].equipment.name)
        assertEquals("TestMaterial", playerMaterialInventory.materialItemList[0].material.name)


    }



    private fun initPlayerAndInventory() {
        val player = playerService.savePlayer(
            Player(
                name = "TestPlayer",
            )
        )

        equipmentItemInventoryRepository.save(
            EquipmentInventory(
                player = player
            )
        )

        materialItemInventoryRepository.save(
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