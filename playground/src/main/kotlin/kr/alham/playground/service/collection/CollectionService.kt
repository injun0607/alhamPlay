package kr.alham.playground.service.collection

import kr.alham.playground.domain.collection.PlayerEquipmentCollection
import kr.alham.playground.domain.collection.PlayerMaterialCollection
import kr.alham.playground.domain.item.Equipment
import kr.alham.playground.domain.item.Item
import kr.alham.playground.domain.item.ItemType
import kr.alham.playground.domain.item.Material
import kr.alham.playground.dto.collection.PlayerCollectionDTO
import kr.alham.playground.repository.collection.PlayerEquipmentCollectionRepository
import kr.alham.playground.repository.collection.PlayerMaterialCollectionRepository
import kr.alham.playground.repository.item.EquipmentRepository
import kr.alham.playground.repository.item.MaterialRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CollectionService(
    private val playerMaterialCollectionRepository: PlayerMaterialCollectionRepository,
    private val playerEquipmentCollectionRepository: PlayerEquipmentCollectionRepository,
    private val materialRepository: MaterialRepository,
    private val equipmentRepository: EquipmentRepository
) {

    fun getPlayerCollection(playerId: Long): PlayerCollectionDTO {

        val playerMaterialCollectionList =  playerMaterialCollectionRepository.getAllMaterialCollectionByPlayerId(playerId)
        val playerEquipmentCollectionList = playerEquipmentCollectionRepository.getAllEquipmentCollectionByPlayerId(playerId)

        val allEquipmentList = equipmentRepository.findAll()
        val allMaterialList = materialRepository.findAll()

        return PlayerCollectionDTO.create(
            playerMaterialCollectionList = playerMaterialCollectionList,
            playerEquipmentCollectionList = playerEquipmentCollectionList,
            allMaterialList = allMaterialList,
            allEquipmentList = allEquipmentList
        )

    }

    fun isExistsCollection(playerId: Long,item: Item): Boolean{
        val itemId = requireNotNull(item.id){"아이템 ID가 null입니다."}
        return when(item.type){
            ItemType.EQUIPMENT -> {
                playerEquipmentCollectionRepository.existsPlayerEquipmentCollectionByPlayerIdAndEquipmentId(
                    playerId = playerId,
                    equipmentId = itemId
                )
            }

            ItemType.MATERIAL -> {
                playerMaterialCollectionRepository.existsPlayerMaterialCollectionByPlayerIdAndMaterialId(
                    playerId = playerId,
                    materialId = itemId
                )
            }
        }
    }

    @Transactional
    fun saveCollection(playerId: Long, item: Item) {
        val itemId = requireNotNull(item.id){"아이템 ID가 null입니다."}
        when(item.type){
            ItemType.EQUIPMENT -> {
                if(!isExistsCollection(playerId,item)){
                    playerEquipmentCollectionRepository.save(
                        PlayerEquipmentCollection.of(
                            playerId = playerId,
                            equipmentId = itemId
                        )
                    )
                }
            }

            ItemType.MATERIAL -> {
                if(!isExistsCollection(playerId,item)){
                    playerMaterialCollectionRepository.save(
                        PlayerMaterialCollection.of(
                            playerId = playerId,
                            materialId = itemId
                        )
                    )
                }
            }
        }
    }



}