package kr.alham.playground.service.area

import kr.alham.playground.domain.area.FieldArea
import kr.alham.playground.domain.area.FieldType
import kr.alham.playground.domain.area.Tile
import kr.alham.playground.repository.area.FieldAreaRepository
import kr.alham.playground.repository.area.RedisTileCacheRepository
import kr.alham.playground.system.area.AreaSystem
import org.springframework.stereotype.Service

@Service
class AreaService(
    private val fieldAreaRepository: FieldAreaRepository,
    private val redisTileCacheRepository: RedisTileCacheRepository,
    private val areaSystem: AreaSystem
) {


    fun saveSelectedField(playerId: Long, fieldType: FieldType) {
        redisTileCacheRepository.savePlayerSelectedField(playerId, fieldType)
    }

    fun selectFieldArea(playerId: Long): FieldType? {
        return redisTileCacheRepository.getPlayerSelectedField(playerId)
    }

    fun findFieldArea(fieldId: Long): FieldArea{
        return fieldAreaRepository.findById(fieldId).orElseThrow {
            IllegalArgumentException("Field area with id $fieldId not found")
        }
    }

    fun findAreaById(id: Long): FieldArea{
        val filedArea = fieldAreaRepository.findById(id).orElseThrow{
            IllegalArgumentException("Area with id $id not found")
        }

        val filedTiles = redisTileCacheRepository.getTilesByFieldAreaId(id)
            ?: run{
                val tiles = areaSystem.initTiles()
                areaSystem.changeRandomTypeTiles(tiles,10,8,3,2,1,1)
                redisTileCacheRepository.saveTiles(id, tiles)
                tiles
            }

        filedArea.tiles = filedTiles

        return filedArea
    }

    fun findFieldAreaByType(fieldType: FieldType): FieldArea {
        return fieldAreaRepository.findByFieldType(fieldType)
            ?: throw IllegalArgumentException("Field area with type $fieldType not found")
    }

    fun saveSelectedTile(playerId: Long, tile: Tile) {
        redisTileCacheRepository.savePlayerSelectedTile(playerId, tile)
    }

    fun getSelectedTile(playerId: Long): Tile? {
        return redisTileCacheRepository.getPlayerSelectedTile(playerId)
    }


}