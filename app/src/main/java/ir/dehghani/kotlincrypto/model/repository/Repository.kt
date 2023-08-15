package ir.dehghani.kotlincrypto.model.repository

import ir.dehghani.kotlincrypto.model.FullModelImpl
import ir.dehghani.kotlincrypto.model.items.currency.pojo.CurrencyItem
import ir.dehghani.kotlincrypto.model.repository.utils.RepoResultCallback

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


    override fun getAllCurrency(result: RepoResultCallback<List<CurrencyItem>>) {
        return serviceRepo!!.getAllCurrency(result)
    }

    override fun getCurrency(ID: String, result: RepoResultCallback<CurrencyItem>) {
        return serviceRepo!!.getCurrency(ID, result)
    }
}