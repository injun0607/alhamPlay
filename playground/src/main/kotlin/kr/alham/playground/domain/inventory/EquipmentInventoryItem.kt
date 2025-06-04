package kr.alham.playground.domain.inventory

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import kr.alham.playground.domain.item.Equipment
import kr.alham.playground.domain.item.Material

@Entity
class EquipmentInventoryItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_inventory_item_id")
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "equipment_inventory_id")
    val equipmentInventory: EquipmentInventory = EquipmentInventory(),

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    val equipment: Equipment = Equipment()
){}