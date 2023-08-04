package ir.dehghani.kotlincrypto

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import java.lang.reflect.Field
import androidx.arch.core.executor.testing.InstantTaskExecutorRule


open class BaseClassTest {

    private val dispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    fun setMock(mock: Any, instance: Field) {
        try {
            instance.setAccessible(true)
            instance.set(instance, mock)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}