package kr.alham.playground.domain.monster

import jakarta.persistence.*
import kr.alham.playground.domain.card.MonsterCard
import java.time.LocalDate


@Entity
class MonsterCardInfo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "monster_id")
    val monster: Monster = Monster(),

    @ManyToOne
    @JoinColumn(name = "monster_card_id")
    val card: MonsterCard = MonsterCard(),

    val createDate: LocalDate? = null,
    val updateData: LocalDate? = null

    ) {
}