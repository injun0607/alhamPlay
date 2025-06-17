package kr.alham.playground.common.utils.mapper

import kr.alham.playground.domain.area.FieldArea
import kr.alham.playground.dto.field.FieldAreaDTO

object FieldAreaMapper {

    fun fieldAreaToDTO(
        fieldArea: FieldArea
    ): FieldAreaDTO {
        return FieldAreaDTO(
            id = fieldArea.id,
            name = fieldArea.name,
            description = fieldArea.description,
            fieldType = fieldArea.fieldType
        )
    }

}