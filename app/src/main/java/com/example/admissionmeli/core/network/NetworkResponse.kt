package com.example.admissionmeli.core.network

sealed class NetworkResponse<T> {
    class Success<T>(val data: T) : NetworkResponse<T>()
    class Error<T : Any>(val code: Int, val message: String?) : NetworkResponse<T>()
    class Failure<T : Any>(val exception: Exception) : NetworkResponse<T>()
}


/**
 * This function folds the possible states of a response as a lambda.
 */
inline fun <R, T> NetworkResponse<T>.fold(
    onSuccess: (value: T) -> R,
    onError: (code: Int, message: String?) -> R,
    onException: (exception: Exception) -> R,
): R = when (this) {
    is NetworkResponse.Success -> onSuccess(data)
    is NetworkResponse.Error -> onError(code, message)
    is NetworkResponse.Failure -> onException(exception)
}