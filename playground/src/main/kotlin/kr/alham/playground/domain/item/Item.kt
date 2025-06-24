package kr.alham.playground.domain.item

interface Item {
    var id: Long?
    var name: String
    var description: String
    var type: ItemType
    var itemRarity: ItemRarity
    var itemImg: String
}


enum class ItemType {
    EQUIPMENT,
    MATERIAL,
}

enum class ItemRarity(val value: Int) {
    COMMON(1),
    UNCOMMON(2),
    RARE(4),
    EPIC(8),
    UNIQUE(16),
    LEGENDARY(32)
}