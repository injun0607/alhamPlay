package kr.alham.playground.domain.item

import jakarta.persistence.*

@Entity
@Table(name = "al_material")
class Material(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Long? = null,
    override val name: String = "",
    override val description: String = "",
    override val type: ItemType = ItemType.MATERIAL,
    override val itemRarity: ItemRarity = ItemRarity.COMMON,
) : Item {

}