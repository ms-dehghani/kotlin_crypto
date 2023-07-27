package ir.dehghani.kotlincrypto.model.repository.api.utility

import androidx.lifecycle.LiveData
import ir.dehghani.kotlincrypto.model.repository.utils.RepoMiddlewareFunc
import ir.dehghani.kotlincrypto.model.repository.utils.RepoProgressCallback

object WebserviceMiddleware {

    fun call(callingFunction: () -> WebserviceResponseModel<Any>, repoMiddlewareFunc: RepoMiddlewareFunc): Any? {
        repoMiddlewareFunc.progressCallback.showProgress()

        val hasConnection = repoMiddlewareFunc.hasInternetConnection.invoke() ?: false
        if (!hasConnection) {
            repoMiddlewareFunc.progressCallback.hideProgress()
            repoMiddlewareFunc.showTryAgain.invoke().observeForever { value ->
                run {
                    if (value)
                        call(callingFunction, repoMiddlewareFunc)
                }
            }
        }

        val response: WebserviceResponseModel<Any> = callingFunction.invoke()

        repoMiddlewareFunc.progressCallback.hideProgress()

        return if (response.status)
            response.data
        else
            null
    }

}
