package ir.dehghani.kotlincrypto.model.items.currency.list.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ir.dehghani.kotlincrypto.base.arch.BasePresenter
import ir.dehghani.kotlincrypto.model.items.currency.list.model.CurrencyListModel
import ir.dehghani.kotlincrypto.model.repository.utils.RepoResultCallback
import ir.dehghani.kotlincrypto.model.items.currency.pojo.CurrencyItem
import ir.dehghani.kotlincrypto.utils.runOnBackground
import java.lang.Exception

class CurrencyListPresenter private constructor(model: CurrencyListModel) : BasePresenter<CurrencyListModel>(model) {

    companion object {

        @Volatile
        private var instance: CurrencyListPresenter? = null

        fun getInstance(model: CurrencyListModel) =
            instance ?: synchronized(this) {
                instance ?: CurrencyListPresenter(model).also { instance = it }
            }
    }


    fun getAllCurrency(): LiveData<List<CurrencyItem>> {

        val resultList = MutableLiveData<List<CurrencyItem>>()

        runOnBackground({
            val result = object : RepoResultCallback<List<CurrencyItem>> {
                override fun onError(ex: Exception) {
                }

                override fun onResponse(data: List<CurrencyItem>) {
                    resultList.postValue(data)
                }
            }
            getModel().getAllCurrency(result)
        }, this::getAllCurrency.name)

        return resultList
    }


}