package ir.dehghani.kotlincrypto.model.items.currency.item.presenter

import ir.dehghani.kotlincrypto.SingletonBaseTest
import ir.dehghani.kotlincrypto.model.items.currency.item.CurrencyItemModelImpl
import ir.dehghani.kotlincrypto.model.items.currency.item.model.CurrencyItemModel
import ir.dehghani.kotlincrypto.model.repository.utils.RepoResultCallback
import ir.dehghani.kotlincrypto.pojo.CurrencyItem
import net.datafaker.Faker
import org.json.JSONObject
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.mockStatic
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import java.lang.reflect.Field


class CurrencyItemPresenterTest : SingletonBaseTest() {

    var faker = Faker()

    val repo = mock(CurrencyItemModelImpl::class.java)
    val model = mock(CurrencyItemModel::class.java)

    @Before
    fun init() {
        setMock(CurrencyItemModel.getInstance(repo), CurrencyItemModel::class.java.getDeclaredField("instance"))
    }

    @Test
    fun getCurrency() {
        checkOnResponseData()
        checkOnErrorData()
    }


    private fun checkOnResponseData() {

        val fakeResult = getFakeCurrency()
        lateinit var resultItem: CurrencyItem

        val resultCallback = object : RepoResultCallback<CurrencyItem> {
            override fun onResponse(data: CurrencyItem) {
                resultItem = data
            }

            override fun onError(ex: Exception) {
            }

        }

        `when`(model.getCurrency(fakeResult.id, resultCallback)).then {
            synchronized(this) {
                resultCallback.onResponse(fakeResult)
            }
        }

        model.getCurrency(fakeResult.id, resultCallback)

        verify(model, Mockito.times(1)).getCurrency(fakeResult.id, result = resultCallback)
        assertEquals(resultItem.id, fakeResult.id)

    }

    private fun checkOnErrorData() {

        val fakeResult = getFakeCurrency()
        lateinit var resultItem: Exception

        val resultCallback = object : RepoResultCallback<CurrencyItem> {
            override fun onResponse(data: CurrencyItem) {
            }

            override fun onError(ex: Exception) {
                resultItem = ex
            }

        }

        `when`(model.getCurrency(fakeResult.id, resultCallback)).then {
            synchronized(this) {
                resultCallback.onError(Exception(""))
            }
        }

        model.getCurrency(fakeResult.id, resultCallback)

        verify(model, Mockito.times(1)).getCurrency(fakeResult.id, result = resultCallback)
        assertEquals(resultItem.message, "")

    }


    private fun getFakeCurrency() = CurrencyItem(faker.number().positive().toString(), faker.name().firstName(), faker.name().lastName(), JSONObject())


}