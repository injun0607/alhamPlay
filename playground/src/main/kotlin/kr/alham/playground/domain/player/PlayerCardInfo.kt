package kr.alham.playground.domain.player

import jakarta.persistence.*
import kr.alham.playground.domain.card.Card
import kr.alham.playground.domain.card.PlayerCard
import java.time.LocalDate


@Entity
@Table(name = "al_player_card_info")
class PlayerCardInfo(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    val player: Player = Player(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_card_id")
    val card: PlayerCard = PlayerCard(),

    val createDate: LocalDate? = null,
    val updateData: LocalDate? = null
) {

}