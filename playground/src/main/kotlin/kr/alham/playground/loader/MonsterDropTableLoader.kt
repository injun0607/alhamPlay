package kr.alham.playground.loader

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import kr.alham.playground.common.ITEM_JSON_PATH
import kr.alham.playground.common.ITEM_MONSTER_JSON_PATH
import kr.alham.playground.common.RECIPE_JSON_PATH
import kr.alham.playground.domain.area.FieldType
import kr.alham.playground.domain.item.EquipmentRecipe
import kr.alham.playground.domain.item.ItemRarity
import kr.alham.playground.dto.drop.MonsterDropTableDTO
import org.springframework.stereotype.Component
import java.util.Objects
//TODO - 드롭아이템은 디비 ID값을 반환해야함 - 현재 이름을 반환함
@Component
class MonsterDropTableLoader {
    private val mapper = jacksonObjectMapper()
    private val recipePath = ITEM_MONSTER_JSON_PATH

    fun loadMonsterDropItemTable(monsterId: Long): MonsterDropTableDTO {
        val fileName = recipePath + "MONSTER_DROP_TABLE.json"  // 파일 이름 대소문자 정확히!
        val inputStream = javaClass.getResourceAsStream(fileName)
            ?: throw IllegalArgumentException("Monster drop table not found for file: $fileName")
        val monsterDropTableList: List<MonsterDropTableDTO> = mapper.readValue(inputStream)

        val monsterDropMap = monsterDropTableList.associateBy { it.monsterId }

        return monsterDropMap[monsterId]
            ?: throw IllegalArgumentException("Monster drop table not found for monsterId: $monsterId")
    }

    fun loadDropRate(): Map<ItemRarity, Double> {
        val fileName = ITEM_MONSTER_JSON_PATH + "MONSTER_DROP_RATE.json"
        val inputStream = javaClass.getResourceAsStream(fileName)
            ?: throw IllegalArgumentException("Drop rate not found for file: $fileName")

        val stringMap: Map<String, Double> = mapper.readValue(inputStream)
        return stringMap.mapKeys { ItemRarity.valueOf(it.key) }
    }
}