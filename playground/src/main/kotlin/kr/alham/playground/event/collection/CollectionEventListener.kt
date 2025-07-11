package kr.alham.playground.event.collection

import kr.alham.playground.domain.item.Item
import kr.alham.playground.domain.item.ItemType
import kr.alham.playground.dto.collection.ItemCollectionEvent
import kr.alham.playground.repository.item.EquipmentRepository
import kr.alham.playground.repository.item.MaterialRepository
import kr.alham.playground.service.collection.CollectionService
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionalEventListener

@Component
class CollectionEventListener(
    private val collectionService: CollectionService,
    private val materialRepository: MaterialRepository,
    private val equipmentRepository: EquipmentRepository
) {

    //아이템 저장은 보장되어야한다. 도감
    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun collectionNewEvent(itemCollectionEvent: ItemCollectionEvent){
        val item = when(itemCollectionEvent.itemType){
            ItemType.EQUIPMENT -> {
                equipmentRepository.findById(itemCollectionEvent.itemId).orElseThrow {
                    IllegalArgumentException("아이템 ID가 잘못되었습니다: ${itemCollectionEvent.itemId}")
                }
            }

            ItemType.MATERIAL -> {
                materialRepository.findById(itemCollectionEvent.itemId).orElseThrow {
                    IllegalArgumentException("아이템 ID가 잘못되었습니다: ${itemCollectionEvent.itemId}")
                }
            }
        }

        if(!collectionService.isExistsCollection(itemCollectionEvent.playerId,item)){
            println("아이템 도감에 등록되지 않은 아이템입니다: ${itemCollectionEvent.itemId}")
            collectionService.saveCollection(
                playerId = itemCollectionEvent.playerId,
                item = item
            )
        }
    }
}