package kr.alham.playground.dto.craft

import kr.alham.playground.domain.item.ItemRarity

data class IngredientsInfoDTO(
  val inventoryItemId: Long = 0L,
  val name: String = "",
  val itemRarity: ItemRarity = ItemRarity.COMMON,
  val quantity: Int = 1,
){}

data class IngredientsInfoDTOList(
  val ingredients: List<IngredientsInfoDTO> = emptyList()
) {
  companion object {
    fun from(ingredients: List<IngredientsInfoDTO>): IngredientsInfoDTOList {
      return IngredientsInfoDTOList(ingredients)
    }
  }
}