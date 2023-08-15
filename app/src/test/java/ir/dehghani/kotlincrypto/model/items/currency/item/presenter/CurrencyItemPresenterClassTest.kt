package ir.dehghani.kotlincrypto.model.items.currency.item.presenter

import ir.dehghani.kotlincrypto.BaseClassTest
import ir.dehghani.kotlincrypto.getOrAwaitValue
import ir.dehghani.kotlincrypto.model.items.currency.item.CurrencyItemModelImpl
import ir.dehghani.kotlincrypto.model.items.currency.item.model.CurrencyItemModel
import ir.dehghani.kotlincrypto.model.items.currency.item.state.CurrencyItemState
import ir.dehghani.kotlincrypto.model.repository.utils.RepoResultCallback
import ir.dehghani.kotlincrypto.model.items.currency.pojo.CurrencyItem
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import net.datafaker.Faker
import org.json.JSONObject
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`


class CurrencyItemPresenterClassTest : BaseClassTest() {

    var faker = Faker()

    val repo = mock(CurrencyItemModelImpl::class.java)
    val model = mock(CurrencyItemModel::class.java)

    @Before
    fun init() {
        setMock(CurrencyItemModel.getInstance(repo), CurrencyItemModel::class.java.getDeclaredField("instance"))
    }

    @Test
    fun checkSuccessResponse() = runTest(UnconfinedTestDispatcher()) {

        val stateManager = CurrencyItemState
        val presenter = CurrencyItemPresenter.getInstance(model = model, state = stateManager)

        val itemResponse = getFakeCurrency()

        `when`(model.getCurrency(org.mockito.kotlin.any(), org.mockito.kotlin.any())).then {
            (it.arguments[1] as RepoResultCallback<CurrencyItem>).onResponse(itemResponse)
        }

        presenter.getCurrency("")
        val value = stateManager.getItemDetail().getOrAwaitValue()

        assert(value != null)
        assert(value.id == itemResponse.id)
    }


    @Test
    fun checkUnSuccessResponse() {

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


    private fun getFakeCurrency() = CurrencyItem(faker.number().positive().toString(), faker.name().firstName(), faker.name().lastName(), JSONObject())


}