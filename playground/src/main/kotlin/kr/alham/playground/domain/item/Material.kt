package kr.alham.playground.domain.item

import jakarta.persistence.*
import kr.alham.playground.domain.area.FieldType

@Entity
@Table(
    name = "al_material",
    indexes = [
        Index(name = "idx_material_name", columnList = "name", unique = true),
    ]
)
class Material(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long? = null,
    @Column(nullable = false, unique = true)
    override var name: String = "",
    override var description: String = "",
    @Enumerated(EnumType.STRING)
    override var type: ItemType = ItemType.MATERIAL,
    @Enumerated(EnumType.STRING)
    override var itemRarity: ItemRarity = ItemRarity.COMMON,
    @Enumerated(EnumType.STRING)
    var dropArea: FieldType = FieldType.FOREST
) : Item {

}