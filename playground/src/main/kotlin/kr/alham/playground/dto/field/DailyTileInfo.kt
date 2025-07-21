package kr.alham.playground.dto.field

import kr.alham.playground.domain.area.TileType
import kr.alham.playground.domain.item.ItemRarity

data class DailyTileInfo(
    val selectedTileFlag: Boolean = false,
    val selectedTileX: Int = 0,
    val selectedTileY: Int = 0,
    val selectedTileRarity: TileType = TileType.COMMON,
    var availableUpdateCount: Int = 3,
){
}
