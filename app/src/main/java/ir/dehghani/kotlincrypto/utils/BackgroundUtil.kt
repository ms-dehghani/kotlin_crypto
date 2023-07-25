package ir.dehghani.kotlincrypto.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun RunOnBackground(callingFunction: () -> Unit) {
    GlobalScope.launch(Dispatchers.IO) {
        callingFunction.invoke()
    }
}