package kr.alham.playground.loader

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import kr.alham.playground.domain.area.FieldType
import kr.alham.playground.domain.item.ItemRarity
import org.springframework.stereotype.Component

import kr.alham.playground.common.ITEM_JSON_PATH


@Component
class DropTableLoader {

    private val mapper = jacksonObjectMapper()

    private val itemPath = ITEM_JSON_PATH

    fun loadDropTable(filedType:FieldType): Map<ItemRarity, List<String>> {
        val fileName = itemPath + "${filedType.name}_DROP_TABLE.json"
        val inputStream = javaClass.getResourceAsStream(fileName)
            ?: throw IllegalArgumentException("Drop table not found for area: ${filedType.name}")

        val stringMap: Map<String, List<String>> = mapper.readValue(inputStream)
        return stringMap.mapKeys { ItemRarity.valueOf(it.key) }
    }

    fun loadDropRate(): Map<ItemRarity, Double> {
        val fileName = itemPath + "DROP_RATE.json"
        val inputStream = javaClass.getResourceAsStream(fileName)
            ?: throw IllegalArgumentException("Drop rate not found")

        val stringMap: Map<String,Double> = mapper.readValue(inputStream)

        return stringMap.mapKeys { ItemRarity.valueOf(it.key) }

    }
}