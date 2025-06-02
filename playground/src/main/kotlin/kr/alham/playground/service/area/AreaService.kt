package kr.alham.playground.service.area

import kr.alham.playground.domain.area.FieldArea
import kr.alham.playground.repository.area.FieldAreaRepository
import kr.alham.playground.repository.area.RedisTileCacheRepository
import kr.alham.playground.system.area.AreaSystem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Service

@Service
class AreaService(
    private val fieldAreaRepository: FieldAreaRepository,
    private val redisTileCacheRepository: RedisTileCacheRepository,
    private val areaSystem: AreaSystem
) {

    fun findAreaById(id: Long): FieldArea{
        val filedArea = fieldAreaRepository.findById(id).orElseThrow{
            IllegalArgumentException("Area with id $id not found")
        }



        val filedTiles = redisTileCacheRepository.getTilesByFieldAreaId(id)
            ?: run{
                val tiles = areaSystem.initTiles()
                areaSystem.generateTile(tiles,10,8,3,2,1,1)
                redisTileCacheRepository.saveTiles(id, tiles)
                tiles
            }

        filedArea.tiles = filedTiles

        return filedArea
    }



}