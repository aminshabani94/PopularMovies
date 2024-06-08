package com.asn.popularmovies.data.model

sealed class CustomResult<out T : Any> {
    class Success<out T : Any>(val data: T) : CustomResult<T>()
    class Failure(val error: Throwable) : CustomResult<Nothing>()
    data object NullOrEmpty : CustomResult<Nothing>()
    data object NoInternet : CustomResult<Nothing>()
}