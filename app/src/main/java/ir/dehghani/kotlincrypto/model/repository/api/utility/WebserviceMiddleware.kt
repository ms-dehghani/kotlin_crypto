package ir.dehghani.kotlincrypto.model.repository.api.utility

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ir.dehghani.kotlincrypto.model.items.currency.pojo.CurrencyItem
import ir.dehghani.kotlincrypto.model.repository.utils.RepoResultCallback

object WebserviceMiddleware {

    fun <T> call(
        callingFunction: () -> WebserviceResponseModel<Any>,
        resultCallback: RepoResultCallback<T>,
        type: TypeToken<T>
    ) {
        val response: WebserviceResponseModel<Any> = callingFunction.invoke()
        if (response.status)
            resultCallback.onResponse(convertor(response.data.toString(), type))
        else
            resultCallback.onError(Exception(response.message))
    }

    private fun <T> convertor(json: String, type: TypeToken<T>): T {
        return Gson().fromJson(json, type)
    }

}


