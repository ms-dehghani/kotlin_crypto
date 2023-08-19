package ir.dehghani.kotlincrypto.model.items.currency.item.presenter

import ir.dehghani.kotlincrypto.BaseClassTest
import ir.dehghani.kotlincrypto.getOrAwaitValue
import ir.dehghani.kotlincrypto.model.items.currency.getFakeCurrency
import ir.dehghani.kotlincrypto.model.items.currency.item.CurrencyItemModelImpl
import ir.dehghani.kotlincrypto.model.items.currency.item.model.CurrencyItemModel
import ir.dehghani.kotlincrypto.model.items.currency.pojo.CurrencyItem
import ir.dehghani.kotlincrypto.model.repository.utils.RepoResultCallback
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`


class CurrencyItemPresenterTest : BaseClassTest() {

    val repo = mock(CurrencyItemModelImpl::class.java)
    val model = mock(CurrencyItemModel::class.java)

    @Before
    fun init() {
        setMock(CurrencyItemModel.getInstance(repo), CurrencyItemModel::class.java.getDeclaredField("instance"))
    }

    @Test
    fun checkSuccessResponse() = runTest(UnconfinedTestDispatcher()) {

        val presenter = CurrencyItemPresenter.getInstance(model = model)

        val itemResponse = getFakeCurrency()

        `when`(model.getCurrency(org.mockito.kotlin.any(), org.mockito.kotlin.any())).then {
            (it.arguments[1] as RepoResultCallback<CurrencyItem>).onResponse(itemResponse)
        }


        val value = presenter.getCurrency("").getOrAwaitValue()

        assert(value != null)
        assert(value.id == itemResponse.id)
    }


    @Test
    fun checkUnSuccessResponse() = runTest(UnconfinedTestDispatcher()) {

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
            resultCallback.onError(Exception(""))
        }

        model.getCurrency(fakeResult.id, resultCallback)

        verify(model, Mockito.times(1)).getCurrency(fakeResult.id, result = resultCallback)
        assertEquals(resultItem.message, "")

    }


}