package kr.alham.playground.controller.collection

import kr.alham.playground.dto.collection.PlayerCollectionDTO
import kr.alham.playground.service.collection.CollectionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/collection")
class CollectionController(
    private val collectionService: CollectionService
) {

    @GetMapping
    fun getMaterialCollection(): PlayerCollectionDTO{
        val playerid = 1L // 예시로 플레이어 ID를 하드코딩

        return collectionService.getPlayerCollection(playerid)
    }



}