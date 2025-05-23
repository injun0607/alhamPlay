package kr.alham.playground.system.gather

import kr.alham.playground.domain.area.Area
import kr.alham.playground.domain.area.FieldArea
import kr.alham.playground.domain.area.Tile
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GatherSystemTest{

    @Test
    fun GatherSystemPseudoCode(){

        /*
        1. x,y의 입력을 받아서
        2. x,y 좌표에 할당된 아이템을 확인한뒤
        3. 아이템을 뱉는다
         */

        val fieldArea = FieldArea(
            id = 1,
            name = "Test Field",
            description = "This is a test field area.",
            tiles = listOf(
                Tile(x = 0, y = 0),
                Tile(x = 1, y = 0),
                Tile(x = 0, y = 1),
                Tile(x = 1, y = 1)
            )
        )

        val tile = Tile(0, 0)


    }
}