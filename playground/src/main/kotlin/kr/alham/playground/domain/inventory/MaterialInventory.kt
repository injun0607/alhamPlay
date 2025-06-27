package kr.alham.playground.domain.inventory

import jakarta.persistence.*
import kr.alham.playground.domain.player.Player

@Entity
class MaterialInventory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_inventory_id")
    val id: Long? = null,

    @OneToOne
    @JoinColumn(name = "player_id", unique = true)
    val player: Player = Player(),

    @OneToMany(mappedBy = "materialInventory", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    val materialItemList: MutableList<MaterialInventoryItem> = mutableListOf()

){
    fun addMaterialItem(materialInventoryItem: MaterialInventoryItem) {
        materialItemList.add(materialInventoryItem)
        materialInventoryItem.materialInventory = this
    }


}