package kr.alham.playground.domain.player

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import kr.alham.playground.domain.card.Card
import kr.alham.playground.domain.card.PlayerCard
import java.time.LocalDate


@Entity
@Table(name = "al_player_card_info")
class PlayerCardInfo(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "player_id")
    val player: Player = Player(),

    @ManyToOne
    @JoinColumn(name = "player_card_id")
    val card: PlayerCard = PlayerCard(),

    val createDate: LocalDate? = null,
    val updateData: LocalDate? = null
) {

}