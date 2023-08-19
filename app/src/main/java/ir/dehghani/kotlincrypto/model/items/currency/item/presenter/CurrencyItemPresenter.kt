package ir.dehghani.kotlincrypto.model.items.currency.item.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ir.dehghani.kotlincrypto.base.arch.BasePresenter
import ir.dehghani.kotlincrypto.model.items.currency.item.model.CurrencyItemModel
import ir.dehghani.kotlincrypto.model.repository.utils.RepoResultCallback
import ir.dehghani.kotlincrypto.model.items.currency.pojo.CurrencyItem
import ir.dehghani.kotlincrypto.utils.runOnBackground
import java.lang.Exception

class CurrencyItemPresenter private constructor(model: CurrencyItemModel) :
    BasePresenter<CurrencyItemModel>(model) {

    companion object {

        @Volatile
        private var instance: CurrencyItemPresenter? = null

        fun getInstance(model: CurrencyItemModel) =
            instance ?: synchronized(this) {
                instance ?: CurrencyItemPresenter(model).also { instance = it }
            }
    }


    fun getCurrency(ID: String): LiveData<CurrencyItem> {

        val resultItem = MutableLiveData<CurrencyItem>()

        runOnBackground({
            val result = object : RepoResultCallback<CurrencyItem> {
                override fun onError(ex: Exception) {
                }

                override fun onResponse(detail: CurrencyItem) {
                    resultItem.postValue(detail)
                }
            }
            getModel().getCurrency(ID, result)
        }, this::getCurrency.name)

        return resultItem
    }


}