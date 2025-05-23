package kr.alham.playground.domain.area

interface Area{
    var id: Long?
    var name: String
    var description: String
    var type: AreaType
    var tiles: List<Tile>
}

class Tile(
    var x: Int = 0,
    var y: Int = 0,
){
}

enum class AreaType {
    FIELD,
    CITY,
    DUNGEON,
}

