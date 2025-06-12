package kr.alham.playground.service.drop

import kr.alham.playground.system.drop.DropSystem
import org.springframework.stereotype.Component

@Component
class DropService(
    private val dropSystem: DropSystem
) {

    fun getDropItem(playerId : Long ,monsterId: Long): String {
        val item = dropSystem.dropItem(monsterId)
        //드롭아이템 저장 로직 추가 필요




        return item
    }
}