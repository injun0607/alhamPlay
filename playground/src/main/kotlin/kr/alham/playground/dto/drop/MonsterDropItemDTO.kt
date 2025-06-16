package kr.alham.playground.dto.drop

import kr.alham.playground.domain.item.ItemRarity
import kr.alham.playground.domain.item.ItemType

data class MonsterDropItemDTO(
    val itemType: ItemType,
    val itemId: Long
){}

data class MonsterDropResultDTO(
    val name: String,
    val description: String,
    val itemRarity: ItemRarity,
    val itemType: ItemType,
){}
