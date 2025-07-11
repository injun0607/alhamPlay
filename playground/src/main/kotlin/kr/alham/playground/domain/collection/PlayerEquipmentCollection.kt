package kr.alham.playground.domain.collection

import jakarta.persistence.*
import kr.alham.playground.domain.enums.CollectionLevelEnums
import kr.alham.playground.domain.item.Equipment
import kr.alham.playground.domain.player.Player
import java.time.LocalDateTime
import java.util.Collections

@Entity
@Table(
    uniqueConstraints = [UniqueConstraint(columnNames = ["player_id", "equipment_id"])]
)
class PlayerEquipmentCollection(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    val player:Player = Player(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id")
    val equipment: Equipment = Equipment(),

    @Enumerated(EnumType.STRING)
    var level: CollectionLevelEnums = CollectionLevelEnums.LEVEL1,
    var quantity: Int = 0,
    val discoveredAt: LocalDateTime = LocalDateTime.now(),

    ) {
    companion object {
        fun of(playerId: Long, equipmentId: Long, quantity: Int = 0): PlayerEquipmentCollection {
            return PlayerEquipmentCollection(
                player = Player(
                    id = playerId
                ),
                equipment = Equipment(
                    id = equipmentId
                ),
                quantity = quantity
            )
        }
    }

    fun levelUpCheck(exp: Int){
        if(exp <= 0) return


    }


}