package com.example.admissionmeli.core.network

import com.example.admissionmeli.R
import com.example.admissionmeli.core.network.HttpCodes.getHttpErrorMessage
import retrofit2.HttpException
import java.io.IOException
import java.net.UnknownHostException

fun Throwable.getErrorMessage(): Int = when (this) {
    is UnknownHostException -> R.string.unknown_host_exception
    is IOException -> R.string.io_exception
    is HttpException -> getHttpErrorMessage(code())
    else -> R.string.unknown_error
}