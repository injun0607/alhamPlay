package kr.alham.playground.service.player

import kr.alham.playground.domain.card.PlayerCard
import kr.alham.playground.domain.common.TargetElementStatus
import kr.alham.playground.domain.enums.BattlePhase
import kr.alham.playground.domain.enums.CardTarget
import kr.alham.playground.domain.enums.CardType
import kr.alham.playground.domain.player.Player
import kr.alham.playground.dto.card.CardIdDTO
import kr.alham.playground.repository.player.PlayerCardInfoRepository
import kr.alham.playground.repository.player.PlayerRepository
import kr.alham.playground.service.card.CardService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.*

@SpringBootTest
class PlayerServiceTest{

    lateinit var playerService: PlayerService

    @Autowired
    lateinit var playerRepository: PlayerRepository
    @Autowired
    lateinit var playerCardInfoRepository: PlayerCardInfoRepository

    @Autowired
    lateinit var cardService: CardService

    @BeforeEach
    fun setUp() {
        // 테스트용 플레이어 서비스 초기화
        playerService = PlayerService(
            playerRepository = playerRepository,
            playerCardInfoRepository = playerCardInfoRepository
        )
    }

    private fun saveTestPlayerAndCardInfo(){
        val player = Player (
            name = "테스트 플레이어"
        )

        val savePlayer = playerService.savePlayer(player)

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

        val playerOpponentCardDamage10 = PlayerCard(
            battlePhase = BattlePhase.ENGAGEMENT,
            name = "공격",
            description = "상대에게 10 데미지",
            cardTarget = CardTarget.OPPONENT,
            cardType = CardType.ATTACK,
            cost = 1,
            effectOpponentNum = 10.0,
            effectOpponentStat = TargetElementStatus.HP
        )

        val playerMutualCardSelf5op15 = PlayerCard(
            battlePhase = BattlePhase.FINALIZATION,
            name = "희생의 일격",
            description = "상대에게 15 데미지를 주고 자신은 5",
            cardTarget = CardTarget.MUTUAL,
            cardType = CardType.ATTACK,
            cost = 1,
            effectSelfNum = 5.0,
            effectSelfStat = TargetElementStatus.HP,
            effectOpponentNum = 15.0,
            effectOpponentStat = TargetElementStatus.HP
        )

        val playerBuffAtk3 = PlayerCard(
            battlePhase = BattlePhase.ENGAGEMENT,
            name = "집중 강화",
            description = "자신의 ATK +3",
            cardTarget = CardTarget.SELF,
            cardType = CardType.BUFF,
            cost = 1,
            effectSelfNum = 3.0,
            effectSelfStat = TargetElementStatus.ATK
        )

        val saveCardOne =  cardService.savePlayerCard(playerSelfCardAtk1)
        val saveCardTwo = cardService.savePlayerCard(playerOpponentCardDamage10)
        val saveCardThree = cardService.savePlayerCard(playerMutualCardSelf5op15)
        val saveCardFour = cardService.savePlayerCard(playerBuffAtk3)


        playerService.savePlayerCardInfo(savePlayer, saveCardOne)
        playerService.savePlayerCardInfo(savePlayer, saveCardTwo)
        playerService.savePlayerCardInfo(savePlayer, saveCardThree)
        playerService.savePlayerCardInfo(savePlayer, saveCardFour)

    }


    @Test
    fun savePlayerAndFind(){
        //플레이어의 정보가 저장이 잘되는지 확인하는 테스트
        val player = Player (
            name = "테스트 플레이어"
        )

        playerService.savePlayer(player)
        require(player.id != null) { "Player ID should not be null after saving." }
        val foundPlayer = playerService.findPlayerById(1L)

        assertEquals(foundPlayer.id, 1L)
        assertEquals(foundPlayer.name, "테스트 플레이어")
        assertEquals(foundPlayer.atk,1.0)

    }

    @Test
    fun `testFindPlayerById`(){

        val player = Player(
            name = "테스트 플레이어"
        )

        val savedPlayer = playerService.savePlayer(player)
        val foundPlayer = playerService.findPlayerById(1L)

        val playerId = requireNotNull(foundPlayer.id) { "Player ID should not be null after saving." }

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

        val playerOpponentCardDamage10 = PlayerCard(
            battlePhase = BattlePhase.ENGAGEMENT,
            name = "공격",
            description = "상대에게 10 데미지",
            cardTarget = CardTarget.OPPONENT,
            cardType = CardType.ATTACK,
            cost = 1,
            effectOpponentNum = 10.0,
            effectOpponentStat = TargetElementStatus.HP
        )

        val playerMutualCardSelf5op15 = PlayerCard(
            battlePhase = BattlePhase.FINALIZATION,
            name = "희생의 일격",
            description = "상대에게 15 데미지를 주고 자신은 5",
            cardTarget = CardTarget.MUTUAL,
            cardType = CardType.ATTACK,
            cost = 1,
            effectSelfNum = 5.0,
            effectSelfStat = TargetElementStatus.HP,
            effectOpponentNum = 15.0,
            effectOpponentStat = TargetElementStatus.HP
        )

        val playerBuffAtk3 = PlayerCard(
            battlePhase = BattlePhase.ENGAGEMENT,
            name = "집중 강화",
            description = "자신의 ATK +3",
            cardTarget = CardTarget.SELF,
            cardType = CardType.BUFF,
            cost = 1,
            effectSelfNum = 3.0,
            effectSelfStat = TargetElementStatus.ATK
        )

        val saveCardOne =  cardService.savePlayerCard(playerSelfCardAtk1)
        val saveCardTwo = cardService.savePlayerCard(playerOpponentCardDamage10)
        val saveCardThree = cardService.savePlayerCard(playerMutualCardSelf5op15)
        val saveCardFour = cardService.savePlayerCard(playerBuffAtk3)

        val emptyCardInfoList = playerService.findAllPlayerCardInfoByPlayerId(playerId)

        assertEquals(0, emptyCardInfoList.size, "Initially, there should be no card info for the player.")

        playerService.savePlayerCardInfo(foundPlayer, saveCardOne)
        playerService.savePlayerCardInfo(foundPlayer, saveCardTwo)
        playerService.savePlayerCardInfo(foundPlayer, saveCardThree)
        playerService.savePlayerCardInfo(foundPlayer, saveCardFour)


        val cardInfoList = playerService.findAllPlayerCardInfoByPlayerId(playerId)

        assertEquals(4,cardInfoList.size)

    }


    @Test
    fun hasPlayerCard() {
        // 플레이어가 특정 카드를 가지고 있는지 확인하는 테스트
        saveTestPlayerAndCardInfo()

        val foundPlayer = playerService.findPlayerById(1L);

        val cardDTOIdList = listOf(
            CardIdDTO(id = 1L),
            CardIdDTO(id = 2L),
            CardIdDTO(id = 3L),
            CardIdDTO(id = 4L)
        )
        //플레이어가 카드 안가지고있으면 false
        val falseResult = playerService.hasPlayerCard(1L, listOf(CardIdDTO(id = 5L), CardIdDTO(id = 6L)))
        //가지고있으면 true
        val trueResult = playerService.hasPlayerCard(1L,cardDTOIdList)

        //일부만 가지고있어도 false
        val partialResult = playerService.hasPlayerCard(1L, listOf(CardIdDTO(id = 1L), CardIdDTO(id = 5L)))

        assertEquals(false, falseResult, "Player should not have cards with IDs 5 and 6.")
        assertEquals(true, trueResult, "Player should have cards with IDs 1, 2, 3, and 4.")
        assertEquals(false, partialResult, "Player should not have cards with IDs 1 and 5, as 5 is not owned.")


    }


}