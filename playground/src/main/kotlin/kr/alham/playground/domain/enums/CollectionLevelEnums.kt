package kr.alham.playground.domain.enums

enum class CollectionLevelEnums(
    val value: Int,
    val exp: Int,
) {
    LEVEL1(1,5),
    LEVEL2(2,10),
    LEVEL3(3,15),
    LEVEL4(4,20),
    LEVEL5(5,25),
    LEVEL6(6,30),
    LEVEL7(7,35),
    LEVEL8(8,40),
    LEVEL9(9,45),
    LEVEL10(10,50),
    LEVEL11(11,55),
    LEVEL12(12,60),
    LEVEL13(13,65),
    LEVEL14(14,70),
    LEVEL15(15,75),
    LEVEL16(16,80),
    LEVEL17(17,85),
    LEVEL18(18,90),
    LEVEL19(19,95),
    LEVEL20(20,100),
    MAX(Int.MAX_VALUE, 0);

    fun levelUp(): CollectionLevelEnums {
        return entries.firstOrNull { it.value == this.value + 1 } ?: MAX
    }

    fun checkLevelUpExp(inputExp: Int): Boolean{
        return this.exp <= inputExp
    }

}