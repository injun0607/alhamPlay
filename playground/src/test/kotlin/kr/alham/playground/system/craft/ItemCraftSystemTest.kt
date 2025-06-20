package kr.alham.playground.system.craft

import kr.alham.playground.domain.item.EquipmentRecipe
import kr.alham.playground.domain.item.ItemRarity
import kr.alham.playground.dto.craft.EquipmentRecipeDTO
import kr.alham.playground.dto.craft.MaterialDTO
import kr.alham.playground.loader.ItemRecipeLoader
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class ItemCraftSystemTest{



    lateinit var itemRecipeLoader: ItemRecipeLoader
    lateinit var recipeMap : Map<ItemRarity,List<EquipmentRecipe>>
    lateinit var itemCraftSystem: ItemCraftSystem

    @BeforeEach
    fun setup() {
        itemRecipeLoader = ItemRecipeLoader()
        recipeMap = itemRecipeLoader.loadEquipmentRecipe()
        itemCraftSystem = ItemCraftSystem(itemRecipeLoader)
    }

    @Test
    fun itemEqualCheckTest(){

        val equipment = EquipmentRecipe(
            name="Test Item",
            ingredients =  mapOf(
                "Test Material" to 1,
                "Test Material2" to 2
            )
        )


        val ingredients = mapOf(
            "Test Material" to 1,
            "Test Material2" to 2
        )

        assertEquals(true, equipment.ingredients == ingredients, "Ingredients should be equal")
    }


    private fun findRecipe(ingredients: Map<String,Int>,itemRecipeMap: Map<ItemRarity, List<EquipmentRecipe>>): EquipmentRecipe? {
        itemRecipeMap.forEach { (rarity, recipes) ->
            recipes.forEach { recipe ->
                if (recipe.ingredients == ingredients) {
                    return recipe
                }
            }
        }
        return null
    }


    @Test
    fun `findRecipeTest`(){

        /**
         * "name": "BeachsandCrabFang",
         *       "ingredients": {
         *         "WetGem": 1,
         *         "BeachSand": 1,
         *         "CrabClaw": 1,
         *         "DeepCoral": 1
         *       }
         *       {
         *       "name": "HellflamesoulTotem",
         *       "ingredients": {
         *         "HellflameSoul": 4
         *       }
         *     }
         */

        val ingredients = mapOf(
            "Sandshell Fragment" to  1,
            "Scorpionspine Chip" to 1,
            "Sunburnt Gourd Husk" to 1,
            "Zephyring Dust" to 1
        )

        val ingredients2 = mapOf(
            "Aridroot Tendril" to 1,
            "Goldenflare Grain" to 1,
            "Permafrost Pellet" to 1,
            "Iceveil Fiber" to 1
        )

        val ingredients3 = mapOf(
            "Stormicicle Chip" to 1,
            "Breachice Crystal" to 1,
            "Glintsnow Dust" to 1,
            "Frostbloom Fragment" to 1
        )

        val recipe = findRecipe(ingredients,recipeMap)
        val recipe2 = findRecipe(ingredients2,recipeMap)
        val recipe3 = findRecipe(ingredients3,recipeMap)


        assertEquals("ColdhideBracers", recipe?.name, "Recipe name should be BeachsandCrabFang")
        assertEquals("FrostTreadBoots", recipe2?.name, "Recipe name should be HellflamesoulTotem")
        assertEquals("AshwrapSandals", recipe3?.name, "Recipe name should be HellflamesoulTotem")

    }

    private fun makeRandomItem(sumItemPoint: Double, itemRecipeListMap: Map<ItemRarity,List<EquipmentRecipe>>):EquipmentRecipe{
        //평균점수를 확인해서 랜덤아이템 생성
        when(sumItemPoint){
            in 0.0..4.0 -> {
                return itemRecipeListMap[ItemRarity.COMMON]?.random()
                    ?: throw IllegalArgumentException("No common items found")
            }
            in 4.0..8.0 -> {
                return itemRecipeListMap[ItemRarity.UNCOMMON]?.random()
                    ?: throw IllegalArgumentException("No uncommon items found")
            }
            in 8.0..16.0 -> {
                return itemRecipeListMap[ItemRarity.RARE]?.random()
                    ?: throw IllegalArgumentException("No rare items found")
            }
            in 16.0..32.0 -> {
                return itemRecipeListMap[ItemRarity.EPIC]?.random()
                    ?: throw IllegalArgumentException("No epic items found")
            }
            in 32.0..96.0 -> {
                return itemRecipeListMap[ItemRarity.UNIQUE]?.random()
                    ?: throw IllegalArgumentException("No unique items found")
            }
            in 96.0..128.0 -> {
                return itemRecipeListMap[ItemRarity.LEGENDARY]?.random()
                    ?: throw IllegalArgumentException("No legendary items found")
            }
            else -> {
                return itemRecipeListMap[ItemRarity.COMMON]?.random()
                    ?: throw IllegalArgumentException("Else Case No Common items found")
            }
        }

    }

    @Test
    fun `makeRandomItemTest`(){

        val randomCommonItem = makeRandomItem(1.0, recipeMap)
        val randomUnCommonItem = makeRandomItem(7.0,recipeMap)
        val randomRareItem = makeRandomItem(16.0,recipeMap)
        val randomEpicItem = makeRandomItem(30.0,recipeMap)
        val randomUniqueItem = makeRandomItem(64.0,recipeMap)
        val randomUniqueItem2 = makeRandomItem(96.0,recipeMap)
        val randomLegendaryItem = makeRandomItem(128.0,recipeMap)
        val randomLegendaryItem2 = makeRandomItem(97.0,recipeMap)
        val randomExceptItem = makeRandomItem(123133.0,recipeMap)

        val commonRecipeList =  recipeMap[ItemRarity.COMMON]
        val unCommonRecipeList = recipeMap[ItemRarity.UNCOMMON]
        val rareRecipeList = recipeMap[ItemRarity.RARE]
        val epicRecipeList = recipeMap[ItemRarity.EPIC]
        val uniqueRecipeList = recipeMap[ItemRarity.UNIQUE]
        val legendaryRecipeList = recipeMap[ItemRarity.LEGENDARY]

        println(randomCommonItem)
        println(randomUnCommonItem)
        println(randomRareItem)
        println(randomEpicItem)
        println(randomUniqueItem)
        println(randomUniqueItem2)
        println(randomLegendaryItem)
        println(randomLegendaryItem2)

        assertEquals(true, commonRecipeList?.contains(randomCommonItem), "Random common item should be of COMMON rarity")
        assertEquals(true, unCommonRecipeList?.contains(randomUnCommonItem), "Random uncommon item should be of UNCOMMON rarity")
        assertEquals(true, rareRecipeList?.contains(randomRareItem), "Random rare item should be of RARE rarity")
        assertEquals(true, epicRecipeList?.contains(randomEpicItem), "Random epic item should be of EPIC rarity")
        assertEquals(true, uniqueRecipeList?.contains(randomUniqueItem), "Random unique item should be of UNIQUE rarity")
        assertEquals(true, uniqueRecipeList?.contains(randomUniqueItem2), "Random unique item should be of UNIQUE rarity")
        assertEquals(true, legendaryRecipeList?.contains(randomLegendaryItem), "Random legendary item should be of LEGENDARY rarity")
        assertEquals(true, legendaryRecipeList?.contains(randomLegendaryItem2), "Random legendary item should be of LEGENDARY rarity")
        assertEquals(true, commonRecipeList?.contains(randomExceptItem), "Random except item should be of COMMON rarity")

    }

    @Test
    fun itemCraftSystemTest(){

        /**
         * "name": "Nature's embraceBlade",
         *       "ingredients": {
         *         "Nature's Embrace": 3,
         *         "WhisperingSeed": 1
         *       }
         *
         *
         *         "COMMON": [
         *     "HerbLeaf",
         *     "Twig",
         *     "MossStone",
         *     "ForestGrass",
         *     "BarkChip",
         *     "BugCarapace"
         *   ],
         *   "UNCOMMON": [
         *     "OakBranch",
         *     "MushroomSpore",
         *     "StickySap",
         *     "WildBerry",
         *     "AntlerHorn"
         *   ],
         *   "RARE": [
         *     "ForestCrystal",
         *     "EmeraldMoss",
         *     "Moonroot",
         *     "SilverLeaf"
         *   ],
         *   "EPIC": [
         *     "AncientTreeHeart",
         *     "DruidStone",
         *     "MysticMushroom"
         *   ],
         *   "UNIQUE": [
         *     "VerdantSpiritCore",
         *     "Nature's Embrace"
         *   ],
         *   "LEGENDARY": [
         *     "WhisperingSeed"
         *   ]
         */

        val uniqueMaterial = MaterialDTO(
            itemRarity = ItemRarity.UNIQUE,
            name = "Nature's Embrace"
        )

        val legendaryMaterial = MaterialDTO(
            itemRarity = ItemRarity.LEGENDARY,
            name = "WhisperingSeed"
        )

        val equipmentRecipeDTO = EquipmentRecipeDTO(
            ingredients = mapOf(
                uniqueMaterial to 3,
                legendaryMaterial to 1
            )
        )


        val randomLegendaryMaterial = MaterialDTO(
            itemRarity = ItemRarity.LEGENDARY,
            name = "WhisperingSeed"
        )

        val randomLegendaryMaterial2 = MaterialDTO(
            itemRarity = ItemRarity.LEGENDARY,
            name = "CrownOfFlames"
        )

        val randomEquipmentRecipeDTO = EquipmentRecipeDTO(
            ingredients = mapOf(
                randomLegendaryMaterial to 2,
                randomLegendaryMaterial2 to 2
            )
        )

        val legendaryRecipeList = recipeMap[ItemRarity.LEGENDARY]

        val fixedRecipe = itemCraftSystem.craftEquipment(equipmentRecipeDTO)
        val randomRecipe = itemCraftSystem.craftEquipment(randomEquipmentRecipeDTO)

        assertEquals("Nature's embraceBlade",fixedRecipe.name, "Crafted item name should be Nature's embraceBlade")
        assertEquals(true,legendaryRecipeList?.contains(randomRecipe) , "Crafted item should contain Nature's Embrace")

    }

}