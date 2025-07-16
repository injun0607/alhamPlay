package kr.alham.playground.domain.area

import jakarta.persistence.*
import kotlin.jvm.Transient

@Entity
@Table(name = "al_field_areas")
class FieldArea(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long? = null,
    override var name: String = "",
    override var description: String = "",
    @Enumerated(EnumType.STRING)
    override var type: AreaType = AreaType.FIELD,
    @Transient
    override var tiles: List<Tile> = mutableListOf(),
    @Enumerated(EnumType.STRING)
    var fieldType: FieldType = FieldType.CHAOS
) : Area {
    override fun getTile(x: Int, y: Int): Tile {
        return tiles.firstOrNull { it.x == x && it.y == y }
            ?: throw IllegalArgumentException("Tile not found at coordinates ($x, $y)")
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