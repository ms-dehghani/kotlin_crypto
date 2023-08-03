package ir.dehghani.kotlincrypto

import java.lang.reflect.Field

open class SingletonBaseTest {


    fun setMock(mock: Any, instance: Field) {
        try {
            instance.setAccessible(true)
            instance.set(instance, mock)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}