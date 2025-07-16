package kr.alham.playground.repository.area

import kr.alham.playground.domain.area.FieldArea
import kr.alham.playground.domain.area.FieldType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FieldAreaRepository: JpaRepository<FieldArea, Long> {

    fun findByFieldType(fieldType: FieldType): FieldArea?

}