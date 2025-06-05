package kr.alham.playground.service.battle

import io.mockk.every
import io.mockk.mockk
import kr.alham.playground.domain.card.MonsterCard
import kr.alham.playground.domain.card.PlayerCard
import kr.alham.playground.domain.common.TargetElementStatus
import kr.alham.playground.domain.enums.BattlePhase
import kr.alham.playground.domain.enums.CardTarget
import kr.alham.playground.domain.enums.CardType
import kr.alham.playground.domain.monster.Monster
import kr.alham.playground.domain.player.Player
import kr.alham.playground.dto.battle.MonsterBattleDTO
import kr.alham.playground.dto.card.CardIdDTO
import kr.alham.playground.repository.monster.MonsterRepository
import kr.alham.playground.repository.player.PlayerRepository
import kr.alham.playground.service.card.CardService
import kr.alham.playground.service.monster.MonsterService
import kr.alham.playground.service.player.PlayerService
import kr.alham.playground.system.cardeffect.CardEffectFactory
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import kotlin.test.assertEquals


@SpringBootTest
@ActiveProfiles("test")
class BattleServiceTest {


    lateinit var player : Player
    lateinit var monster: Monster

    lateinit var playerSelfCardAtk1: PlayerCard
    lateinit var playerOpponentCardDamage10: PlayerCard
    lateinit var playerMutualCardSelf5op15: PlayerCard
    lateinit var playerBuffAtk3: PlayerCard


    lateinit var monsterCardSelfDef1: MonsterCard
    lateinit var monsterOpponentCardAttack10: MonsterCard
    lateinit var monsterOpponentCardAttack5: MonsterCard
    lateinit var monsterMutualCardSelf10toOp15: MonsterCard


    @Autowired
    lateinit var playerService: PlayerService
    @Autowired
    lateinit var monsterService: MonsterService
    @Autowired
    lateinit var cardEffectFactory: CardEffectFactory

    @Autowired
    lateinit var cardService: CardService

    @BeforeEach
    fun setUp() {
        // 테스트용 플레이어와 몬스터를 초기화
         player = Player(
            name = "테스트플레이어",
        )

        monster = Monster(
            name = "테스트 몬스터",
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
            effectSelfStat = TargetElementStatus.DEF
        )

        monsterOpponentCardAttack10 = MonsterCard(
            battlePhase = BattlePhase.ENGAGEMENT,
            name = "공격",
            description = "상대에게 10 데미지",
            cardTarget = CardTarget.OPPONENT,
            cardType = CardType.ATTACK,
            cost = 1,
            effectOpponentNum = 10.0,
            effectOpponentStat = TargetElementStatus.HP
        )

        monsterOpponentCardAttack5 = MonsterCard(
            battlePhase = BattlePhase.FINALIZATION,
            name = "강타",
            description = "상대에게 5 데미지",
            cardTarget = CardTarget.OPPONENT,
            cardType = CardType.ATTACK,
            cost = 1,
            effectOpponentNum = 5.0,
            effectOpponentStat = TargetElementStatus.HP
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
        cardService.savePlayerCard(playerOpponentCardDamage10)
        cardService.savePlayerCard(playerMutualCardSelf5op15)
        cardService.savePlayerCard(playerBuffAtk3)

        cardService.saveMonsterCard(monsterCardSelfDef1)
        cardService.saveMonsterCard(monsterOpponentCardAttack10)
        cardService.saveMonsterCard(monsterOpponentCardAttack5)
        cardService.saveMonsterCard(monsterMutualCardSelf10toOp15)

        //플레이어저장
        val savePlayer = playerService.savePlayer(player)
        //몬스터저장
        val saveMonster = monsterService.saveMonster(monster)



        //플레이어 카드정보저장
        playerService.savePlayerCardInfo(savePlayer, playerSelfCardAtk1)
        playerService.savePlayerCardInfo(savePlayer, playerOpponentCardDamage10)
        playerService.savePlayerCardInfo(savePlayer, playerMutualCardSelf5op15)
        playerService.savePlayerCardInfo(savePlayer, playerBuffAtk3)

        //몬스터 카드정보저장
        monsterService.saveMonsterCardInfo(saveMonster, monsterCardSelfDef1)
        monsterService.saveMonsterCardInfo(saveMonster, monsterOpponentCardAttack10)
        monsterService.saveMonsterCardInfo(saveMonster, monsterOpponentCardAttack5)
        monsterService.saveMonsterCardInfo(saveMonster, monsterMutualCardSelf10toOp15)


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

        //




    }


}