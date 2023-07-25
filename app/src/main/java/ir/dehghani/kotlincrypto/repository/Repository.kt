package ir.dehghani.kotlincrypto.repository

import ir.dehghani.kotlincrypto.base.arch.FullModelImpl
import ir.dehghani.kotlincrypto.base.arch.currency.list.state.CurrencyListState
import ir.dehghani.kotlincrypto.pojo.CurrencyItem
import ir.dehghani.kotlincrypto.repository.api.WebserviceRepo
import kotlinx.coroutines.delay
import org.json.JSONObject

class Repository private constructor(private val serviceRepo: FullModelImpl?,private val localRepo: FullModelImpl?) : RepoImpl {

    companion object {

        @Volatile
        private var instance: Repository? = null

        fun getInstance(): Repository {
            return instance!!
        }

        fun init(serviceRepo: FullModelImpl?,localRepo: FullModelImpl?) {
            instance = Repository(serviceRepo,localRepo)
        }
    }


    override fun getAllCurrency(): List<CurrencyItem> {
        val result  = arrayListOf<CurrencyItem>()
        result.add(getCurrency("1"))
        result.add(getCurrency("2"))
        result.add(getCurrency("3"))
        return result
//        return serviceRepo.getAllCurrency()
    }

    override fun getCurrency(ID: String): CurrencyItem {
//        return serviceRepo.getCurrency(ID)
        return CurrencyItem(ID.toInt(),"name" , "symbol" , JSONObject())
    }
}