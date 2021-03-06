package com.codeartist.starwar.common.utils

data class DataState<T> (
    var status: Status?,
    var message: String? = null,
    var data: T? = null

) {
    companion object {


        fun <T> success(data: T?): DataState<T> {
            return DataState(Status.SUCCESS, null, data)
        }

        fun <T> error(msg: String?): DataState<T> {
            return DataState(Status.ERROR, msg, null)
        }

        fun <T> loading(): DataState<T> {
            return DataState(Status.LOADING, null, null)
        }

    }
}


enum class Status {
    SUCCESS, LOADING, ERROR
}