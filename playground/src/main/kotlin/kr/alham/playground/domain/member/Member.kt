package kr.alham.playground.domain.member

import jakarta.persistence.*
import kr.alham.playground.domain.player.Player

@Entity
@Table(name = "al_member")
class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
    var name: String =""
    var age: Int = 0
    var email : String = ""

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", unique = true)
    var player:Player = Player()
}