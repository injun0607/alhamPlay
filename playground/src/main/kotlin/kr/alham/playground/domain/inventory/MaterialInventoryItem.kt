package kr.alham.playground.domain.inventory

import jakarta.persistence.*
import kr.alham.playground.domain.item.Material

@Entity
class MaterialInventoryItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_inventory_item_id")
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "material_inventory_id")
    var materialInventory: MaterialInventory = MaterialInventory(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_id")
    var material: Material = Material(),

    var itemOrder: Int = 0,
) {
    companion object {
        fun create(materialInventory: MaterialInventory, material: Material,itemOrder : Int): MaterialInventoryItem {
            return MaterialInventoryItem(
                materialInventory = materialInventory,
                material = material,
                itemOrder = itemOrder
            ).also {
                materialInventory.addMaterialItem(it)
            }
        }
    }
}