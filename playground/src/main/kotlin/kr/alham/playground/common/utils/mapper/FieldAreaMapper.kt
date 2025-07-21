package kr.alham.playground.common.utils.mapper

import kr.alham.playground.domain.area.FieldArea
import kr.alham.playground.domain.area.Tile
import kr.alham.playground.domain.area.TileType
import kr.alham.playground.dto.field.DailyTileInfo
import kr.alham.playground.dto.field.FieldAreaDTO

object FieldAreaMapper {

    fun fieldAreaToDTO(
        fieldArea: FieldArea
    ): FieldAreaDTO {
        return FieldAreaDTO(
            id = fieldArea.id,
            name = fieldArea.name,
            description = fieldArea.description,
            fieldType = fieldArea.fieldType,
        )
    }

    fun fieldAreaToDTOWithTileInfo(
        fieldArea: FieldArea,
        dailyTileInfo: DailyTileInfo
    ): FieldAreaDTO{
        return FieldAreaDTO(
            id = fieldArea.id,
            name = fieldArea.name,
            description = fieldArea.description,
            fieldType = fieldArea.fieldType,
            dailyTileInfo = dailyTileInfo
        )
    }

    fun tileToDailyTileInfo(
        tile: Tile,
        tileType: TileType
    ): DailyTileInfo {
        return DailyTileInfo(
            selectedTileFlag = true,
            selectedTileX = tile.x,
            selectedTileY = tile.y,
            selectedTileRarity = tileType,
        )
    }
}