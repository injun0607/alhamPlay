package kr.alham.playground.common.utils

import org.springframework.http.HttpStatus

data class CommonResponse<T>(
    val status: HttpStatus,
    val message: String? = null,
    val data: T? = null
) {
    companion object {
        fun<T> of(
            status: HttpStatus,
            message: String? = null,
            data: T? = null
        ): CommonResponse<T> {
            return CommonResponse(
                status = status,
                message = message,
                data = data
            )
        }
    }
}