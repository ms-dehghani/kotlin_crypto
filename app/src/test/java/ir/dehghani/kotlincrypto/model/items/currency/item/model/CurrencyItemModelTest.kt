package ir.dehghani.kotlincrypto.model.items.currency.item.model

import ir.dehghani.kotlincrypto.model.items.currency.getFakeCurrency
import ir.dehghani.kotlincrypto.model.items.currency.pojo.CurrencyItem
import ir.dehghani.kotlincrypto.model.repository.utils.RepoResultCallback
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any

class CurrencyItemModelTest {

    val itemModel = mock(CurrencyItemModel::class.java)
    val responseCallback = mock(RepoResultCallback::class.java) as RepoResultCallback<CurrencyItem>


    @Test
    fun getCurrency() = runTest(UnconfinedTestDispatcher()){
        val resultItem = getFakeCurrency()


        `when`(itemModel.getCurrency(org.mockito.kotlin.any(), org.mockito.kotlin.any())).then {
            (it.arguments[1] as RepoResultCallback<CurrencyItem>).onResponse(resultItem)
        }

        `when`(responseCallback.onResponse(org.mockito.kotlin.any())).then {
            assertEquals((it.arguments[0] as CurrencyItem).id, resultItem.id)
        }

        itemModel.getCurrency(resultItem.id, result = responseCallback)
        verify(itemModel, times(1)).getCurrency(resultItem.id, result = responseCallback)
    }

    @Test
    fun checkOnSuccess() = runTest(UnconfinedTestDispatcher()){
        val resultItem = getFakeCurrency()

        `when`(itemModel.getCurrency(org.mockito.kotlin.any(), org.mockito.kotlin.any())).then {
            (it.arguments[1] as RepoResultCallback<CurrencyItem>).onResponse(resultItem)
        }

        itemModel.getCurrency(resultItem.id, result = responseCallback)
        verify(itemModel, times(1)).getCurrency(resultItem.id, result = responseCallback)
        verify(responseCallback, times(1)).onResponse(resultItem)
    }

    @Test
    fun checkSameResponse() {
        val resultItem = getFakeCurrency()

        lateinit var item: CurrencyItem

        `when`(responseCallback.onResponse(org.mockito.kotlin.any())).then {
            item = it.arguments[0] as CurrencyItem
            null
        }

        responseCallback.onResponse(resultItem)

        assertEquals(item.id, resultItem.id)
    }


    @Test
    fun checkOnError() = runTest(UnconfinedTestDispatcher()){
        val resultItem = Exception()

        `when`(itemModel.getCurrency(any(), any())).then {
            (it.arguments[1] as RepoResultCallback<CurrencyItem>).onError(resultItem)
        }

        itemModel.getCurrency("", result = responseCallback)
        verify(itemModel, times(1)).getCurrency("", result = responseCallback)
        verify(responseCallback, times(1)).onError(resultItem)
    }


}

