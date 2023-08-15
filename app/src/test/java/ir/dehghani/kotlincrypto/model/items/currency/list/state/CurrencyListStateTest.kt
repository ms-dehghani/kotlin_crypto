package ir.dehghani.kotlincrypto.model.items.currency.list.state

import ir.dehghani.kotlincrypto.BaseClassTest
import ir.dehghani.kotlincrypto.getOrAwaitValue
import ir.dehghani.kotlincrypto.model.items.currency.getFakeCurrency
import ir.dehghani.kotlincrypto.model.items.currency.getFakeCurrencyList
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test


class CurrencyListStateTest : BaseClassTest() {


    var state = CurrencyListState

    @Test
    fun checkNotNull() {
        assertNotNull(state.getItemList())
    }

    @Test
    fun checkPostSingleValue() {

        val fakeValue = getFakeCurrencyList()
        state.getItemList().postValue(fakeValue)
        val value = state.getItemList().getOrAwaitValue()

        for (i in 0 until fakeValue.count())
            assertEquals(fakeValue[i], value[i])
    }

}