package com.santrucho.to_donotes.data

import java.lang.Exception

sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(val e : Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString() : String {
        return when(this){
            is Success<*> -> "Success[data=$data]"
            is Failure -> "Failure[e = $e]"
            Loading -> "Loading"
        }
    }
}

val Result<*>.succeeded
    get() = this is Result.Success && data != null

