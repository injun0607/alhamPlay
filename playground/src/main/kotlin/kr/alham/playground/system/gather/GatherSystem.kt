package kr.alham.playground.system.gather

import kr.alham.playground.domain.area.Area
import kr.alham.playground.domain.area.FieldArea
import kr.alham.playground.domain.area.Tile
import kr.alham.playground.loader.DropTableLoader
import org.springframework.stereotype.Component

@Component
class GatherSystem(
    val dropTableLoader: DropTableLoader,
) {
    fun fieldGather(area: FieldArea, x: Int, y: Int) {
        //area와 tile을 받아서 리턴
        //tileType을 확인한다음 -> 해당 타입에 맞는 가중치 ->
        //가중치는 area별로 다르게
        //로딩해야할건 -> 드랍율 , 드랍아이템
//        dropTableLoader.getAreaTable(area.type)


        val dropTable = requireNotNull(dropTableLoader.loadDropTable(area.fieldType)){
            "Drop table not found for area: ${area.fieldType.name}"
        }



    }


    private fun adjustAndNormalizeDropRates(){

    }


}
