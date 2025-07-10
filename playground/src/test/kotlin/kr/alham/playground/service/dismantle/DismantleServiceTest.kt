package kr.alham.playground.service.dismantle

import kr.alham.playground.common.utils.mapper.ItemMapper
import kr.alham.playground.domain.inventory.EquipmentInventory
import kr.alham.playground.domain.inventory.MaterialInventory
import kr.alham.playground.domain.item.Equipment
import kr.alham.playground.domain.item.ItemRarity
import kr.alham.playground.domain.player.Player
import kr.alham.playground.repository.inventory.EquipmentInventoryRepository
import kr.alham.playground.repository.inventory.MaterialInventoryRepository
import kr.alham.playground.repository.item.EquipmentRepository
import kr.alham.playground.repository.item.MaterialRepository
import kr.alham.playground.service.inventory.InventoryService
import kr.alham.playground.service.player.PlayerService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test

@SpringBootTest
class DismantleServiceTest {

    @Autowired
    lateinit var playerService: PlayerService

    @Autowired
    lateinit var inventoryService: InventoryService

    @Autowired
    lateinit var dismantleService: DismantleService

    @Autowired
    lateinit var equipmentInventoryRepository: EquipmentInventoryRepository

    @Autowired
    lateinit var materialInventoryRepository: MaterialInventoryRepository

    @Autowired
    lateinit var equipmentRepository: EquipmentRepository

    @Autowired
    lateinit var materialRepository: MaterialRepository

    lateinit var equipmentFire: Equipment

    lateinit var equipmentWater: Equipment

    @BeforeEach
    fun setUp() {
        // 초기화 작업
        //플레이어 데이터 넣어주기
        //장비 데이터 넣어주기

        equipmentFire = Equipment(
            name = "Elderwood Longbow22",
            description = "A sword imbued with fire magic",
            itemRarity = ItemRarity.LEGENDARY
        )

        equipmentWater = Equipment(
            name = "Wildheart Totem22",
            description = "A staff imbued with water magic",
            itemRarity = ItemRarity.LEGENDARY
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

        equipmentRepository.save(equipmentFire)
        equipmentRepository.save(equipmentWater)

    }

    @Test
    fun testDismantleEquipment() {
        inventoryService.saveItemToPlayerInventory(1L,equipmentFire)
        inventoryService.saveItemToPlayerInventory(1L,equipmentWater)

        val playerInventory = inventoryService.getEquipmentInventoryByPlayerId(1L)

        val namedList = dismantleService.dismantleEquipment(1L, ItemMapper.equipmentInventoryItemToDTO(playerInventory.equipmentItemList[0]))

        assertEquals(2, namedList.size, "Dismantling should yield two materials")

        namedList.forEach {
            assertEquals("Ancestor’s Sap",it)
        }

        val savedMaterialInventory =  inventoryService.getMaterialInventoryByPlayerId(1L)
        assertEquals(2, savedMaterialInventory.materialItemList[0].quantity)


    }


}