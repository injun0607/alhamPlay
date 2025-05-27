package kr.alham.playground.common.utils

import kr.alham.playground.domain.area.TileType
import kr.alham.playground.domain.item.ItemRarity

fun tileTypeMatcherWithItemRarity(
    tileType: TileType,
): ItemRarity {
    return when (tileType) {
        TileType.COMMON -> ItemRarity.COMMON
        TileType.UNCOMMON -> ItemRarity.UNCOMMON
        TileType.RARE -> ItemRarity.RARE
        TileType.EPIC -> ItemRarity.EPIC
        TileType.UNIQUE -> ItemRarity.UNIQUE
        TileType.LEGENDARY -> ItemRarity.LEGENDARY
    }
}