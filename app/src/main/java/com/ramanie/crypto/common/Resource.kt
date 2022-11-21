package com.ramanie.crypto.common


// This is simply a wrapper class that we'll use to process the data and/or message we get as a response from a request
sealed class Resource<T>(val data: T? = null, val error: ErrorTypes? = null) {
    class Success<T>(data: T?): Resource<T>(data)
    class Error<T>(error: ErrorTypes, data: T? = null): Resource<T>(data, error)
    class Loading<T>(data: T? = null): Resource<T>(data)
}