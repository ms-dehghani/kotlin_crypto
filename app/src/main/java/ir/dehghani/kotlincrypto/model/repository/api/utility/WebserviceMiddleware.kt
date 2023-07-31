package ir.dehghani.kotlincrypto.model.repository.api.utility

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ir.dehghani.kotlincrypto.model.repository.utils.RepoMiddlewareFunc
import ir.dehghani.kotlincrypto.model.repository.utils.RepoProgressCallback
import ir.dehghani.kotlincrypto.model.repository.utils.RepoResultCallback
import ir.dehghani.kotlincrypto.pojo.CurrencyItem
import org.json.JSONArray

object WebserviceMiddleware {

    fun <T> call(
        callingFunction: () -> WebserviceResponseModel<Any>,
        repoMiddlewareFunc: RepoMiddlewareFunc,
        resultCallback: RepoResultCallback<T>
    ) {

        repoMiddlewareFunc.progressCallback.showProgress()

        val hasConnection = repoMiddlewareFunc.hasInternetConnection.invoke()
        if (!hasConnection) {
            repoMiddlewareFunc.progressCallback.hideProgress()
            repoMiddlewareFunc.showTryAgain.invoke().observeForever { value ->
                run {
                    if (value)
                        call(callingFunction, repoMiddlewareFunc, resultCallback)
                }
            }
        }

        val response: WebserviceResponseModel<Any> = callingFunction.invoke()

        repoMiddlewareFunc.progressCallback.hideProgress()
        if (response.status)
            resultCallback.onResponse(convertor(response.data.toString()))
        else
            resultCallback.onError(Exception(""))
    }

    private fun <T> convertor(json: String): T {
        return Gson().fromJson(json, object : TypeToken<T?>() {}.type)
    }

}


