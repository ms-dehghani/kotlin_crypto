package ir.dehghani.kotlincrypto.utils

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

private val callQueue: ArrayList<String> = arrayListOf()

@OptIn(DelicateCoroutinesApi::class)
fun runOnBackground(callingFunction: () -> Unit, funID: String) {
    GlobalScope.launch(Dispatchers.IO) {
        if (!callQueue.contains(funID)) {
            callQueue.add(funID)
            callingFunction.invoke()
            callQueue.remove(funID)
        }
    }
}