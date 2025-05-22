package kr.alham.playground.domain.item

interface Item {
    val id: Long?
    val name: String
    val description: String
    val type: ItemType
    val itemRarity: ItemRarity
}


enum class ItemType {
    EQUIPMENT,
    MATERIAL,
}

enum class ItemRarity {
    COMMON,
    UNCOMMON,
    RARE,
    EPIC,
    UNIQUE,
    LEGENDARY
}