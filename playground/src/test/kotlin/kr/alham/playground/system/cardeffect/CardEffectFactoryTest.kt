package kr.alham.playground.system.cardeffect

import kr.alham.playground.domain.enums.CardType
import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test

@SpringBootTest
class CardEffectFactoryTest{

    @Autowired
    lateinit var cardEffectFactory: CardEffectFactory

    @Test
    fun getStartegyTest() {
        val strategy = cardEffectFactory.get(Pair(CardType.ATTACK,CardType.ATTACK))
        assertNotNull(strategy)
        assertTrue(strategy is AttackToAttackCardEffect)

        val defaultStrategy = cardEffectFactory.get(Pair(CardType.ATTACK, CardType.BUFF))
        assertNotNull(defaultStrategy)
        assertTrue(defaultStrategy is DefaultCardEffect)


        val defaultStrategy2 = cardEffectFactory.get(Pair( CardType.BUFF,CardType.ATTACK))
        assertNotNull(defaultStrategy2)
        assertTrue(defaultStrategy2 is DefaultCardEffect)

        val attackToEvasionStrategy = cardEffectFactory.get(Pair(CardType.ATTACK, CardType.EVASION))
        val attackToEvasionStrategy2 = cardEffectFactory.get(Pair( CardType.EVASION,CardType.ATTACK))

        assertNotNull(attackToEvasionStrategy)
        assertTrue(attackToEvasionStrategy is AttackToEvasionCardEffect)

        assertNotNull(attackToEvasionStrategy2)
        assertTrue(attackToEvasionStrategy2 is AttackToEvasionCardEffect)

        val attackToDefenceStrategy = cardEffectFactory.get(Pair(CardType.ATTACK, CardType.DEFENCE))
        val attackToDefenceStrategy2 = cardEffectFactory.get(Pair(CardType.DEFENCE, CardType.ATTACK))

        assertNotNull(attackToDefenceStrategy)
        assertTrue(attackToDefenceStrategy is AttackToDefenceCardEffect)
        assertNotNull(attackToDefenceStrategy2)
        assertTrue(attackToDefenceStrategy2 is AttackToDefenceCardEffect)


    }





}