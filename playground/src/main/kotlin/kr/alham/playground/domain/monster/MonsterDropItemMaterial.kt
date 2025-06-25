package kr.alham.playground.domain.monster

import jakarta.persistence.*
import kr.alham.playground.domain.item.Material

@Entity
class MonsterDropItemMaterial(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "monster_drop_info_id")
    val monsterDropInfoMaterial: MonsterDropInfoMaterial = MonsterDropInfoMaterial(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_id")
    val material: Material = Material(),
) {
    companion object {
        fun create(
            monsterDropInfoMaterial: MonsterDropInfoMaterial,
            material: Material
        ): MonsterDropItemMaterial {
            return MonsterDropItemMaterial(
                monsterDropInfoMaterial = monsterDropInfoMaterial,
                material = material
            ).also {
                monsterDropInfoMaterial.monsterDropItemMaterialList.add(it)
            }
        }
    }

}