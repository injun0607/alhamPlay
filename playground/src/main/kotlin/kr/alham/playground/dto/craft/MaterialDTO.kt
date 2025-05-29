package kr.alham.playground.dto.craft

import kr.alham.playground.domain.item.ItemRarity

data class MaterialDTO(
    val itemRarity: ItemRarity = ItemRarity.COMMON,
    val name: String = ""
) {

}