package kr.alham.playground.service.area

import io.mockk.every
import io.mockk.mockk
import kr.alham.playground.domain.area.FieldArea
import kr.alham.playground.domain.area.FieldType
import kr.alham.playground.domain.area.TileType
import kr.alham.playground.repository.area.FieldAreaRepository
import kr.alham.playground.repository.area.RedisTileCacheRepository
import kr.alham.playground.system.area.AreaSystem
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*

class AreaServiceTest {


    lateinit var areaService: AreaService
    lateinit var areaSystem: AreaSystem
    lateinit var redisTileCacheRepository: RedisTileCacheRepository
    lateinit var fieldAreaRepository: FieldAreaRepository
    lateinit var filedArea: FieldArea


    @BeforeEach
    fun setup() {
        // 초기화 로직
        redisTileCacheRepository = mockk()
        fieldAreaRepository = mockk()
        areaSystem = AreaSystem()
        areaService = AreaService(
            fieldAreaRepository = fieldAreaRepository,
            redisTileCacheRepository = redisTileCacheRepository,
            areaSystem = areaSystem
        )
        filedArea = FieldArea(
            id = 1L,
            fieldType = FieldType.FOREST,
            tiles = areaSystem.initTiles()
        )

    }

    @Test
    fun findAreaByIdTest() {
        // 테스트 로직
        //areaSystem.generateTile(tiles,10,5,5,3,1,1)

        every {
            fieldAreaRepository.findById(1L)
        } returns (Optional.of(filedArea))


        every {
            redisTileCacheRepository.getTilesByFieldAreaId(1L)
        } returns (areaSystem.changeRandomTypeTiles(
            tiles = filedArea.tiles,
            commonCnt = 10,
            unCommonCnt = 5,
            rareCnt = 5,
            epicCnt = 3,
            uniqueCnt = 1,
            legendaryCnt = 1
        ))

        every {
            fieldAreaRepository.findById(2L)
        } returns (Optional.of(
            FieldArea(
                id = 2L,
                fieldType = FieldType.DESERT,
                tiles = areaSystem.initTiles()
            )
        ))

        val tiles = areaSystem.initTiles()
        areaSystem.changeRandomTypeTiles(tiles,10,8,3,2,1,1)

        every {
            redisTileCacheRepository.getTilesByFieldAreaId(2L)
        } returns (null)

        every{
            redisTileCacheRepository.saveTiles(2L,
                any()
            )
        } returns Unit


        val areaInfo = areaService.findAreaById(1L)
        val areaTiles = areaInfo.tiles

        val nonAreaInfo = areaService.findAreaById(2L)
        val nonAreaTiles = nonAreaInfo.tiles


        //redis에 저장된 타일 정보가 있는 경우
        assertEquals(25, areaTiles.size)
        assertEquals(10, areaTiles.count { it.type == TileType.COMMON })
        assertEquals(5, areaTiles.count { it.type == TileType.UNCOMMON })
        assertEquals(5, areaTiles.count { it.type == TileType.RARE })
        assertEquals(3, areaTiles.count { it.type == TileType.EPIC })
        assertEquals(1, areaTiles.count { it.type == TileType.UNIQUE })
        assertEquals(1, areaTiles.count { it.type == TileType.LEGENDARY })

        //정보 없이 새로 생성되는 타일 케이스
        assertEquals(25, nonAreaTiles.size)
        assertEquals(10, nonAreaTiles.count { it.type == TileType.COMMON })
        assertEquals(8, nonAreaTiles.count { it.type == TileType.UNCOMMON })
        assertEquals(3, nonAreaTiles.count { it.type == TileType.RARE })
        assertEquals(2, nonAreaTiles.count { it.type == TileType.EPIC })
        assertEquals(1, nonAreaTiles.count { it.type == TileType.UNIQUE })
        assertEquals(1, nonAreaTiles.count { it.type == TileType.LEGENDARY })

        assertEquals(25, nonAreaTiles.size)
        assertEquals(10, nonAreaTiles.count { it.type == TileType.COMMON })
        assertNotEquals(5, nonAreaTiles.count { it.type == TileType.UNCOMMON })
        assertNotEquals(5, nonAreaTiles.count { it.type == TileType.RARE })
        assertEquals(2, nonAreaTiles.count { it.type == TileType.EPIC })
        assertNotEquals(0, nonAreaTiles.count { it.type == TileType.UNIQUE })
        assertNotEquals(0, nonAreaTiles.count { it.type == TileType.LEGENDARY })


    }



}
