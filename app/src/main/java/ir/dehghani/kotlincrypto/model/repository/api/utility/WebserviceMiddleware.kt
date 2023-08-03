package ir.dehghani.kotlincrypto.model.repository.api.utility

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ir.dehghani.kotlincrypto.model.repository.utils.RepoResultCallback

object WebserviceMiddleware {

    fun <T> call(
        callingFunction: () -> WebserviceResponseModel<Any>,
        resultCallback: RepoResultCallback<T>
    ) {
        val response: WebserviceResponseModel<Any> = callingFunction.invoke()
        if (response.status)
            resultCallback.onResponse(convertor(response.data.toString()))
        else
            resultCallback.onError(Exception(response.message))
    }

    private fun <T> convertor(json: String): T {
        return Gson().fromJson(json, object : TypeToken<T?>() {}.type)
    }

}


