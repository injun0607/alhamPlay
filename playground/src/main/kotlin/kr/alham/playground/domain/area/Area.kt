package kr.alham.playground.domain.area

interface Area{
    var id: Long?
    var name: String
    var description: String
    var type: AreaType
    var tiles: List<Tile>
    fun getTile(x: Int, y: Int): Tile
}

class Tile(
    var x: Int = 0,
    var y: Int = 0,
    var type: TileType = TileType.COMMON
){
}


enum class TileType{
    COMMON,
    UNCOMMON,
    RARE,
    EPIC,
    UNIQUE,
    LEGENDARY,
}

enum class AreaType {
    FIELD,
    CITY,
    DUNGEON,
}
