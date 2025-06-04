package kr.alham.playground.domain.inventory

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import kr.alham.playground.domain.item.Material

@Entity
class MaterialInventoryItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_inventory_item_id")
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "material_inventory_id")
    val materialInventory: MaterialInventory = MaterialInventory(),

    @ManyToOne
    @JoinColumn(name = "material_id")
    val material: Material = Material()
) {}