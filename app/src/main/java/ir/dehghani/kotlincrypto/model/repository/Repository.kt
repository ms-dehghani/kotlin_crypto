package ir.dehghani.kotlincrypto.model.repository

import ir.dehghani.kotlincrypto.model.FullModelImpl
import ir.dehghani.kotlincrypto.pojo.CurrencyItem
import ir.dehghani.kotlincrypto.model.repository.utils.RepoMiddlewareFunc
import ir.dehghani.kotlincrypto.model.repository.utils.RepoResultCallback
import org.json.JSONObject

class Repository private constructor(private val serviceRepo: FullModelImpl?, private val localRepo: FullModelImpl?) : RepoImpl {

    companion object {

        @Volatile
        private var instance: Repository? = null

        fun getInstance(): Repository {
            return instance!!
        }

        fun init(serviceRepo: FullModelImpl?, localRepo: FullModelImpl?) {
            instance = Repository(serviceRepo, localRepo)
        }
    }


    override fun getAllCurrency(repoMiddlewareFunc: RepoMiddlewareFunc, result: RepoResultCallback<List<CurrencyItem>>) {
        return serviceRepo!!.getAllCurrency(repoMiddlewareFunc, result)
    }

    override fun getCurrency(ID: String, repoMiddlewareFunc: RepoMiddlewareFunc, result: RepoResultCallback<CurrencyItem>) {
        return serviceRepo!!.getCurrency(ID, repoMiddlewareFunc, result)
    }
}