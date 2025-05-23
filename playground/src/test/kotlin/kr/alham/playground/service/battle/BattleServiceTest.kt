package kr.alham.playground.service.battle

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles


@SpringBootTest
@ActiveProfiles("test")
class BattleServiceTest {
//
//
//    private lateinit var monsterCardRepository: MonsterCardRepository
//
//
//    private lateinit var playerRepository: PlayerRepository
//
//
//    private lateinit var playerCardRepository: PlayerCardRepository
//
//
//    private lateinit var monsterRepository: MonsterRepository
//
//
//    private lateinit var attackCardEffect: AttackCardEffect
//
//    private lateinit var healCardEffect: HealCardEffect
//
//    private lateinit var defenceCardEffect: DefenceCardEffect
//
//    private lateinit var evasionCardEffect: EvasionCardEffect
//
//    private lateinit var buffCardEffect: BuffCardEffect
//
//    private lateinit var cardEffectFactory: CardEffectFactory
//
//    private lateinit var battleService: BattleService
//
//    lateinit var playerCardConcentrate: PlayerCard;
//    lateinit var playerCardBash: PlayerCard;
//    lateinit var playerCardCut: PlayerCard;
//    lateinit var playerCardShield: PlayerCard;
//    lateinit var playerCardEvasion: PlayerCard;
//    lateinit var monsterCardConcentrate: MonsterCard;
//    lateinit var monsterCardBash: MonsterCard;
//    lateinit var monsterCardCut: MonsterCard;
//    lateinit var monsterCardShield: MonsterCard;
//    lateinit var monsterCardEvasion: MonsterCard;
//    lateinit var player: Player;
//    lateinit var monster: Monster;
//
//    @BeforeEach
//    fun setUp() {
//
//        attackCardEffect = AttackCardEffect()
//        buffCardEffect = BuffCardEffect()
//        evasionCardEffect = EvasionCardEffect()
//        defenceCardEffect = DefenceCardEffect()
//        healCardEffect = HealCardEffect()
//
//        playerRepository = mockk()
//        monsterRepository = mockk()
//        playerCardRepository = mockk()
//        monsterCardRepository = mockk()
//
////
////        every { buffCardEffect.supportedType() }.returns(CardType.BUFF)
////        every { evasionCardEffect.supportedType() }.returns(CardType.EVASION)
////        every { defenceCardEffect.supportedType() }.returns(CardType.DEFENCE)
////        every { healCardEffect.supportedType() }.returns(CardType.HEAL)
//
//
//        cardEffectFactory = CardEffectFactory(
//            strategies = listOf(
//                attackCardEffect,
//                buffCardEffect,
//                evasionCardEffect,
//                defenceCardEffect,
//                healCardEffect
//            )
//        )
//
//
//        playerCardConcentrate = PlayerCard(
//            id=1L,
//            name = "집중",
//            battlePhase = BattlePhase.PREPARATION,
//            description = "민첩 + 1",
//            cardTarget = CardTarget.SELF,
//            cardType = CardType.BUFF,
//            effectSelfNum = 1.0,
//            effectSelfStat = TargetElementStatus.DEX,
//        )
//
//        playerCardBash = PlayerCard(
//            id=2L,
//            name = "강타",
//            battlePhase = BattlePhase.ENGAGEMENT,
//            description = "상대방에게 5의 피해를 줍니다.",
//            cost = 2,
//            cardTarget = CardTarget.OPPONENT,
//            effectOpponentNum = 5.0,
//            effectOpponentStat = TargetElementStatus.HP,
//        )
//
//        playerCardCut = PlayerCard(
//            id=3L,
//            name = "베기",
//            battlePhase = BattlePhase.ENGAGEMENT,
//            description = "상대방에게 6의 피해를 줍니다.",
//            cost = 2,
//            cardTarget = CardTarget.OPPONENT,
//            effectOpponentNum = 6.0,
//            effectOpponentStat = TargetElementStatus.HP,
//        )
//
//        playerCardShield = PlayerCard(
//            id=4L,
//            name = "방패막기",
//            battlePhase = BattlePhase.ENGAGEMENT,
//            description = "10데미지를 막습니다.",
//            cost = 2,
//            cardTarget = CardTarget.OPPONENT,
//            cardType = CardType.DEFENCE,
//            effectSelfNum = 10.0,
//            effectSelfStat = TargetElementStatus.HP,
//        )
//
//        playerCardEvasion = PlayerCard(
//            id=5L,
//            name = "회피",
//            battlePhase = BattlePhase.FINALIZATION,
//            description = "상대방의 마지막 공격을 회피합니다.",
//            cardTarget = CardTarget.SELF,
//            cardType = CardType.EVASION,
//            effectSelfNum = 10.0,
//            effectSelfStat = TargetElementStatus.HP,
//        )
//
//        monsterCardCut = MonsterCard(
//            name = "베기",
//            battlePhase = BattlePhase.ENGAGEMENT,
//            description = "상대방에게 6의 피해를 줍니다.",
//            cost = 2,
//            cardTarget = CardTarget.OPPONENT,
//            effectOpponentNum = 6.0,
//            effectOpponentStat = TargetElementStatus.HP,
//            battleOrder = 1
//        )
//
//        monsterCardBash = MonsterCard(
//            name = "강타",
//            battlePhase = BattlePhase.ENGAGEMENT,
//            description = "상대방에게 5의 피해를 줍니다.",
//            cost = 2,
//            cardTarget = CardTarget.OPPONENT,
//            effectOpponentNum = 5.0,
//            effectOpponentStat = TargetElementStatus.HP,
//            battleOrder = 2
//        )
//
//        monsterCardConcentrate = MonsterCard(
//            name = "집중",
//            battlePhase = BattlePhase.PREPARATION,
//            description = "민첩 + 1",
//            cardTarget = CardTarget.SELF,
//            cardType = CardType.BUFF,
//            effectSelfNum = 1.0,
//            effectSelfStat = TargetElementStatus.DEX,
//        )
//
//        monsterCardShield = MonsterCard(
//            name = "방패막기",
//            battlePhase = BattlePhase.ENGAGEMENT,
//            description = "10데미지를 막습니다.",
//            cost = 2,
//            cardTarget = CardTarget.OPPONENT,
//            cardType = CardType.DEFENCE,
//            effectSelfNum = 10.0,
//            effectSelfStat = TargetElementStatus.HP,
//            battleOrder = 3
//        )
//
//        monsterCardEvasion = MonsterCard(
//            name = "회피",
//            battlePhase = BattlePhase.FINALIZATION,
//            description = "상대방의 마지막 공격을 회피합니다.",
//            cardTarget = CardTarget.SELF,
//            cardType = CardType.EVASION,
//            effectSelfNum = 10.0,
//            effectSelfStat = TargetElementStatus.HP,
//        )
//
//        player = Player(id = 1L , name = "인준")
//        monster = Monster(id = 1L ,name = "슬라임")
//
//        battleService = BattleService(
//            cardEffectFactory = cardEffectFactory,
//            playerRepository = playerRepository,
//            monsterRepository = monsterRepository,
//            playerCardRepository = playerCardRepository,
//            monsterCardRepository = monsterCardRepository
//        )
//
//        every {
//            playerRepository.findById(any())
//        }.returns(Optional.of(player))
//
//
//        every {
//            monsterRepository.findById(any())
//        }.returns(Optional.of(monster))
//
//        every{
//            playerCardRepository.validPlayerCardInfo(any(), any(), any())
//        }.returns(true)
//
//        every {
//            monsterCardRepository.findCardListByMonsterIdAndCardIdIn(any())
//        } returns (listOf(
//            MonsterCardInfo(
//                card = monsterCardCut
//            ),
//            MonsterCardInfo(
//                card = monsterCardBash
//            ),
//            MonsterCardInfo(
//                card = monsterCardConcentrate
//            ),
//            MonsterCardInfo(
//                card = monsterCardShield
//            ),
//            MonsterCardInfo(
//                card = monsterCardEvasion
//            )
//        ))
//
//
//        every {
//            playerCardRepository.findCardListByPlayerIdAndCardIdIn(any(), listOf(1L))
//        } returns (
//                listOf(
//                    PlayerCardInfo(
//                        card = playerCardConcentrate
//                    )))
//
//
//        every {
//            playerCardRepository.findCardListByPlayerIdAndCardIdIn(any(), listOf(2L,3L,4L))
//        } returns (
//                listOf(
//                    PlayerCardInfo(
//                        card = playerCardBash
//                    ),
//                    PlayerCardInfo(
//                        card = playerCardCut
//                    ),
//                    PlayerCardInfo(
//                        card = playerCardShield
//                    )
//                ))
//
//        every {
//            playerCardRepository.findCardListByPlayerIdAndCardIdIn(any(), listOf(5L))
//        } returns (
//                listOf(
//                    PlayerCardInfo(
//                        card = playerCardEvasion
//                    )
//                ))
//
//
//    }
//
//    @Test
//    fun `mockito bean dependency test`() {
//        val attackEffect = cardEffectFactory.get(CardType.ATTACK)
//        val buffEffect = cardEffectFactory.get(CardType.BUFF)
//        val evasionEffect = cardEffectFactory.get(CardType.EVASION)
//        val defenceEffect = cardEffectFactory.get(CardType.DEFENCE)
//        val healEffect = cardEffectFactory.get(CardType.HEAL)
//
//        assert(attackEffect is AttackCardEffect)
//        assert(buffEffect is BuffCardEffect)
//        assert(evasionEffect is EvasionCardEffect)
//        assert(defenceEffect is DefenceCardEffect)
//        assert(healEffect is HealCardEffect)
//
//    }
//
//
//    @Test
//    fun `initBattleState`(){
//        val monsterBattleDTO = MonsterBattleDTO(
//            playerId = 1L,
//            monsterId = 1L,
//            preparationCard = listOf(CardIdDTO(1L)),
//            engagementCardList = listOf(CardIdDTO(2L), CardIdDTO(3L), CardIdDTO(4L)),
//            finalizationCard = listOf(CardIdDTO(5L))
//        )
//
//        val initMonsterBattleState:BattleState = battleService.initMonsterBattleState(monsterBattleDTO)
//
//
//        //preparationCardList 확인
//        assert(initMonsterBattleState.preparationMonsterBattleStatus.playerCardList.size == 1)
//        assert(initMonsterBattleState.preparationMonsterBattleStatus.monsterCardList.size == 1)
//        assert(initMonsterBattleState.preparationMonsterBattleStatus.monsterCardList[0].name == "집중")
//        assert(initMonsterBattleState.preparationMonsterBattleStatus.playerCardList[0].name == "집중")
//
//
//        //engagementCardList 확인
//        assert(initMonsterBattleState.engagementMonsterBattleStatus.playerCardList.size == 3)
//        assert(initMonsterBattleState.engagementMonsterBattleStatus.monsterCardList.size == 3)
//        //monsterCardList 확인
//        assert(initMonsterBattleState.engagementMonsterBattleStatus.monsterCardList[0].name == "베기")
//        assert(initMonsterBattleState.engagementMonsterBattleStatus.monsterCardList[1].name == "강타")
//        assert(initMonsterBattleState.engagementMonsterBattleStatus.monsterCardList[2].name == "방패막기")
//        //playerCardList 확인
//        assert(initMonsterBattleState.engagementMonsterBattleStatus.playerCardList[0].name == "강타")
//        assert(initMonsterBattleState.engagementMonsterBattleStatus.playerCardList[1].name == "베기")
//        assert(initMonsterBattleState.engagementMonsterBattleStatus.playerCardList[2].name == "방패막기")
//
//        //finalizationCardList 확인
//        assert(initMonsterBattleState.finalizationMonsterBattleStatus.playerCardList.size == 1)
//        assert(initMonsterBattleState.finalizationMonsterBattleStatus.monsterCardList.size == 1)
//        assert(initMonsterBattleState.finalizationMonsterBattleStatus.monsterCardList[0].name == "회피")
//        assert(initMonsterBattleState.finalizationMonsterBattleStatus.playerCardList[0].name == "회피")
//
//    }
//
//
//    @Test
//    fun testMonsterBattle() {
//
//    }
//
//
//    @Test
//    fun `testMonsterBattlePreparation - 몬스터,유저 dex+1`() {
//
//        val monsterBattleDTO = MonsterBattleDTO(
//            playerId = 1L,
//            monsterId = 1L,
//            preparationCard = listOf(CardIdDTO(1L)),
//            engagementCardList = listOf(CardIdDTO(2L), CardIdDTO(3L), CardIdDTO(4L)),
//            finalizationCard = listOf(CardIdDTO(5L))
//        )
//
//        val initMonsterBattleState:BattleState = battleService.initMonsterBattleState(monsterBattleDTO)
//
//        val preparedBattleState = battleService.monsterBattlePreparation(initMonsterBattleState)
//
//        assertEquals(2.0,preparedBattleState.preparationMonsterBattleStatus.playerStatus.get(TargetElementStatus.DEX),"플레이어 dex+1")
//        assertEquals(2.0,preparedBattleState.preparationMonsterBattleStatus.monsterStatus.get(TargetElementStatus.DEX),"몬스터 dex+1")
//
//    }
//
//    @Test
//    fun `testMonsterBattleEngagement`(){
//
//    }
}