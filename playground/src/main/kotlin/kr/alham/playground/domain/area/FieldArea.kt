package kr.alham.playground.domain.area

class FieldArea(
    override var id: Long? = null,
    override var name: String = "",
    override var description: String = "",
    override var type: AreaType = AreaType.FIELD,
    override var tiles: List<Tile> = mutableListOf(),
    var fieldType: FieldType = FieldType.CHAOS
):Area {
    override fun getTile(x: Int, y: Int): Tile {
        TODO("Not yet implemented")
    }
}


enum class FieldType {
    FOREST,
    DESERT,
    GLACIER,
    VOLCANO,
    COAST,
    CHAOS
}