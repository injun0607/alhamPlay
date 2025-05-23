package kr.alham.playground.domain.item

import jakarta.persistence.*

@Entity
@Table(name = "al_material")
class Material(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long? = null,
    override var name: String = "",
    override var description: String = "",
    override var type: ItemType = ItemType.MATERIAL,
    override var itemRarity: ItemRarity = ItemRarity.COMMON,
) : Item {

}