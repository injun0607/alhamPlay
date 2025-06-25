package kr.alham.playground.domain.monster

import jakarta.persistence.*

@Entity
class MonsterDropInfoMaterial(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "monster_id")
    val monster: Monster = Monster(),

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "monsterDropInfoMaterial", cascade = [CascadeType.ALL], orphanRemoval = true)
    val monsterDropItemMaterialList: MutableList<MonsterDropItemMaterial> = mutableListOf(),

) {



}