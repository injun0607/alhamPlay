package kr.alham.playground.dto.field

import kr.alham.playground.domain.area.FieldType

data class FieldAreaDTO(
    val id: Long? = null,
    val name: String = "",
    val description: String = "",
    val fieldType: FieldType,
) {
}