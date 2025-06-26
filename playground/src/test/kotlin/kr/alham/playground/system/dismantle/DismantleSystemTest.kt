package kr.alham.playground.system.dismantle

import kr.alham.playground.domain.item.Equipment
import kr.alham.playground.domain.item.ItemRarity
import kr.alham.playground.loader.ItemRecipeLoader
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test



class DismantleSystemTest{


    lateinit var dismantleSystem: DismantleSystem

    @BeforeEach
    fun setUp() {
        // Mock or initialize the itemRecipeLoader as needed
        val itemRecipeLoader = ItemRecipeLoader()
        dismantleSystem = DismantleSystem(itemRecipeLoader)
    }

    @Test
    fun dismantleEquipmentTest(){
        /**
         *   "COMMON": [
         *     {
         *       "name": "Barkskin Vest",
         *       "ingredients": {
         *         "Twisted Vine Fiber": 1,
         *         "Fernscale Bark": 1,
         *         "Dryleaf Bundle": 1,
         *         "Twigknit Cord": 1
         *       }
         *     }
         */
        val equipment = Equipment(
            name = "Barkskin Vest",
            itemRarity = ItemRarity.COMMON,
        )


        val materialNameList = listOf("Twisted Vine Fiber", "Fernscale Bark", "Dryleaf Bundle", "Twigknit Cord")

        val dismantleList = dismantleSystem.dismantleEquipment(equipment)



        dismantleList.forEach {
            println(it)
            assertTrue(
                materialNameList.contains(it),
                "Dismantled material $it is not in the expected list: $materialNameList"
            )
        }

    }



}