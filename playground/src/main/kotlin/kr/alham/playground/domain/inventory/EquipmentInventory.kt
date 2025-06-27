package kr.alham.playground.domain.inventory

import jakarta.persistence.*
import kr.alham.playground.domain.player.Player

@Entity
class EquipmentInventory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_inventory_id")
    val id: Long? = null,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", unique = true)
    val player: Player = Player(),

    @OneToMany(mappedBy = "equipmentInventory", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.LAZY)
    val equipmentItemList: MutableList<EquipmentInventoryItem> = mutableListOf()
){
    fun addEquipmentItem(equipmentItem: EquipmentInventoryItem) {
        equipmentItemList.add(equipmentItem)
        equipmentItem.equipmentInventory = this // 양방향 연관관계 설정
    }

}