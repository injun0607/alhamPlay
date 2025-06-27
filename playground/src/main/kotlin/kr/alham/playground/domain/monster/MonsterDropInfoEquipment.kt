package kr.alham.playground.domain.monster

import jakarta.persistence.*

@Entity
class MonsterDropInfoEquipment(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "monster_id", unique = true)
    val monster: Monster = Monster(),

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "monsterDropInfoEquipment", cascade = [CascadeType.ALL], orphanRemoval = true)
    val monsterDropItemEquipmentList: MutableList<MonsterDropItemEquipment> = mutableListOf(),

) {



}