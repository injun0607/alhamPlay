package kr.alham.playground.system.cardeffect

import kr.alham.playground.domain.card.PlayerCard
import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test

@SpringBootTest
class AloneCardEffectTest{

    @Autowired
    lateinit var aloneCardEffect: AloneCardEffect

    lateinit var playerCardBashSelf: PlayerCard
    lateinit var playerCardBashOpponent: PlayerCard
    lateinit var playerCardBashMutual: PlayerCard



    @Test
    fun applyEffectTest(){

    }


}