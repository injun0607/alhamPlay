package kr.alham.playground.dto.battle

import kr.alham.playground.dto.card.CardIdDTO

class MonsterBattleDTO(
    var userId: Long = 0,
    var monsterId: Long = 0,
    var preparationCard: CardIdDTO = CardIdDTO(),
    var engagementCardList: List<CardIdDTO> = listOf(),
    var finalizationCard: CardIdDTO = CardIdDTO(),
) {
}