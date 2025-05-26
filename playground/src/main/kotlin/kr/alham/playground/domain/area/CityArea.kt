package kr.alham.playground.domain.area

class CityArea(
    override var id: Long? = null,
    override var name: String = "",
    override var description: String = "",
    override var type: AreaType = AreaType.CITY,
    override var tiles: List<Tile> = mutableListOf(),

) : Area{
    override fun getTile(x: Int, y: Int): Tile {
        TODO("Not yet implemented")
    }
}