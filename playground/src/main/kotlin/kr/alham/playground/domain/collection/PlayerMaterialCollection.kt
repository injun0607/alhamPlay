package kr.alham.playground.domain.collection

import jakarta.persistence.*
import kr.alham.playground.domain.item.Material
import kr.alham.playground.domain.player.Player
import java.time.LocalDateTime

@Entity
@Table(
    uniqueConstraints = [UniqueConstraint(columnNames = ["player_id", "material_id"])]
)
class PlayerMaterialCollection(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    val player: Player = Player(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_id")
    val material: Material = Material(),

    val discoveredAt: LocalDateTime = LocalDateTime.now()
) {
    companion object {
        fun of(playerId: Long, materialId: Long): PlayerMaterialCollection {
            return PlayerMaterialCollection(
                player = Player(
                    id = playerId
                ),
                material = Material(
                    id = materialId
                )
            )
        }
    }
}