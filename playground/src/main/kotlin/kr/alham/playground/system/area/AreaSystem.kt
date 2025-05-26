package kr.alham.playground.system.area

import kr.alham.playground.domain.area.*
import org.springframework.stereotype.Component

@Component
class AreaSystem {

    fun generateTile(
        area: Area,
        commonCnt: Int,
        unCommonCnt: Int,
        rareCnt: Int,
        epicCnt: Int,
        uniqueCnt: Int,
        legendaryCnt: Int
    ): Area {


        if(commonCnt + unCommonCnt + rareCnt + epicCnt + uniqueCnt + legendaryCnt != 25){
            throw IllegalArgumentException("타일의 숫자는 모두 25개여야 합니다.")
        }

        val tiles = area.tiles
        val numbers = (0..24).toList().shuffled()
        //타일 생성 로직

        var index = 0

        for(i in 0 until commonCnt) {
            val number = numbers[i]
            tiles[number].type = TileType.COMMON
            index++
        }

        for(i in 0 until unCommonCnt) {
            val number = numbers[index]
            tiles[number].type = TileType.UNCOMMON
            index++
        }

        for(i in 0 until rareCnt) {
            val number = numbers[index]
            tiles[number].type = TileType.RARE
            index++
        }

        for(i in 0 until epicCnt) {
            val number = numbers[index]
            tiles[number].type = TileType.EPIC
            index++
        }

        for(i in 0 until uniqueCnt) {
            val number = numbers[index]
            tiles[number].type = TileType.UNIQUE
            index++
        }

        for(i in 0 until legendaryCnt) {
            val number = numbers[index]
            tiles[number].type = TileType.LEGENDARY
            index++
        }

        return area
    }

    fun initFieldArea(fieldType: FieldType): FieldArea{
        return FieldArea(
            id = null,
            name = "Field Area",
            description = "This is a field area.",
            type = AreaType.FIELD,
            tiles = mutableListOf(
                Tile(x = 0, y = 0),
                Tile(x = 1, y = 0),
                Tile(x = 2, y = 0),
                Tile(x = 3, y = 0),
                Tile(x = 4, y = 0),
                Tile(x = 0, y = 1),
                Tile(x = 1, y = 1),
                Tile(x = 2, y = 1),
                Tile(x = 3, y = 1),
                Tile(x = 4, y = 1),
                Tile(x = 0, y = 2),
                Tile(x = 1, y = 2),
                Tile(x = 2, y = 2),
                Tile(x = 3, y = 2),
                Tile(x = 4, y = 2),
                Tile(x = 0, y = 3),
                Tile(x = 1, y = 3),
                Tile(x = 2, y = 3),
                Tile(x = 3, y = 3),
                Tile(x = 4, y = 3),
                Tile(x = 0, y = 4),
                Tile(x = 1, y = 4),
                Tile(x = 2, y = 4),
                Tile(x = 3, y = 4),
                Tile(x = 4, y = 4)
            ),
            fieldType = fieldType
        )
    }
}