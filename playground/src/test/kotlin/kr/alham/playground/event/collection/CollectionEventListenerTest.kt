package kr.alham.playground.event.collection

import kr.alham.playground.domain.item.Item
import kr.alham.playground.domain.item.ItemType
import kr.alham.playground.domain.item.Material
import kr.alham.playground.dto.collection.ItemCollectionEvent
import kr.alham.playground.service.collection.CollectionService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationEventPublisher

@SpringBootTest
class CollectionEventListenerTest{


    @Autowired
    lateinit var collectionEventListener: CollectionEventListener

    @Autowired
    lateinit var applicationEventPublisher: ApplicationEventPublisher

    @Autowired
    lateinit var collectionService: CollectionService

    @Test
    fun `testCollectionNewEvent`(){

        val playerId = 1L
        val itemInfo = Material(
            id = 1L,
            name = "collection Material",
            description = "Test Material for Collection",
            type = ItemType.EQUIPMENT
        )

        applicationEventPublisher.publishEvent(
            ItemCollectionEvent(
                playerId = playerId,
                itemId = itemInfo.id!!,
                itemType = itemInfo.type
            )
        )

        val collect = collectionService.getPlayerCollection(1L)

        val cnt = collect.equipmentCollectionList.count {
            it.isCollected
        }

        val filteredItem = collect.equipmentCollectionList.first{
            it.isCollected
        }

        assertEquals(1, cnt)
        assertEquals(1L, filteredItem.equipmentId)


    }


}