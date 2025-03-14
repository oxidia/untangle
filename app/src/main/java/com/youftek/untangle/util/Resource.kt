package com.youftek.untangle.util


sealed class Resource<T>(
    open val data: T? = null,
    val message: String? = null,
) {
    class IsLoading<T>: Resource<T>()
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) :
        Resource<T>(data, message)
}
