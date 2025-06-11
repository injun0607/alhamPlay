package kr.alham.playground.service.battle

import kr.alham.playground.domain.battle.*
import kr.alham.playground.domain.card.Card
import kr.alham.playground.domain.card.MonsterCard
import kr.alham.playground.domain.card.PlayerCard
import kr.alham.playground.domain.common.TargetElementStatus
import kr.alham.playground.domain.enums.BattlePhase
import kr.alham.playground.domain.enums.BattleResult
import kr.alham.playground.domain.enums.CardTarget
import kr.alham.playground.domain.enums.CardType
import kr.alham.playground.domain.monster.Monster
import kr.alham.playground.domain.player.Player
import kr.alham.playground.dto.battle.MonsterBattleDTO
import kr.alham.playground.dto.card.CardIdDTO
import kr.alham.playground.service.card.CardService
import kr.alham.playground.service.monster.MonsterService
import kr.alham.playground.service.player.PlayerService
import kr.alham.playground.system.battle.monster.MonsterCardProvider
import kr.alham.playground.system.cardeffect.CardEffectFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import kotlin.test.assertEquals


@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ActiveProfiles("test")
class BattleServiceTest {


    lateinit var player : Player
    lateinit var monster: Monster

    lateinit var weakPlayer: Player
    lateinit var weakMonster: Monster

    lateinit var playerSelfCardAtk1: PlayerCard
    lateinit var playerOpponentCardDamage10: PlayerCard
    lateinit var playerOpponentCardDamage8: PlayerCard
    lateinit var playerMutualCardSelf5op15: PlayerCard
    lateinit var playerBuffAtk3: PlayerCard


    lateinit var monsterCardSelfDef1: MonsterCard
    lateinit var monsterOpponentCardAttack10: MonsterCard
    lateinit var monsterOpponentCardAttack11: MonsterCard
    lateinit var monsterOpponentCardAttack12: MonsterCard
    lateinit var monsterOpponentCardAttack5: MonsterCard
    lateinit var monsterOpponentCardAttack15: MonsterCard
    lateinit var monsterMutualCardSelf10toOp15: MonsterCard


    @Autowired
    lateinit var playerService: PlayerService
    @Autowired
    lateinit var monsterService: MonsterService
    @Autowired
    lateinit var cardEffectFactory: CardEffectFactory

    @Autowired
    lateinit var cardService: CardService

    @Autowired
    lateinit var monsterCardProvider: MonsterCardProvider

    lateinit var battleService: BattleService

    @BeforeEach
    fun setUp() {
        // 테스트용 플레이어와 몬스터를 초기화
         player = Player(
            name = "테스트플레이어",
            hp = 100.0,
        )

        monster = Monster(
            name = "테스트 몬스터",
            hp = 100.0,
        )

        weakPlayer = Player(
            name = "약한 플레이어",
            hp = 30.0,
        )

        weakMonster = Monster(
            name = "약한 몬스터",
            hp = 30.0,
        )



        playerSelfCardAtk1 = PlayerCard(
            battlePhase = BattlePhase.PREPARATION,
            name = "자신어택강화",
            description = "자신의 ATK +1",
            cardTarget = CardTarget.SELF,
            cardType = CardType.BUFF,
            cost = 1,
            effectSelfNum = 1.0,
            effectSelfStat = TargetElementStatus.ATK
        )

        playerOpponentCardDamage10 = PlayerCard(
            battlePhase = BattlePhase.ENGAGEMENT,
            name = "공격",
            description = "상대에게 10 데미지",
            cardTarget = CardTarget.OPPONENT,
            cardType = CardType.ATTACK,
            cost = 1,
            effectOpponentNum = 10.0,
            effectOpponentStat = TargetElementStatus.HP
        )

        playerOpponentCardDamage8 = PlayerCard(
            battlePhase = BattlePhase.ENGAGEMENT,
            name = "공격",
            description = "상대에게 8 데미지",
            cardTarget = CardTarget.OPPONENT,
            cardType = CardType.ATTACK,
            cost = 1,
            effectOpponentNum = 8.0,
            effectOpponentStat = TargetElementStatus.HP
        )

        playerMutualCardSelf5op15 = PlayerCard(
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

        playerBuffAtk3 = PlayerCard(
            battlePhase = BattlePhase.ENGAGEMENT,
            name = "집중 강화",
            description = "자신의 ATK +3",
            cardTarget = CardTarget.SELF,
            cardType = CardType.BUFF,
            cost = 1,
            effectSelfNum = 3.0,
            effectSelfStat = TargetElementStatus.ATK
        )


        monsterCardSelfDef1 = MonsterCard(
            battlePhase = BattlePhase.PREPARATION,
            name = "자신방어강화",
            description = "자신의 DEF +1",
            cardTarget = CardTarget.SELF,
            cardType = CardType.BUFF,
            cost = 1,
            effectSelfNum = 1.0,
            effectSelfStat = TargetElementStatus.DEF,
            battleOrder = 1
        )

        monsterOpponentCardAttack10 = MonsterCard(
            battlePhase = BattlePhase.ENGAGEMENT,
            name = "공격",
            description = "상대에게 10 데미지",
            cardTarget = CardTarget.OPPONENT,
            cardType = CardType.ATTACK,
            cost = 1,
            effectOpponentNum = 10.0,
            effectOpponentStat = TargetElementStatus.HP,
            battleOrder = 1
        )

        monsterOpponentCardAttack5 = MonsterCard(
            battlePhase = BattlePhase.ENGAGEMENT,
            name = "강타",
            description = "상대에게 5 데미지",
            cardTarget = CardTarget.OPPONENT,
            cardType = CardType.ATTACK,
            cost = 1,
            effectOpponentNum = 5.0,
            effectOpponentStat = TargetElementStatus.HP,
            battleOrder = 2
        )

        monsterOpponentCardAttack15 = MonsterCard(
            battlePhase = BattlePhase.ENGAGEMENT,
            name = "강타",
            description = "상대에게 15 데미지",
            cardTarget = CardTarget.OPPONENT,
            cardType = CardType.ATTACK,
            cost = 1,
            effectOpponentNum = 15.0,
            effectOpponentStat = TargetElementStatus.HP,
            battleOrder = 3
        )

        monsterOpponentCardAttack11 = MonsterCard(
            battlePhase = BattlePhase.ENGAGEMENT,
            name = "공격",
            description = "상대에게 11 데미지",
            cardTarget = CardTarget.OPPONENT,
            cardType = CardType.ATTACK,
            cost = 1,
            effectOpponentNum = 11.0,
            effectOpponentStat = TargetElementStatus.HP,
            battleOrder = 4
        )

        monsterOpponentCardAttack12 = MonsterCard(
            battlePhase = BattlePhase.ENGAGEMENT,
            name = "공격",
            description = "상대에게 12 데미지",
            cardTarget = CardTarget.OPPONENT,
            cardType = CardType.ATTACK,
            cost = 1,
            effectOpponentNum = 12.0,
            effectOpponentStat = TargetElementStatus.HP,
            battleOrder = 5
        )


        monsterMutualCardSelf10toOp15 = MonsterCard(
            battlePhase = BattlePhase.FINALIZATION,
            name = "희생의 일격",
            description = "상대에게 15 데미지를 주고 자신은 10",
            cardTarget = CardTarget.MUTUAL,
            cardType = CardType.ATTACK,
            cost = 1,
            effectSelfNum = 10.0,
            effectSelfStat = TargetElementStatus.HP,
            effectOpponentNum = 15.0,
            effectOpponentStat = TargetElementStatus.HP
        )


        cardService.savePlayerCard(playerSelfCardAtk1)
        cardService.savePlayerCard(playerOpponentCardDamage8)
        cardService.savePlayerCard(playerOpponentCardDamage10)
        cardService.savePlayerCard(playerMutualCardSelf5op15)
        cardService.savePlayerCard(playerBuffAtk3)

        cardService.saveMonsterCard(monsterCardSelfDef1)
        cardService.saveMonsterCard(monsterOpponentCardAttack15)
        cardService.saveMonsterCard(monsterOpponentCardAttack10)
        cardService.saveMonsterCard(monsterOpponentCardAttack11)
        cardService.saveMonsterCard(monsterOpponentCardAttack12)
        cardService.saveMonsterCard(monsterOpponentCardAttack5)
        cardService.saveMonsterCard(monsterMutualCardSelf10toOp15)

        //플레이어저장
        val savePlayer = playerService.savePlayer(player)
        val saveWeakPlayer = playerService.savePlayer(weakPlayer)
        //몬스터저장
        val saveMonster = monsterService.saveMonster(monster)
        val saveWeakMonster = monsterService.saveMonster(weakMonster)



        //플레이어 카드정보저장
        playerService.savePlayerCardInfo(savePlayer, playerSelfCardAtk1)
        playerService.savePlayerCardInfo(savePlayer, playerOpponentCardDamage10)
        playerService.savePlayerCardInfo(savePlayer, playerOpponentCardDamage8)
        playerService.savePlayerCardInfo(savePlayer, playerMutualCardSelf5op15)
        playerService.savePlayerCardInfo(savePlayer, playerBuffAtk3)

        // //약한 플레이어 카드정보저장
        playerService.savePlayerCardInfo(saveWeakPlayer, playerSelfCardAtk1)
        playerService.savePlayerCardInfo(saveWeakPlayer, playerOpponentCardDamage10)
        playerService.savePlayerCardInfo(saveWeakPlayer, playerOpponentCardDamage8)
        playerService.savePlayerCardInfo(saveWeakPlayer, playerMutualCardSelf5op15)
        playerService.savePlayerCardInfo(saveWeakPlayer, playerBuffAtk3)

        //몬스터 카드정보저장
        monsterService.saveMonsterCardInfo(saveMonster, monsterCardSelfDef1)
        monsterService.saveMonsterCardInfo(saveMonster, monsterOpponentCardAttack15)
        monsterService.saveMonsterCardInfo(saveMonster, monsterOpponentCardAttack10)
        monsterService.saveMonsterCardInfo(saveMonster, monsterOpponentCardAttack11)
        monsterService.saveMonsterCardInfo(saveMonster, monsterOpponentCardAttack12)
        monsterService.saveMonsterCardInfo(saveMonster, monsterOpponentCardAttack5)
        monsterService.saveMonsterCardInfo(saveMonster, monsterMutualCardSelf10toOp15)

        // //약한 몬스터 카드정보저장
        monsterService.saveMonsterCardInfo(saveWeakMonster, monsterCardSelfDef1)
        monsterService.saveMonsterCardInfo(saveWeakMonster, monsterOpponentCardAttack15)
        monsterService.saveMonsterCardInfo(saveWeakMonster, monsterOpponentCardAttack10)
        monsterService.saveMonsterCardInfo(saveWeakMonster, monsterOpponentCardAttack11)
        monsterService.saveMonsterCardInfo(saveWeakMonster, monsterOpponentCardAttack12)
        monsterService.saveMonsterCardInfo(saveWeakMonster, monsterOpponentCardAttack5)
        monsterService.saveMonsterCardInfo(saveWeakMonster, monsterMutualCardSelf10toOp15)

        battleService = BattleService(
            playerService = playerService,
            monsterService = monsterService,
            cardEffectFactory = cardEffectFactory,
            monsterCardProvider = monsterCardProvider
        )
    }

    @Test
    fun playBattle(){
        val monsterBattleDTO = MonsterBattleDTO(
            playerId = 1L,
            monsterId = 1L,
            preparationCard = listOf(
                CardIdDTO(playerSelfCardAtk1.id!!),
            ),
            engagementCardList = listOf(
                CardIdDTO(playerBuffAtk3.id!!),
                CardIdDTO(playerOpponentCardDamage10.id!!),
                CardIdDTO(playerOpponentCardDamage10.id!!),
                CardIdDTO(playerOpponentCardDamage8.id!!),
                CardIdDTO(playerOpponentCardDamage10.id!!),
            ),
            finalizationCard = listOf(
                CardIdDTO(playerMutualCardSelf5op15.id!!),
            )
        )


        //유저가 해당 카드정보를 가지고 있는지 확인
        val playerId = monsterBattleDTO.playerId
        val monsterId = monsterBattleDTO.monsterId

        //
        assertEquals(true , playerService.hasPlayerCard(playerId,monsterBattleDTO.preparationCard)  )
        assertEquals(true , playerService.hasPlayerCard(playerId,monsterBattleDTO.engagementCardList)  )
        assertEquals(true , playerService.hasPlayerCard(playerId,monsterBattleDTO.finalizationCard)  )


    }

    @Test
    fun initMonsterBattleTest(){

        //테스트해야할거 ->
        //플레이어 정보 불러와서 -> 카드 오더대로 잘 맞춰졌는지

        val monsterBattleDTO = MonsterBattleDTO(
            playerId = 1L,
            monsterId = 1L,
            preparationCard = listOf(
                CardIdDTO(playerSelfCardAtk1.id!!),
            ),
            engagementCardList = listOf(
                CardIdDTO(playerBuffAtk3.id!!),
                CardIdDTO(playerOpponentCardDamage10.id!!),
                CardIdDTO(playerOpponentCardDamage10.id!!),
                CardIdDTO(playerOpponentCardDamage8.id!!),
                CardIdDTO(playerOpponentCardDamage10.id!!),
            ),
            finalizationCard = listOf(
                CardIdDTO(playerMutualCardSelf5op15.id!!),
            )
        )


        val battleStatus = initMonsterBattleState(monsterBattleDTO)
        //배틀 카드 순서
        //버프 -> 10 -> 10 -> 8 -> 10

        //우선 플레이어 카드 제대로있는지
        assertEquals(1, battleStatus.preparationMonsterBattleStatus.playerCardList.size)
        assertEquals(1, battleStatus.preparationMonsterBattleStatus.monsterCardList.size)

        assertEquals(5, battleStatus.engagementMonsterBattleStatus.playerCardList.size)
        assertEquals(5, battleStatus.engagementMonsterBattleStatus.monsterCardList.size)

        assertEquals(1, battleStatus.finalizationMonsterBattleStatus.playerCardList.size)
        assertEquals(1, battleStatus.finalizationMonsterBattleStatus.monsterCardList.size)

        assertEquals(playerSelfCardAtk1.id!!, battleStatus.preparationMonsterBattleStatus.playerCardList[0].id)
        assertEquals(monsterCardSelfDef1.id!!, battleStatus.preparationMonsterBattleStatus.monsterCardList[0].id)

        //플레이어 카드 오더대로 잘 정렬되었는지 확인
        assertEquals(playerBuffAtk3.id!!, battleStatus.engagementMonsterBattleStatus.playerCardList[0].id)
        assertEquals(playerOpponentCardDamage10.id!!, battleStatus.engagementMonsterBattleStatus.playerCardList[1].id)
        assertEquals(playerOpponentCardDamage10.id!!, battleStatus.engagementMonsterBattleStatus.playerCardList[2].id)
        assertEquals(playerOpponentCardDamage8.id!!, battleStatus.engagementMonsterBattleStatus.playerCardList[3].id)
        assertEquals(playerOpponentCardDamage10.id!!, battleStatus.engagementMonsterBattleStatus.playerCardList[4].id)

        assertEquals(monsterOpponentCardAttack10.id!!, battleStatus.engagementMonsterBattleStatus.monsterCardList[0].id)
        assertEquals(monsterOpponentCardAttack5.id!!, battleStatus.engagementMonsterBattleStatus.monsterCardList[1].id)
        assertEquals(monsterOpponentCardAttack15.id!!, battleStatus.engagementMonsterBattleStatus.monsterCardList[2].id)


        assertEquals(monsterMutualCardSelf10toOp15.id!!, battleStatus.finalizationMonsterBattleStatus.monsterCardList[0].id)
        assertEquals(playerMutualCardSelf5op15.id!!, battleStatus.finalizationMonsterBattleStatus.playerCardList[0].id)


    }

    @Test
    fun battlePreparationTest() {
        //배틀 준비단계 테스트
        val monsterBattleDTO = MonsterBattleDTO(
            playerId = 1L,
            monsterId = 1L,
            preparationCard = listOf(
                CardIdDTO(playerSelfCardAtk1.id!!),
            ),
            engagementCardList = listOf(
                CardIdDTO(playerBuffAtk3.id!!),
                CardIdDTO(playerOpponentCardDamage10.id!!),
                CardIdDTO(playerOpponentCardDamage10.id!!),
                CardIdDTO(playerOpponentCardDamage8.id!!),
                CardIdDTO(playerOpponentCardDamage10.id!!),
            ),
            finalizationCard = listOf(
                CardIdDTO(playerMutualCardSelf5op15.id!!),
            )
        )

        val battleStatus = initMonsterBattleState(monsterBattleDTO)

        val player = battleStatus.player
        val monster = battleStatus.monster

        val playerStatus = battleStatus.getPreparationSelfStatus()
        val monsterStatus = battleStatus.getPreparationOpponentStatus()

        val playerCardList = battleStatus.preparationMonsterBattleStatus.playerCardList
        val monsterCardList = battleStatus.preparationMonsterBattleStatus.monsterCardList

        val monsterStrategy = battleStatus.monsterCardStrategy

        for(i in 0 until 1){
            val playerCard = selectPlayerCard(i, playerCardList)
            val monsterCard = monsterStrategy.getMonsterCard(i, monsterStatus, monsterCardList)

            val cardStrategy =  cardEffectFactory.get(Pair(playerCard.cardType, monsterCard.cardType))
            val playerBattleStatus = BattleStatus(playerCard, playerStatus, player)
            val monsterBattleStatus = BattleStatus(monsterCard, monsterStatus, monster)

            cardStrategy.applyEffect(playerBattleStatus, monsterBattleStatus)
        }

        battleStatus.engagementMonsterBattleStatus.playerStatus = playerStatus.clone()
        battleStatus.engagementMonsterBattleStatus.monsterStatus = monsterStatus.clone()

        assertEquals(2.0,battleStatus.engagementMonsterBattleStatus.playerStatus.get(TargetElementStatus.ATK))
        assertEquals(1.0,battleStatus.engagementMonsterBattleStatus.monsterStatus.get(TargetElementStatus.DEF))

    }

    @Test
    fun engagementTest(){

        val monsterBattleDTO = MonsterBattleDTO(
            playerId = 1L,
            monsterId = 1L,
            preparationCard = listOf(
                CardIdDTO(playerSelfCardAtk1.id!!),
            ),
            engagementCardList = listOf(
                CardIdDTO(playerBuffAtk3.id!!),
                CardIdDTO(playerOpponentCardDamage10.id!!),
                CardIdDTO(playerOpponentCardDamage10.id!!),
                CardIdDTO(playerOpponentCardDamage8.id!!),
                CardIdDTO(playerOpponentCardDamage10.id!!),
            ),
            finalizationCard = listOf(
                CardIdDTO(playerMutualCardSelf5op15.id!!),
            )
        )

        val monsterBattleWeakPlayerDTO = MonsterBattleDTO(
            playerId = 2L,
            monsterId = 1L,
            preparationCard = listOf(
                CardIdDTO(playerSelfCardAtk1.id!!),
            ),
            engagementCardList = listOf(
                CardIdDTO(playerBuffAtk3.id!!),
                CardIdDTO(playerOpponentCardDamage10.id!!),
                CardIdDTO(playerOpponentCardDamage10.id!!),
                CardIdDTO(playerOpponentCardDamage8.id!!),
                CardIdDTO(playerOpponentCardDamage10.id!!),
            ),
            finalizationCard = listOf(
                CardIdDTO(playerMutualCardSelf5op15.id!!),
            )
        )

        val monsterBattleWeakMonsterDTO = MonsterBattleDTO(
            playerId = 1L,
            monsterId = 2L,
            preparationCard = listOf(
                CardIdDTO(playerSelfCardAtk1.id!!),
            ),
            engagementCardList = listOf(
                CardIdDTO(playerBuffAtk3.id!!),
                CardIdDTO(playerOpponentCardDamage10.id!!),
                CardIdDTO(playerOpponentCardDamage10.id!!),
                CardIdDTO(playerOpponentCardDamage8.id!!),
                CardIdDTO(playerOpponentCardDamage10.id!!),
            ),
            finalizationCard = listOf(
                CardIdDTO(playerMutualCardSelf5op15.id!!),
            )
        )

        val battleState = initMonsterBattleState(monsterBattleDTO)

        val player = battleState.player
        val monster = battleState.monster

        val playerStatus = battleState.getEngagementSelfStatus()
        val monsterStatus = battleState.getEngagementOpponentStatus()

        val playerCardList = battleState.engagementMonsterBattleStatus.playerCardList
        val monsterCardList = battleState.engagementMonsterBattleStatus.monsterCardList

        //순서대로 진행(카드개수가 안맞는 경우가 생길수있음)
        val monsterStrategy = battleState.monsterCardStrategy
        for (i in 0 until 5) {

            val playerCard = selectPlayerCard(i, playerCardList)
            val monsterCard = monsterStrategy.getMonsterCard(i, monsterStatus, monsterCardList)

            val cardStrategy = cardEffectFactory.get(Pair(playerCard.cardType, monsterCard.cardType))
            val playerBattleStatus = BattleStatus(playerCard, playerStatus, player)
            val monsterBattleStatus = BattleStatus(monsterCard, monsterStatus, monster)
            cardStrategy.applyEffect(playerBattleStatus, monsterBattleStatus)
            //둘중 하나라도 hp가 0이 되었으면 마지막 페이지 진입
            if(checkFinalizationPhase(playerBattleStatus,monsterBattleStatus)){
                break
            }
        }

        battleState.finalizationMonsterBattleStatus.playerStatus = playerStatus.clone()
        battleState.finalizationMonsterBattleStatus.monsterStatus = monsterStatus.clone()

        assertEquals(62.0,monsterStatus.get(TargetElementStatus.HP))
        assertEquals(47.0,playerStatus.get(TargetElementStatus.HP))

        val weakPlayerMonsterBattleState = initMonsterBattleState(monsterBattleWeakPlayerDTO)
        val weakPlayer = weakPlayerMonsterBattleState.player
        val strongMonster = weakPlayerMonsterBattleState.monster

        val weakPlayerStatus = weakPlayerMonsterBattleState.getEngagementSelfStatus()
        val strongMonsterStatus = weakPlayerMonsterBattleState.getEngagementOpponentStatus()

        val weakPlayerCardList = weakPlayerMonsterBattleState.engagementMonsterBattleStatus.playerCardList
        val strongMonsterCardList = weakPlayerMonsterBattleState.engagementMonsterBattleStatus.monsterCardList

        //순서대로 진행(카드개수가 안맞는 경우가 생길수있음)
        val strongMonsterStrategy = weakPlayerMonsterBattleState.monsterCardStrategy
        for (i in 0 until 5) {

            val playerCard = selectPlayerCard(i, weakPlayerCardList)
            val monsterCard = strongMonsterStrategy.getMonsterCard(i, strongMonsterStatus, strongMonsterCardList)

            val cardStrategy = cardEffectFactory.get(Pair(playerCard.cardType, monsterCard.cardType))
            val playerBattleStatus = BattleStatus(playerCard, weakPlayerStatus, weakPlayer)
            val monsterBattleStatus = BattleStatus(monsterCard, strongMonsterStatus, strongMonster)
            cardStrategy.applyEffect(playerBattleStatus, monsterBattleStatus)
            //둘중 하나라도 hp가 0이 되었으면 마지막 페이지 진입
            if(checkFinalizationPhase(playerBattleStatus,monsterBattleStatus)){
                break
            }
        }


        assertEquals(80.0,strongMonsterStatus.get(TargetElementStatus.HP))
        assertEquals(0.0,weakPlayerStatus.get(TargetElementStatus.HP))

        //
        val weakMonsterMonsterBattleState = initMonsterBattleState(monsterBattleWeakMonsterDTO)
        val strongPlayer = weakMonsterMonsterBattleState.player
        val weakMonster = weakMonsterMonsterBattleState.monster

        val strongPlayerStatus = weakMonsterMonsterBattleState.getEngagementSelfStatus()
        val weakMonsterStatus = weakMonsterMonsterBattleState.getEngagementOpponentStatus()

        val strongPlayerCardList = weakMonsterMonsterBattleState.engagementMonsterBattleStatus.playerCardList
        val weakMonsterCardList = weakMonsterMonsterBattleState.engagementMonsterBattleStatus.monsterCardList

        //순서대로 진행(카드개수가 안맞는 경우가 생길수있음)
        val weakMonsterStrategy = weakMonsterMonsterBattleState.monsterCardStrategy
        for (i in 0 until 5) {

            val playerCard = selectPlayerCard(i, strongPlayerCardList)
            val monsterCard = weakMonsterStrategy.getMonsterCard(i, weakMonsterStatus, weakMonsterCardList)

            val cardStrategy = cardEffectFactory.get(Pair(playerCard.cardType, monsterCard.cardType))
            val playerBattleStatus = BattleStatus(playerCard, strongPlayerStatus, strongPlayer)
            val monsterBattleStatus = BattleStatus(monsterCard, weakMonsterStatus, weakMonster)
            cardStrategy.applyEffect(playerBattleStatus, monsterBattleStatus)
            //둘중 하나라도 hp가 0이 되었으면 마지막 페이지 진입
            if(checkFinalizationPhase(playerBattleStatus,monsterBattleStatus)){
                break
            }
        }

        assertEquals(0.0,weakMonsterStatus.get(TargetElementStatus.HP))
        assertEquals(47.0,strongPlayerStatus.get(TargetElementStatus.HP))

    }

    @Test
    fun finalizationTest() {
        //배틀 최종단계 테스트
        val monsterBattleDTO = MonsterBattleDTO(
            playerId = 1L,
            monsterId = 1L,
            preparationCard = listOf(
                CardIdDTO(playerSelfCardAtk1.id!!),
            ),
            engagementCardList = listOf(
                CardIdDTO(playerBuffAtk3.id!!),
                CardIdDTO(playerOpponentCardDamage10.id!!),
                CardIdDTO(playerOpponentCardDamage10.id!!),
                CardIdDTO(playerOpponentCardDamage8.id!!),
                CardIdDTO(playerOpponentCardDamage10.id!!),
            ),
            finalizationCard = listOf(
                CardIdDTO(playerMutualCardSelf5op15.id!!),
            )
        )

        val battleState = initMonsterBattleState(monsterBattleDTO)

        val playerStatus = battleState.getFinalizationSelfStatus()
        val monsterStatus = battleState.getFinalizationOpponentStatus()

        val playerCardList = battleState.finalizationMonsterBattleStatus.playerCardList
        val monsterCardList = battleState.finalizationMonsterBattleStatus.monsterCardList

        //카드 오더대로 진행
        for (i in 0 until 1) {
            val playerCard = selectPlayerCard(i, playerCardList)
            val monsterCard = selectPlayerCard(i, monsterCardList)

            val cardStrategy = cardEffectFactory.get(Pair(playerCard.cardType, monsterCard.cardType))
            val playerBattleStatus = BattleStatus(playerCard, playerStatus, battleState.player)
            val monsterBattleStatus = BattleStatus(monsterCard, monsterStatus, battleState.monster)
            cardStrategy.applyEffect(playerBattleStatus, monsterBattleStatus)
        }

        assertEquals(80.0,playerStatus.get(TargetElementStatus.HP))
        assertEquals(75.0,monsterStatus.get(TargetElementStatus.HP))

    }

    @Test
    fun `최종 배틀 상태 확인 테스트`(){

        val monsterBattleDTO = MonsterBattleDTO(
            playerId = 1L,
            monsterId = 1L,
            preparationCard = listOf(
                CardIdDTO(playerSelfCardAtk1.id!!),
            ),
            engagementCardList = listOf(
                CardIdDTO(playerBuffAtk3.id!!),
                CardIdDTO(playerOpponentCardDamage10.id!!),
                CardIdDTO(playerOpponentCardDamage10.id!!),
                CardIdDTO(playerOpponentCardDamage8.id!!),
                CardIdDTO(playerOpponentCardDamage10.id!!),
            ),
            finalizationCard = listOf(
                CardIdDTO(playerMutualCardSelf5op15.id!!),
            )
        )

        val battleStatus = monsterBattle(monsterBattleDTO)

        assertEquals(2.0,battleStatus.preparationMonsterBattleStatus.playerStatus.get(TargetElementStatus.ATK))
        assertEquals(1.0,battleStatus.preparationMonsterBattleStatus.monsterStatus.get(TargetElementStatus.DEF))
        assertEquals(100.0 , battleStatus.preparationMonsterBattleStatus.playerStatus.get(TargetElementStatus.HP))
        assertEquals(100.0 , battleStatus.preparationMonsterBattleStatus.monsterStatus.get(TargetElementStatus.HP))

        assertEquals(47.0,battleStatus.engagementMonsterBattleStatus.playerStatus.get(TargetElementStatus.HP))
        assertEquals(66.0,battleStatus.engagementMonsterBattleStatus.monsterStatus.get(TargetElementStatus.HP))
        assertEquals(5.0,battleStatus.engagementMonsterBattleStatus.playerStatus.get(TargetElementStatus.ATK))

        assertEquals(5.0,battleStatus.finalizationMonsterBattleStatus.playerStatus.get(TargetElementStatus.ATK))
        assertEquals(27.0,battleStatus.finalizationMonsterBattleStatus.playerStatus.get(TargetElementStatus.HP))
        assertEquals(42.0,battleStatus.finalizationMonsterBattleStatus.monsterStatus.get(TargetElementStatus.HP))
    }

    @Test
    fun `battleServiceMonsterBattleTest - battleResult 확인 테스트`(){
        val monsterBattleDTO = MonsterBattleDTO(
            playerId = 1L,
            monsterId = 1L,
            preparationCard = listOf(
                CardIdDTO(playerSelfCardAtk1.id!!),
            ),
            engagementCardList = listOf(
                CardIdDTO(playerBuffAtk3.id!!),
                CardIdDTO(playerOpponentCardDamage10.id!!),
                CardIdDTO(playerOpponentCardDamage10.id!!),
                CardIdDTO(playerOpponentCardDamage8.id!!),
                CardIdDTO(playerOpponentCardDamage10.id!!),
            ),
            finalizationCard = listOf(
                CardIdDTO(playerMutualCardSelf5op15.id!!),
            )
        )

        val battleResult = battleService.monsterBattle(monsterBattleDTO)

        assertEquals(BattleResult.LOSE, battleResult)
    }


    private fun monsterBattle(monsterBattleDTO: MonsterBattleDTO): MonsterBattleState{
        val battleState = initMonsterBattleState(monsterBattleDTO)
        preparationBattle(battleState)
        engagementBattle(battleState)
        finalizationBattle(battleState)

        return battleState
    }

    private fun initMonsterBattleState(monsterBattleDTO: MonsterBattleDTO): MonsterBattleState{
        val player = playerService.findPlayerById(monsterBattleDTO.playerId)
        val monster = monsterService.findMonsterById(monsterBattleDTO.monsterId)

        val playerId = requireNotNull(player.id)
        val monsterId = requireNotNull(monster.id)

        val playerPreparationCardIdList = monsterBattleDTO.preparationCard

        //플레이어 준비단계 카드 확인
        if (!playerService.hasPlayerCard(playerId, playerPreparationCardIdList)) {
            throw IllegalArgumentException("Player does not have the required cards for battle. Preparation Card List: $playerPreparationCardIdList")
        }

        //플레이어 진행단계 카드 확인
        val playerEngagementCardIdList = monsterBattleDTO.engagementCardList
        if (!playerService.hasPlayerCard(playerId, playerEngagementCardIdList)) {
            throw IllegalArgumentException("Player does not have the required cards for engagement. Engagement Card List: $playerEngagementCardIdList")
        }

        //플레이어 최종단계 카드 확인
        val playerFinalizationCardIdList = monsterBattleDTO.finalizationCard
        if (!playerService.hasPlayerCard(playerId, playerFinalizationCardIdList)) {
            throw IllegalArgumentException("Player does not have the required cards for finalization. Finalization Card List: $playerFinalizationCardIdList")
        }

        val playerAllCardIdDTO = playerPreparationCardIdList + playerEngagementCardIdList + playerFinalizationCardIdList

        val playerCardList = playerService.findPlayerCardInfoListByPlayerIdAndCardIdList(playerId,playerAllCardIdDTO.map{it.id})

        if(playerCardList.isEmpty()){
            throw IllegalArgumentException("Player does not have any valid cards for battle. Player Card List: $playerCardList")
        }

        val filteredPreparationCardList = playerCardList
            .filter { it.card.battlePhase ==  BattlePhase.PREPARATION }
            .map{ it.card }

        val playerPreparationCardList = orderCardList(playerPreparationCardIdList, filteredPreparationCardList)

        val filteredEngagementCardList = playerCardList
            .filter { it.card.battlePhase == BattlePhase.ENGAGEMENT }
            .map{ it.card }

        val playerEngagementCardList = orderCardList(playerEngagementCardIdList, filteredEngagementCardList)

        val filteredFinalizationCardList = playerCardList
            .filter { it.card.battlePhase == BattlePhase.FINALIZATION }
            .map{ it.card }

        val playerFinalizationCardList = orderCardList(playerFinalizationCardIdList, filteredFinalizationCardList)

        //monsterCard 확인
        val monsterCardList = monsterService.findAllMonsterCardInfoByMonsterId(monsterId)

        val monsterPreparationCardList = monsterCardList
            .filter { it.card.battlePhase == BattlePhase.PREPARATION }
            .map { it.card }
            .sortedBy { it.battleOrder }

        val monsterEngagementCardList = monsterCardList
            .filter { it.card.battlePhase == BattlePhase.ENGAGEMENT }
            .map { it.card }
            .sortedBy { it.battleOrder }

        val monsterFinalizationCardList = monsterCardList
            .filter { it.card.battlePhase == BattlePhase.FINALIZATION }
            .map { it.card }
            .sortedBy { it.battleOrder }


        val preparationMonsterBattleStatus = PreparationMonsterBattleStatus(
            playerStatus = player.getStatus().clone(),
            monsterStatus = monster.getStatus().clone(),
            playerCardList = playerPreparationCardList,
            monsterCardList = monsterPreparationCardList
        )

        val engagementMonsterBattleStatus = EngagementMonsterBattleStatus(
            playerStatus = player.getStatus().clone(),
            monsterStatus = monster.getStatus().clone(),
            playerCardList = playerEngagementCardList,
            monsterCardList = monsterEngagementCardList
        )

        val finalizationMonsterBattleStatus = FinalizationBattleStatus(
            playerStatus = player.getStatus().clone(),
            monsterStatus = monster.getStatus().clone(),
            playerCardList = playerFinalizationCardList,
            monsterCardList = monsterFinalizationCardList
        )

        return MonsterBattleState(
            preparationMonsterBattleStatus = preparationMonsterBattleStatus,
            engagementMonsterBattleStatus = engagementMonsterBattleStatus,
            finalizationMonsterBattleStatus = finalizationMonsterBattleStatus,
            monsterCardStrategy = monsterCardProvider.getMonsterProvider(monster.monsterType),
            player = player,
            monster = monster
        )
    }

    private fun preparationBattle(battleState: MonsterBattleState): MonsterBattleState{
        val player = battleState.player
        val monster = battleState.monster

        val playerStatus = battleState.getPreparationSelfStatus()
        val monsterStatus = battleState.getPreparationOpponentStatus()

        val playerCardList = battleState.preparationMonsterBattleStatus.playerCardList
        val monsterCardList = battleState.preparationMonsterBattleStatus.monsterCardList

        val monsterStrategy = battleState.monsterCardStrategy

        for(i in 0 until 1){
            val playerCard = selectPlayerCard(i, playerCardList)
            val monsterCard = monsterStrategy.getMonsterCard(i, monsterStatus, monsterCardList)

            val cardStrategy =  cardEffectFactory.get(Pair(playerCard.cardType, monsterCard.cardType))
            val playerBattleStatus = BattleStatus(playerCard, playerStatus, player)
            val monsterBattleStatus = BattleStatus(monsterCard, monsterStatus, monster)

            cardStrategy.applyEffect(playerBattleStatus, monsterBattleStatus)
        }

        battleState.engagementMonsterBattleStatus.playerStatus = playerStatus.clone()
        battleState.engagementMonsterBattleStatus.monsterStatus = monsterStatus.clone()

        return battleState
    }

    private fun engagementBattle(battleState: MonsterBattleState): MonsterBattleState{
        val player = battleState.player
        val monster = battleState.monster

        val playerStatus = battleState.getEngagementSelfStatus()
        val monsterStatus = battleState.getEngagementOpponentStatus()

        val playerCardList = battleState.engagementMonsterBattleStatus.playerCardList
        val monsterCardList = battleState.engagementMonsterBattleStatus.monsterCardList

        //순서대로 진행(카드개수가 안맞는 경우가 생길수있음)
        val monsterStrategy = battleState.monsterCardStrategy
        for (i in 0 until 5) {

            val playerCard = selectPlayerCard(i, playerCardList)
            val monsterCard = monsterStrategy.getMonsterCard(i, monsterStatus, monsterCardList)

            val cardStrategy = cardEffectFactory.get(Pair(playerCard.cardType, monsterCard.cardType))
            val playerBattleStatus = BattleStatus(playerCard, playerStatus, player)
            val monsterBattleStatus = BattleStatus(monsterCard, monsterStatus, monster)
            cardStrategy.applyEffect(playerBattleStatus, monsterBattleStatus)
            //둘중 하나라도 hp가 0이 되었으면 마지막 페이지 진입
            if(checkFinalizationPhase(playerBattleStatus,monsterBattleStatus)){
                break
            }
        }

        battleState.finalizationMonsterBattleStatus.playerStatus = playerStatus.clone()
        battleState.finalizationMonsterBattleStatus.monsterStatus = monsterStatus.clone()

        return battleState
    }

    private fun finalizationBattle(battleState: MonsterBattleState): MonsterBattleState{
        val player = battleState.player
        val monster = battleState.monster

        val playerStatus = battleState.getFinalizationSelfStatus()
        val monsterStatus = battleState.getFinalizationOpponentStatus()

        val playerCardList = battleState.finalizationMonsterBattleStatus.playerCardList
        val monsterCardList = battleState.finalizationMonsterBattleStatus.monsterCardList

        //순서대로 진행(카드개수가 안맞는 경우가 생길수있음)
        val monsterStrategy = battleState.monsterCardStrategy
        for (i in 0 until 1) {

            val playerCard = selectPlayerCard(i, playerCardList)
            val monsterCard = monsterStrategy.getMonsterCard(i, monsterStatus, monsterCardList)

            val cardStrategy = cardEffectFactory.get(Pair(playerCard.cardType, monsterCard.cardType))
            val playerBattleStatus = BattleStatus(playerCard, playerStatus, player)
            val monsterBattleStatus = BattleStatus(monsterCard, monsterStatus, monster)
            cardStrategy.applyEffect(playerBattleStatus, monsterBattleStatus)
        }

        return battleState
    }


    private fun selectPlayerCard(idx: Int,cardList: List<Card>): Card {
        if( idx < 0 || idx >= cardList.size) {
            throw IllegalArgumentException("Invalid card index: $idx. Card list size: ${cardList.size}")
        }
        return cardList[idx]
    }

    private fun checkFinalizationPhase(targetOneBattleStatus : BattleStatus, targetTwoBattleStatus: BattleStatus): Boolean{
        return targetOneBattleStatus.status.get(TargetElementStatus.HP) <= 0.0 ||
                targetTwoBattleStatus.status.get(TargetElementStatus.HP) <= 0.0
    }

    private fun orderCardList(orderIdList: List<CardIdDTO>, orderCardList: List<Card>): List<Card>{
        return orderIdList.map { cardIdInfo ->
            orderCardList.first { it.id == cardIdInfo.id }
        }
    }

}