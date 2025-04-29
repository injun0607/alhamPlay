package kr.alham.playground.domain.common

interface TargetElement{
    val name: String
    val hp: Double
    val mp: Double
    val atk: Double
    val def: Double
    val str: Double
    val dex: Double
    val int: Double
    val lck: Double
    fun getStatus(): Map<TargetElementStatus, Double>
    fun getStatusValue(): Double
}
enum class TargetElementStatus(
    val defaultValue: Double,
) {
    HP(10.0),
    MP(10.0),
    ATK(10.0),
    DEF(10.0),
    STR(1.0),
    DEX(1.0),
    INT(1.0),
    LCK(1.0)
}