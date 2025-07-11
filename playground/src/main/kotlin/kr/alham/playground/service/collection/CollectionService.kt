package kr.alham.playground.service.collection

import kr.alham.playground.domain.collection.PlayerEquipmentCollection
import kr.alham.playground.domain.collection.PlayerMaterialCollection
import kr.alham.playground.domain.enums.CollectionLevelEnums
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

        val playerMaterialCollectionList =
            playerMaterialCollectionRepository.getAllMaterialCollectionByPlayerId(playerId)
        val playerEquipmentCollectionList =
            playerEquipmentCollectionRepository.getAllEquipmentCollectionByPlayerId(playerId)

        val allEquipmentList = equipmentRepository.findAll()
        val allMaterialList = materialRepository.findAll()

        return PlayerCollectionDTO.create(
            playerMaterialCollectionList = playerMaterialCollectionList,
            playerEquipmentCollectionList = playerEquipmentCollectionList,
            allMaterialList = allMaterialList,
            allEquipmentList = allEquipmentList
        )

    }

    fun isExistsCollection(playerId: Long, item: Item): Boolean {
        val itemId = requireNotNull(item.id) { "아이템 ID가 null입니다." }
        return when (item.type) {
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
        val itemId = requireNotNull(item.id) { "아이템 ID가 null입니다." }
        when (item.type) {
            ItemType.EQUIPMENT -> {
                if (!isExistsCollection(playerId, item)) {
                    playerEquipmentCollectionRepository.save(
                        PlayerEquipmentCollection.of(
                            playerId = playerId,
                            equipmentId = itemId
                        )
                    )
                }
            }

            ItemType.MATERIAL -> {
                if (!isExistsCollection(playerId, item)) {
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

    @Transactional
    fun updateEquipmentCollection(playerId: Long, equipment: Equipment) {

        //collection 무조건 존재하긴할텐데 한번더 확인//
        val equipmentId = requireNotNull(equipment.id) { "장비 ID가 null입니다." }
        if (!isExistsCollection(playerId, equipment)) {
            //해당케이스는 사실존재하면 안되는 케이스 (방어코드)
            playerEquipmentCollectionRepository.save(
                PlayerEquipmentCollection.of(
                    playerId = playerId,
                    equipmentId = equipmentId,
                    quantity = 1
                )
            )
        }else{
            val playerCollectionInfo = getPlayerEquipmentCollectionByEquipmentId(equipmentId)
            val quantity = playerCollectionInfo.quantity + 1

            if(playerCollectionInfo.level.checkLevelUpExp(quantity)){
                playerCollectionInfo.level = playerCollectionInfo.level.levelUp()
                playerCollectionInfo.quantity = 0 // 레벨업 시에는 수량 초기화
            }else{
                playerCollectionInfo.quantity = quantity
            }
        }

    }

    fun getPlayerEquipmentCollectionByEquipmentId(equipmentId: Long): PlayerEquipmentCollection {
        return playerEquipmentCollectionRepository.getPlayerEquipmentCollectionByEquipmentId(equipmentId)
            ?: throw IllegalArgumentException("해당 장비의 플레이어 컬렉션 정보가 없습니다.")
    }


}