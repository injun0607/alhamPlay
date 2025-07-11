package kr.alham.playground.service.collection

import kr.alham.playground.domain.collection.PlayerMaterialCollection
import kr.alham.playground.domain.enums.CollectionLevelEnums
import kr.alham.playground.repository.collection.PlayerEquipmentCollectionRepository
import kr.alham.playground.repository.collection.PlayerMaterialCollectionRepository
import kr.alham.playground.repository.item.EquipmentRepository
import kr.alham.playground.repository.item.MaterialRepository
import kr.alham.playground.service.inventory.InventoryService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CollectionServiceTest{

    @Autowired
    lateinit var collectionService: CollectionService

    @Autowired
    lateinit var materialRepository: MaterialRepository

    @Autowired
    lateinit var equipmentRepository: EquipmentRepository

    @Autowired
    lateinit var playerMaterialCollectionRepository: PlayerMaterialCollectionRepository

    @Autowired
    lateinit var playerEquipmentCollectionRepository: PlayerEquipmentCollectionRepository

    @Autowired
    lateinit var inventoryService: InventoryService

    @Test
    fun `testCollectionRepositoryExsitedMethod`(){

        val playerId = 1L
        val materialId = 1L
        val equipmentId = 1L

        // Test Material Collection Repository
        assertFalse(playerMaterialCollectionRepository.existsPlayerMaterialCollectionByPlayerIdAndMaterialId(playerId, materialId))

        // Test Equipment Collection Repository
        assertFalse(playerEquipmentCollectionRepository.existsPlayerEquipmentCollectionByPlayerIdAndEquipmentId(playerId, equipmentId))



    }

    @Test
    fun collectionSaveTest(){


        val playerId = 1L
        val material = materialRepository.findById(1L).orElseThrow { NoSuchElementException("Material not found") }
        val equipment = equipmentRepository.findById(1L).orElseThrow { NoSuchElementException("Equipment not found") }


        // Save Material Collection
        collectionService.saveCollection(playerId, material)
        assertTrue(playerMaterialCollectionRepository.existsPlayerMaterialCollectionByPlayerIdAndMaterialId(playerId, material.id!!))

        // Save Equipment Collection
        collectionService.saveCollection(playerId,equipment)
        assertTrue(playerEquipmentCollectionRepository.existsPlayerEquipmentCollectionByPlayerIdAndEquipmentId(playerId, equipment.id!!))

        val collection = collectionService.getPlayerCollection(1L)

        assertEquals(1, collection.materialCollectionList.count { it.isCollected })
        assertEquals(1, collection.equipmentCollectionList.count { it.isCollected })

    }


    @Test
    fun `getPlayerCollectionTest - playerCollection테스트`(){

        val materialOne = materialRepository.findById(1L).orElseThrow { NoSuchElementException("Material not found") }
        val materialTwo = materialRepository.findById(2L).orElseThrow { NoSuchElementException("Material not found") }
        val materialThree = materialRepository.findById(3L).orElseThrow { NoSuchElementException("Material not found") }

        val equipmentOne = equipmentRepository.findById(1L).orElseThrow { NoSuchElementException("Equipment not found") }
        val equipmentTwo = equipmentRepository.findById(2L).orElseThrow { NoSuchElementException("Equipment not found") }
        val equipmentThree = equipmentRepository.findById(3L).orElseThrow { NoSuchElementException("Equipment not found") }


        collectionService.saveCollection(1L, materialOne)
        collectionService.saveCollection(1L, materialTwo)
        collectionService.saveCollection(1L, materialThree)
        collectionService.saveCollection(1L, equipmentOne)
        collectionService.saveCollection(1L, equipmentTwo)
        collectionService.saveCollection(1L, equipmentThree)


        val playerCollection = collectionService.getPlayerCollection(1L)

        
        val materialAll =  materialRepository.findAll()
        val equipmentAll = equipmentRepository.findAll()


        assertEquals(materialAll.size , playerCollection.materialCollectionList.size)
        assertEquals(equipmentAll.size , playerCollection.equipmentCollectionList.size)

        assertEquals(3, playerCollection.materialCollectionList.count { it.isCollected })
        assertEquals(3, playerCollection.equipmentCollectionList.count { it.isCollected })


    }

    @Test
    fun updateEquipmentCollectionTest(){

        val playerId = 1L
        val equipment = equipmentRepository.findById(1L).orElseThrow { NoSuchElementException("Equipment not found") }
        val equipmentTwo = equipmentRepository.findById(2L).orElseThrow { NoSuchElementException("Equipment not found") }

        // Update Equipment Collection
        //해당장비 없을떄 방어코드 작동확인
        collectionService.updateEquipmentCollection(playerId, equipment)

        // Verify the update
        collectionService.isExistsCollection(playerId, equipment).also {
            assertTrue(it, "Equipment collection should exist after update")
        }

        collectionService.getPlayerEquipmentCollectionByEquipmentId(equipment.id!!).also{
            assertEquals(1,it.quantity)
        }

        //있는 장비 확인
        collectionService.saveCollection(playerId, equipmentTwo)
        collectionService.updateEquipmentCollection(playerId, equipmentTwo)

        collectionService.getPlayerEquipmentCollectionByEquipmentId(equipmentTwo.id!!).also {
            assertEquals(1, it.quantity)
            assertEquals(CollectionLevelEnums.LEVEL1, it.level)
        }

        collectionService.updateEquipmentCollection(playerId, equipmentTwo)
        collectionService.updateEquipmentCollection(playerId, equipmentTwo)
        collectionService.updateEquipmentCollection(playerId, equipmentTwo)
        collectionService.updateEquipmentCollection(playerId, equipmentTwo)

        collectionService.getPlayerEquipmentCollectionByEquipmentId(equipmentTwo.id!!).also {
            assertEquals(0, it.quantity)
            assertEquals(CollectionLevelEnums.LEVEL2 , it.level)
        }

    }

    @Test
    fun registerCollectionTest(){
        // Given
        val playerId = 1L
        val equipment = equipmentRepository.findById(1L)

        inventoryService.saveItemToPlayerInventory(playerId,equipment.get())
        val playerInventory = inventoryService.getEquipmentInventoryByPlayerId(playerId)
        val equipmentItem = playerInventory.equipmentItemList[0]
        // When
        collectionService.registerCollection(1L,equipmentItem.id!! ,equipmentItem.equipment)
        // Then
        val registeredInventory = inventoryService.getEquipmentInventoryByPlayerId(playerId)

        assertEquals(0,registeredInventory.equipmentItemList.size)
        collectionService.getPlayerEquipmentCollectionByEquipmentId(equipmentItem.id!!).also {
            assertEquals(1, it.quantity)
            assertEquals(CollectionLevelEnums.LEVEL1, it.level)
        }

    }


}