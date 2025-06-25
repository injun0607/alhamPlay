package kr.alham.playground.domain.monster

import jakarta.persistence.*
import kr.alham.playground.domain.item.Equipment

@Entity
class MonsterDropItemEquipment(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "monster_drop_info_id")
    val monsterDropInfoEquipment: MonsterDropInfoEquipment = MonsterDropInfoEquipment(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id")
    val equipment: Equipment = Equipment()
) {
    companion object {
        fun create(
            monsterDropInfoEquipment: MonsterDropInfoEquipment,
            equipment: Equipment
        ): MonsterDropItemEquipment {
            return MonsterDropItemEquipment(
                monsterDropInfoEquipment = monsterDropInfoEquipment,
                equipment = equipment
            ).also {
                monsterDropInfoEquipment.monsterDropItemEquipmentList.add(it)
            }
        }
    }
}