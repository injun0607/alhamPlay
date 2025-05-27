package kr.alham.playground.common.utils

import kr.alham.playground.domain.area.TileType
import kr.alham.playground.domain.item.ItemRarity
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TypeMatchersKtTest{

    @Test
    fun `test tileTypeMatcherWithItemRarity`() {
        assertEquals(ItemRarity.COMMON, tileTypeMatcherWithItemRarity(TileType.COMMON))
        assertEquals(ItemRarity.UNCOMMON, tileTypeMatcherWithItemRarity(TileType.UNCOMMON))
        assertEquals(ItemRarity.RARE, tileTypeMatcherWithItemRarity(TileType.RARE))
        assertEquals(ItemRarity.EPIC, tileTypeMatcherWithItemRarity(TileType.EPIC))
        assertEquals(ItemRarity.UNIQUE, tileTypeMatcherWithItemRarity(TileType.UNIQUE))
        assertEquals(ItemRarity.LEGENDARY, tileTypeMatcherWithItemRarity(TileType.LEGENDARY))
    }


}