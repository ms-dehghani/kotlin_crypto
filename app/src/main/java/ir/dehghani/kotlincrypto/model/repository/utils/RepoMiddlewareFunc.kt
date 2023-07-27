package ir.dehghani.kotlincrypto.model.repository.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class RepoMiddlewareFunc(
    var onCancel: (() -> Unit) = { emptyVoidFunction() },
    var showTryAgain: (() -> LiveData<Boolean>) = emptyLiveDataBooleanFunction(false),
    var hasInternetConnection: (() -> Boolean) = emptyBooleanFunction(true),
    var progressCallback: RepoProgressCallback = SimpleRepoProgressCallback()
)

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

class SimpleRepoProgressCallback : RepoProgressCallback {
    override fun showProgress() {
    }

    override fun hideProgress() {
    }
}