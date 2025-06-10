package kr.alham.playground.service.monster

import kr.alham.playground.domain.card.MonsterCard
import kr.alham.playground.domain.common.TargetElementStatus
import kr.alham.playground.domain.enums.BattlePhase
import kr.alham.playground.domain.enums.CardTarget
import kr.alham.playground.domain.enums.CardType
import kr.alham.playground.domain.monster.Monster
import kr.alham.playground.repository.monster.MonsterCardInfoRepository
import kr.alham.playground.repository.monster.MonsterRepository
import kr.alham.playground.service.card.CardService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Test

@SpringBootTest
class MonsterServiceTest{

    lateinit var monsterService: MonsterService

    @Autowired
    lateinit var monsterRepository: MonsterRepository

    @Autowired
    lateinit var monsterCardInfoRepository: MonsterCardInfoRepository

    @Autowired
    lateinit var cardService: CardService

    @BeforeEach
    fun setUp() {
        // 테스트용 몬스터 서비스 초기화
        monsterService = MonsterService(
            monsterRepository = monsterRepository,
            monsterCardInfoRepository = monsterCardInfoRepository
        )
    }


    @Test
    fun monsterSaveAndFindTest(){
        val monster = Monster(
            name = "테스트 몬스터",
            atk = 5.0,
            hp = 10.0
        )


        monsterService.saveMonster(monster)
        val foundMonster = monsterService.findMonsterById(1L)

        assertEquals(foundMonster.id, 1L)
        assertEquals(foundMonster.name, "테스트 몬스터")
        assertEquals(foundMonster.atk, 5.0)
        assertEquals(foundMonster.hp, 10.0)
    }


    @Test
    fun monsterInfoSaveAndFindTest(){
        val monster = Monster(
            name = "테스트 몬스터",
            atk = 5.0,
            hp = 10.0
        )

        val monsterCardSelf = MonsterCard(
            battlePhase = BattlePhase.PREPARATION,
            name = "테스트 몬스터 카드",
            description = "테스트 몬스터 카드 설명",
            cardTarget = CardTarget.SELF,
            cardType = CardType.ATTACK,
            cost = 2,
            effectSelfNum = 3.0,
            effectSelfStat = TargetElementStatus.ATK
        )

        val monsterCardOpponent = MonsterCard(
            battlePhase = BattlePhase.ENGAGEMENT,
            name = "테스트 몬스터 카드 상대",
            description = "테스트 몬스터 카드 상대 설명",
            cardTarget = CardTarget.OPPONENT,
            cardType = CardType.ATTACK,
            cost = 2,
            effectOpponentNum = 4.0,
            effectOpponentStat = TargetElementStatus.HP
        )

        val monsterCardMutual = MonsterCard(
            battlePhase = BattlePhase.FINALIZATION,
            name = "테스트 몬스터 카드 상호",
            description = "테스트 몬스터 카드 상호 설명",
            cardTarget = CardTarget.MUTUAL,
            cardType = CardType.ATTACK,
            cost = 2,
            effectSelfNum = 1.0,
            effectSelfStat = TargetElementStatus.HP,
            effectOpponentNum = 2.0,
            effectOpponentStat = TargetElementStatus.HP
        )

        monsterService.saveMonster(monster)

        val foundMonster = monsterService.findMonsterById(1L)
        assertEquals(foundMonster.name, "테스트 몬스터")

        val monsterId = requireNotNull(foundMonster.id){
            "Monster ID should not be null after saving."
        }

        cardService.saveMonsterCard(monsterCardSelf)
        cardService.saveMonsterCard(monsterCardOpponent)
        cardService.saveMonsterCard(monsterCardMutual)

        val emptyMonsterInfo = monsterService.findAllMonsterCardInfoByMonsterId(monsterId)
        assertEquals(0,emptyMonsterInfo.size)

        monsterService.saveMonsterCardInfo(foundMonster, monsterCardSelf)
        monsterService.saveMonsterCardInfo(foundMonster, monsterCardOpponent)
        monsterService.saveMonsterCardInfo(foundMonster, monsterCardMutual)

        val monsterCardInfoList = monsterService.findAllMonsterCardInfoByMonsterId(monsterId)
        assertEquals(3,monsterCardInfoList.size)

    }








}