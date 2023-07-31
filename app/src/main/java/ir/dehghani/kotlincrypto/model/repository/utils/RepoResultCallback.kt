package ir.dehghani.kotlincrypto.model.repository.utils

import java.lang.Exception

interface RepoResultCallback<T> {
    fun onResponse(data :T)
    fun onError(ex: Exception)
}