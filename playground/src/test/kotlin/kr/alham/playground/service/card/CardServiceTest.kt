package kr.alham.playground.service.card

import kr.alham.playground.domain.card.MonsterCard
import kr.alham.playground.domain.card.PlayerCard
import kr.alham.playground.domain.common.TargetElementStatus
import kr.alham.playground.domain.enums.BattlePhase
import kr.alham.playground.domain.enums.CardTarget
import kr.alham.playground.domain.enums.CardType
import kr.alham.playground.repository.card.MonsterCardRepository
import kr.alham.playground.repository.card.PlayerCardRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import kotlin.test.assertEquals

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class CardServiceTest{

    lateinit var cardService: CardService

    @Autowired
    lateinit var monsterCardRepository: MonsterCardRepository

    @Autowired
    lateinit var playerCardRepository: PlayerCardRepository

    @BeforeEach
    fun setUp() {
        // 테스트용 카드 서비스 초기화
        cardService = CardService(
            playerCardRepository = playerCardRepository,
            monsterCardRepository = monsterCardRepository
        )
    }

    @Test
    fun cardSaveTest(){
        val playerSelfCardAtk1 = PlayerCard(
            battlePhase = BattlePhase.PREPARATION,
            name = "자신어택강화",
            description = "자신의 ATK +1",
            cardTarget = CardTarget.SELF,
            cardType = CardType.BUFF,
            cost = 1,
            effectSelfNum = 1.0,
            effectSelfStat = TargetElementStatus.ATK
        )

        val monsterCard = MonsterCard(
            battlePhase = BattlePhase.PREPARATION,
            name = "몬스터카드",
            description = "몬스터 카드 테스트",
            cardTarget = CardTarget.SELF,
            cardType = CardType.ATTACK,
            cost = 2,
            effectSelfNum = 2.0,
            effectSelfStat = TargetElementStatus.HP
        )

        cardService.savePlayerCard(playerSelfCardAtk1)
        cardService.saveMonsterCard(monsterCard)

        val foundPlayerCard = cardService.findPlayerCardById(playerSelfCardAtk1.id!!)
        val foundMonsterCard = cardService.findMonsterCardById(monsterCard.id!!)

        assertEquals(foundPlayerCard.name, "자신어택강화")
        assertEquals(foundPlayerCard.effectSelfNum, 1.0)
        assertEquals(foundPlayerCard.effectSelfStat, TargetElementStatus.ATK)
        assertEquals(foundPlayerCard.cardType, CardType.BUFF)

        assertEquals(foundMonsterCard.name, "몬스터카드")
        assertEquals(foundMonsterCard.effectSelfNum, 2.0)
        assertEquals(foundMonsterCard.effectSelfStat, TargetElementStatus.HP)
        assertEquals(foundMonsterCard.cardType, CardType.ATTACK)

    }







}