package ir.dehghani.kotlincrypto.model.repository

import ir.dehghani.kotlincrypto.model.FullModelImpl
import ir.dehghani.kotlincrypto.pojo.CurrencyItem
import ir.dehghani.kotlincrypto.model.repository.utils.RepoMiddlewareFunc
import org.json.JSONObject

class Repository private constructor(private val serviceRepo: FullModelImpl?, private val localRepo: FullModelImpl?) : RepoImpl {

    companion object {

        @Volatile
        private var instance: Repository? = null

        fun getInstance(): Repository {
            return instance!!
        }

        fun init(serviceRepo: FullModelImpl?, localRepo: FullModelImpl?) {
            instance = Repository(serviceRepo,localRepo)
        }
    }


    override fun getAllCurrency(repoMiddlewareFunc: RepoMiddlewareFunc): List<CurrencyItem> {
        val result  = arrayListOf<CurrencyItem>()
        result.add(getCurrency("1"))
        result.add(getCurrency("2"))
        result.add(getCurrency("3"))
        return result
//        return serviceRepo.getAllCurrency()
    }

    override fun getCurrency(ID: String,repoMiddlewareFunc: RepoMiddlewareFunc): CurrencyItem {
//        return serviceRepo.getCurrency(ID)
        return CurrencyItem(ID.toInt(),"name" , "symbol" , JSONObject())
    }
}