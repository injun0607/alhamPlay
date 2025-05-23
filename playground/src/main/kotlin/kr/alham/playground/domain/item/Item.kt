package kr.alham.playground.domain.item

interface Item {
    var id: Long?
    var name: String
    var description: String
    var type: ItemType
    var itemRarity: ItemRarity
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