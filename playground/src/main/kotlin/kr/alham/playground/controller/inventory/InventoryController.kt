package kr.alham.playground.controller.inventory

import kr.alham.playground.dto.inventory.PlayerInventoryDTO
import kr.alham.playground.service.inventory.InventoryService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/inventory")
@CrossOrigin(origins = ["*"])
class InventoryController(
    private val inventoryService: InventoryService
) {

    @GetMapping
    fun getInventory(): PlayerInventoryDTO {
        //TODO - 유저 인증 정보 받아서 진행
        val playerId = 1L
        return inventoryService.getPlayerInventory(playerId)
    }


}