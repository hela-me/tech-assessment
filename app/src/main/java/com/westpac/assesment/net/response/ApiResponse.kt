package com.westpac.assesment.net.response

class ApiResponse<out T>(val status: ResponseStatus, val data: T? = null, val message: String? = null, val errorCode: Int? = null) {

    enum class ResponseStatus {
        LOADING, SUCCESS, FAILURE
    }

    companion object {
        fun <T> loading(): ApiResponse<T> {
            return ApiResponse(ResponseStatus.LOADING)
        }

        fun <T> success(data: T?): ApiResponse<T> {
            return ApiResponse(ResponseStatus.SUCCESS, data = data)
        }

        fun <T> failure(message: String, errorCode: Int? = null): ApiResponse<T> {
            return ApiResponse(ResponseStatus.FAILURE, message = message, errorCode = errorCode)
        }
    }
}