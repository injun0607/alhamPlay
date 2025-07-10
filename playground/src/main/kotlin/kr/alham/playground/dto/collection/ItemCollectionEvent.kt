package kr.alham.playground.dto.collection

import kr.alham.playground.domain.item.ItemType

data class ItemCollectionEvent(
    val playerId: Long,
    val itemId: Long,
    val itemType: ItemType,
    var isNew: Boolean
){

}
