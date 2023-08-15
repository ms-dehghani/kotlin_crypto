package ir.dehghani.kotlincrypto.model.items.currency.item.state

import ir.dehghani.kotlincrypto.BaseClassTest
import ir.dehghani.kotlincrypto.getOrAwaitValue
import ir.dehghani.kotlincrypto.model.items.currency.getFakeCurrency
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test


class CurrencyItemStateTest : BaseClassTest() {


    var state = CurrencyItemState

    @Test
    fun checkNotNull() {
        assertNotNull(state.getItemDetail())
    }

    @Test
    fun checkPostSingleValue() {

        val fakeValue = getFakeCurrency()
        state.getItemDetail().postValue(fakeValue)
        val value = state.getItemDetail().getOrAwaitValue()

        assertEquals(fakeValue, value)
    }

}