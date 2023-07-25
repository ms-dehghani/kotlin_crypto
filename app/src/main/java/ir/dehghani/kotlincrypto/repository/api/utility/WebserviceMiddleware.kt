package ir.dehghani.kotlincrypto.repository.api.utility

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ir.dehghani.kotlincrypto.utils.RunOnBackground

class WebserviceMiddleware {

    private var onCancel: () -> Unit
    private var showTryAgain: () -> LiveData<Boolean>
    private var hasInternetConnection: () -> Boolean
    private var progressCallback: WebserviceProgressCallback

    constructor(
        onCancel: (() -> Unit)?, showTryAgain: (() -> LiveData<Boolean>)?, hasInternetConnection: (() -> Boolean)?,
        progressCallback: WebserviceProgressCallback?
    ) {
        this.onCancel = (onCancel ?: this::emptyVoidFunction)
        this.showTryAgain = (showTryAgain ?: (this::emptyLiveDataBooleanFunction)(false))
        this.hasInternetConnection = (hasInternetConnection ?: (this::emptyBooleanFunction)(true))
        this.progressCallback = (progressCallback ?: SimpleWebserviceProgressCallback())
    }

    fun Call(callingFunction: () -> WebserviceResponseModel<Any>): Any? {
        return _Call(callingFunction)
    }

    private fun _Call(callingFunction: () -> WebserviceResponseModel<Any>): Any? {
        progressCallback.showProgress()

        val hasConnection = hasInternetConnection.invoke()
        if (!hasConnection) {
            progressCallback.hideProgress()
            showTryAgain.invoke().observeForever { value ->
                run {
                    if (value)
                        _Call(callingFunction)
                }
            }
        }

        val response: WebserviceResponseModel<Any> = callingFunction.invoke()

        progressCallback.hideProgress()

        return if (response.status)
            response.data
        else
            null
    }


    private fun emptyVoidFunction() {
        return
    }

    private fun emptyBooleanFunction(result: Boolean): () -> Boolean {
        return { result }
    }

    private fun emptyLiveDataBooleanFunction(resultValue: Boolean): () -> MutableLiveData<Boolean> {
        val result = MutableLiveData<Boolean>()
        result.postValue(resultValue);
        return { result }
    }

    inner class SimpleWebserviceProgressCallback : WebserviceProgressCallback {
        override fun showProgress() {
        }

        override fun hideProgress() {
        }
    }

}
