package kr.alham.playground.repository.area

import kr.alham.playground.domain.area.Area
import kr.alham.playground.domain.area.FieldArea
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FieldAreaRepository: JpaRepository<FieldArea, Long> {

}