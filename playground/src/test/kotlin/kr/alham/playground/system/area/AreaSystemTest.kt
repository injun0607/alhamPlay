package kr.alham.playground.system.area

import kr.alham.playground.domain.area.FieldType
import kr.alham.playground.domain.area.TileType
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import kotlin.test.Test

class AreaSystemTest{

    lateinit var areaSystem: AreaSystem

    @BeforeEach
    fun setup() {
        areaSystem = AreaSystem()
    }

    @Test
    fun initFiledAreaTest(){

        val forestArea =  areaSystem.initFieldArea(FieldType.FOREST)
        val tiles = areaSystem.initTiles()
        assertEquals(FieldType.FOREST, forestArea.fieldType, "포레스트 에리어 확인")
        assertEquals(25, forestArea.tiles.size, "포레스트 에리어 타일 개수 확인")
    }

    @Test
    fun generateTileTest(){

        val tiles = areaSystem.initTiles()
        val generatedArea = areaSystem.changeRandomTypeTiles(
            tiles = tiles,
            commonCnt = 10,
            unCommonCnt = 5,
            rareCnt = 5,
            epicCnt = 3,
            uniqueCnt = 1,
            legendaryCnt = 1
        )


        val commonCnt = tiles.count { it.type == TileType.COMMON }
        val unCommonCnt = tiles.count { it.type == TileType.UNCOMMON }
        val rareCnt = tiles.count { it.type == TileType.RARE }
        val epicCnt = tiles.count { it.type == TileType.EPIC }
        val uniqueCnt = tiles.count { it.type == TileType.UNIQUE }
        val legendaryCnt = tiles.count { it.type == TileType.LEGENDARY }

        assertEquals(10, commonCnt, "Common 타일 개수 확인")
        assertEquals(5, unCommonCnt, "Uncommon 타일 개수 확인")
        assertEquals(5, rareCnt, "Rare 타일 개수 확인")
        assertEquals(3, epicCnt, "Epic 타일 개수 확인")
        assertEquals(1, uniqueCnt, "Unique 타일 개수 확인")
        assertEquals(1, legendaryCnt, "Legendary 타일 개수 확인")

        for (tile in tiles) {
            println("Tile at (${tile.x}, ${tile.y}) is of type ${tile.type}")
        }
    }


    @Test
    fun generateTileTest2(){

        val tiles = areaSystem.initTiles()
        val generatedArea = areaSystem.changeRandomTypeTiles(
            tiles = tiles,
            commonCnt = 20,
            unCommonCnt = 5,
            rareCnt = 0,
            epicCnt = 0,
            uniqueCnt = 0,
            legendaryCnt = 0
        )


        val commonCnt = tiles.count { it.type == TileType.COMMON }
        val unCommonCnt = tiles.count { it.type == TileType.UNCOMMON }
        val rareCnt = tiles.count { it.type == TileType.RARE }
        val epicCnt = tiles.count { it.type == TileType.EPIC }
        val uniqueCnt = tiles.count { it.type == TileType.UNIQUE }
        val legendaryCnt = tiles.count { it.type == TileType.LEGENDARY }

        assertEquals(20, commonCnt, "Common 타일 개수 확인")
        assertEquals(5, unCommonCnt, "Uncommon 타일 개수 확인")
        assertEquals(0, rareCnt, "Rare 타일 개수 확인")
        assertEquals(0, epicCnt, "Epic 타일 개수 확인")
        assertEquals(0, uniqueCnt, "Unique 타일 개수 확인")
        assertEquals(0, legendaryCnt, "Legendary 타일 개수 확인")

        for (tile in tiles) {
            println("Tile at (${tile.x}, ${tile.y}) is of type ${tile.type}")
        }
    }


    @Test
    fun initTileTest(){
        val tiles = areaSystem.initTiles()
        kotlin.test.assertEquals(25, tiles.size, "타일 개수 확인")


    }

}