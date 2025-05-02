package kr.alham.playground.dto.battle

import kr.alham.playground.dto.card.CardIdDTO

class MonsterBattleDTO(
    var playerId: Long = 0,
    var monsterId: Long = 0,
    var preparationCard: List<CardIdDTO> = listOf(),
    var engagementCardList: List<CardIdDTO> = listOf(),
    var finalizationCard: List<CardIdDTO> = listOf(),
) {
}