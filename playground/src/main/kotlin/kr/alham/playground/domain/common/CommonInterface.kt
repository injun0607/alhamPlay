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
    fun getStatus(): TargetElementStatusMap
    fun getStatusValue(targetElementStatus: TargetElementStatus): Double
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

class TargetElementStatusMap(
    private val statusMap: MutableMap<TargetElementStatus, Double> = mutableMapOf()
){
    init{
        TargetElementStatus.entries.forEach{
            statusMap.putIfAbsent(it,it.defaultValue)
        }
    }
    fun get(status: TargetElementStatus): Double {
        return statusMap[status] ?: 0.0
    }

    fun set(status: TargetElementStatus, value: Double) {
        statusMap[status] = value
    }

    fun add(status: TargetElementStatus, value: Double) {
        statusMap[status] = get(status) + value
    }

    fun clone(): TargetElementStatusMap {
        return TargetElementStatusMap(statusMap.toMutableMap())
    }

}

class TargetElementStatusEmptyMap(
    private val statusMap: MutableMap<TargetElementStatus, Double> = mutableMapOf()
){

    fun isEmpty(): Boolean {
        return statusMap.isEmpty()
    }
    fun set(status: TargetElementStatus, value: Double) {
        statusMap.putIfAbsent(status,value)
    }
    fun get(status: TargetElementStatus): Double {
        return statusMap[status] ?: 0.0
    }

}