package ir.dehghani.kotlincrypto.model.items.currency.list.model

import ir.dehghani.kotlincrypto.model.items.currency.getFakeCurrencyList
import ir.dehghani.kotlincrypto.model.items.currency.pojo.CurrencyItem
import ir.dehghani.kotlincrypto.model.repository.utils.RepoResultCallback
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class CurrencyListModelTest {

    val itemModel = mock(CurrencyListModel::class.java)
    val responseCallback = mock(RepoResultCallback::class.java) as RepoResultCallback<List<CurrencyItem>>

    @Test
    fun getCurrencyList() {
        val resultItem = getFakeCurrencyList()

        `when`(itemModel.getAllCurrency(org.mockito.kotlin.any())).then {
            (it.arguments[0] as RepoResultCallback<List<CurrencyItem>>).onResponse(resultItem)
        }

        `when`(responseCallback.onResponse(org.mockito.kotlin.any())).then {
            assertEquals((it.arguments[0] as List<CurrencyItem>).count(), resultItem.count())
        }

        itemModel.getAllCurrency(result = responseCallback)
        verify(itemModel, times(1)).getAllCurrency(result = responseCallback)
    }

    @Test
    fun checkOnSuccess() {
        val resultItem = getFakeCurrencyList()

        `when`(itemModel.getAllCurrency(org.mockito.kotlin.any())).then {
            (it.arguments[0] as RepoResultCallback<List<CurrencyItem>>).onResponse(resultItem)
        }

        itemModel.getAllCurrency(result = responseCallback)
        verify(itemModel, times(1)).getAllCurrency(result = responseCallback)
        verify(responseCallback, times(1)).onResponse(resultItem)
    }

    @Test
    fun checkSameResponse() {
        val resultItem = getFakeCurrencyList()

        lateinit var item: List<CurrencyItem>

        `when`(responseCallback.onResponse(org.mockito.kotlin.any())).then {
            item = it.arguments[0] as List<CurrencyItem>
            null
        }

        responseCallback.onResponse(resultItem)

        for (i in 0 until item.count())
            assertEquals(item[i], resultItem[i])
    }


    @Test
    fun checkOnError() {
        val resultItem = Exception()

        `when`(itemModel.getAllCurrency(org.mockito.kotlin.any())).then {
            (it.arguments[0] as RepoResultCallback<List<CurrencyItem>>).onError(resultItem)
        }

        itemModel.getAllCurrency(result = responseCallback)
        verify(itemModel, times(1)).getAllCurrency(result = responseCallback)
        verify(responseCallback, times(1)).onError(resultItem)
    }

}

