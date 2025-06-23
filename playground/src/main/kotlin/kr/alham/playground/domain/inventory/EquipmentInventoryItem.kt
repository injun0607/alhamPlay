package kr.alham.playground.domain.inventory

import jakarta.persistence.*
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
    var equipmentInventory: EquipmentInventory = EquipmentInventory(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id")
    var equipment: Equipment = Equipment(),

    var itemOrder: Int = 0

){
    companion object {
        fun create(equipmentInventory: EquipmentInventory, equipment: Equipment,itemOrder: Int): EquipmentInventoryItem {
            return EquipmentInventoryItem(
                equipmentInventory = equipmentInventory,
                equipment = equipment,
                itemOrder = itemOrder
            ).also {
                equipmentInventory.addEquipmentItem(it)
            }
        }
    }
}