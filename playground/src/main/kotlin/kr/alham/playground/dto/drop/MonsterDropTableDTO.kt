package kr.alham.playground.dto.drop

import kr.alham.playground.domain.item.ItemRarity
import kr.alham.playground.domain.item.ItemType

data class MonsterDropTableDTO (
    val monsterId: Long,
    val drops: Map<ItemRarity, Map<ItemType, List<String>>>
)
