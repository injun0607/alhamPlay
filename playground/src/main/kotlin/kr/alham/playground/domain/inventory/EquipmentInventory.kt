package kr.alham.playground.domain.inventory

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import kr.alham.playground.domain.player.Player

@Entity
class EquipmentInventory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_inventory_id")
    val id: Long? = null,

    @OneToOne
    @JoinColumn(name = "player_id")
    val player: Player = Player(),

    @OneToMany(mappedBy = "equipmentInventory")
    val equipmentItemList: MutableList<EquipmentInventoryItem> = mutableListOf()
){
    fun addEquipmentItem(equipmentItem: EquipmentInventoryItem) {
        equipmentItemList.add(equipmentItem)
        equipmentItem.equipmentInventory = this // 양방향 연관관계 설정
    }

}