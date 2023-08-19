package ir.dehghani.kotlincrypto.model.items.currency.list.presenter

import ir.dehghani.kotlincrypto.BaseClassTest
import ir.dehghani.kotlincrypto.getOrAwaitValue
import ir.dehghani.kotlincrypto.model.items.currency.getFakeCurrencyList
import ir.dehghani.kotlincrypto.model.items.currency.list.CurrencyListModelImpl
import ir.dehghani.kotlincrypto.model.items.currency.list.model.CurrencyListModel
import ir.dehghani.kotlincrypto.model.items.currency.pojo.CurrencyItem
import ir.dehghani.kotlincrypto.model.repository.utils.RepoResultCallback
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`


class CurrencyListPresenterTest : BaseClassTest() {

    val repo = mock(CurrencyListModelImpl::class.java)
    val model = mock(CurrencyListModel::class.java)

    @Before
    fun init() {
        setMock(CurrencyListModel.getInstance(repo), CurrencyListModel::class.java.getDeclaredField("instance"))
    }

    @Test
    fun checkSuccessResponse() = runTest(UnconfinedTestDispatcher()) {

        val presenter = CurrencyListPresenter.getInstance(model = model)

        val itemResponse = getFakeCurrencyList()

        `when`(model.getAllCurrency(org.mockito.kotlin.any())).then {
            (it.arguments[0] as RepoResultCallback<List<CurrencyItem>>).onResponse(itemResponse)
        }

        val value = presenter.getAllCurrency().getOrAwaitValue()

        assert(value != null)
        assert(value.count() == itemResponse.count())

        for (i in 0 until itemResponse.count())
            assert(value[i].id == itemResponse[i].id)
    }


    @Test
    fun checkUnSuccessResponse() = runTest(UnconfinedTestDispatcher()) {

        lateinit var resultItem: Exception

        val resultCallback = object : RepoResultCallback<List<CurrencyItem>> {
            override fun onResponse(data: List<CurrencyItem>) {
            }

            override fun onError(ex: Exception) {
                resultItem = ex
            }

        }

        `when`(model.getAllCurrency(resultCallback)).then {
            resultCallback.onError(Exception(""))
        }

        model.getAllCurrency(resultCallback)

        verify(model, Mockito.times(1)).getAllCurrency(result = resultCallback)
        assertEquals(resultItem.message, "")

    }


}