package ir.dehghani.kotlincrypto.model.items.currency.item.model

import ir.dehghani.kotlincrypto.model.repository.utils.RepoResultCallback
import ir.dehghani.kotlincrypto.pojo.CurrencyItem
import net.datafaker.Faker
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class CurrencyItemModelTest {

    var faker = Faker()

    val itemModel = mock(CurrencyItemModel::class.java)
    val responseCallback = mock(RepoResultCallback::class.java) as RepoResultCallback<CurrencyItem>


    @Test
    fun getCurrency() {
        val resultItem = getFakeCurrency()

        lateinit var callbackItem: CurrencyItem

        `when`(itemModel.getCurrency("1", result = responseCallback)).then {
            synchronized(this) {
                callbackItem = resultItem
                responseCallback.onResponse(resultItem)
            }
        }

        `when`(responseCallback.onResponse(resultItem)).then {
            synchronized(this) {
                assertEquals(resultItem.id, callbackItem.id)
            }
        }

        itemModel.getCurrency("1", result = responseCallback)
        verify(itemModel, times(1)).getCurrency("1", result = responseCallback)
    }

    @Test
    fun checkOnSuccess() {
        val resultItem = getFakeCurrency()

        `when`(itemModel.getCurrency("1", result = responseCallback)).then {
            responseCallback.onResponse(resultItem)
        }

        itemModel.getCurrency("1", result = responseCallback)
        verify(itemModel, times(1)).getCurrency("1", result = responseCallback)
        verify(responseCallback, times(1)).onResponse(resultItem)
    }

    @Test
    fun checkSameResponse() {
        val resultItem = getFakeCurrency()

        lateinit var item: CurrencyItem

        `when`(responseCallback.onResponse(resultItem)).then {
            synchronized(this) {
                item = resultItem
            }
        }

        responseCallback.onResponse(resultItem)

        assertEquals(item.id, resultItem.id)
    }


    @Test
    fun checkOnError() {
        val resultItem = Exception()

        `when`(itemModel.getCurrency("1", result = responseCallback)).then {
            responseCallback.onError(resultItem)
        }

        itemModel.getCurrency("1", result = responseCallback)
        verify(itemModel, times(1)).getCurrency("1", result = responseCallback)
        verify(responseCallback, times(1)).onError(resultItem)
    }


    private fun getFakeCurrency() = CurrencyItem(faker.number().positive(), faker.name().firstName(), faker.name().lastName(), JSONObject())

}

