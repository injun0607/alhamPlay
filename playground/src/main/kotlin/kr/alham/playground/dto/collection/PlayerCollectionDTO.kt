package kr.alham.playground.dto.collection

import kr.alham.playground.domain.collection.PlayerEquipmentCollection
import kr.alham.playground.domain.collection.PlayerMaterialCollection
import kr.alham.playground.domain.item.Equipment
import kr.alham.playground.domain.item.Material


data class PlayerCollectionDTO(
    val equipmentCollectionList: List<EquipmentCollectionDTO> = emptyList(),
    val materialCollectionList: List<MaterialCollectionDTO> = emptyList(),
) {
    companion object {
        fun create(
            playerMaterialCollectionList: List<PlayerMaterialCollection>,
            playerEquipmentCollectionList: List<PlayerEquipmentCollection>,
            allMaterialList: List<Material>,
            allEquipmentList: List<Equipment>
        ): PlayerCollectionDTO {
            val hasMaterialMap = playerMaterialCollectionList.associateBy { it.material.id }
            val materialCollectionList = allMaterialList.map { material ->
                val materialId = requireNotNull(material.id) { "Material ID cannot be null" }

                if(hasMaterialMap.containsKey(material.id)){
                    MaterialCollectionDTO(
                        materialId = materialId,
                        name = material.name,
                        description = material.description,
                        itemType = material.type,
                        itemRarity = material.itemRarity,
                        itemImg = material.itemImg,
                        discoveredAt = hasMaterialMap[material.id]?.discoveredAt.toString(),
                        isCollected = true
                    )
                }else{
                    MaterialCollectionDTO(
                        name = material.name,
                        itemImg = material.itemImg,
                        isCollected = false
                    )
                }
            }

            val hasEquipmentMap = playerEquipmentCollectionList.associateBy { it.equipment.id }
            val equipmentCollectionList = allEquipmentList.map { equipment ->
                val equipmentId = requireNotNull(equipment.id) { "Equipment ID cannot be null" }

                if(hasEquipmentMap.containsKey(equipment.id)){
                    EquipmentCollectionDTO(
                        equipmentId = equipmentId,
                        name = equipment.name,
                        description = equipment.description,
                        itemType = equipment.type,
                        equipmentType = equipment.equipmentType,
                        itemRarity = equipment.itemRarity,
                        itemImg = equipment.itemImg,
                        discoveredAt = hasEquipmentMap[equipment.id]?.discoveredAt.toString(),
                        isCollected = true
                    )
                }else{
                    EquipmentCollectionDTO(
                        name = equipment.name,
                        itemImg = equipment.itemImg,
                        isCollected = false
                    )
                }
            }

            return PlayerCollectionDTO(
                equipmentCollectionList = equipmentCollectionList,
                materialCollectionList = materialCollectionList
            )
        }

    }
}