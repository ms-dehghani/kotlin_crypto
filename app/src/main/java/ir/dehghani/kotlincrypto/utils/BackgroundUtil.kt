package ir.dehghani.kotlincrypto.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

private val callQueue: ArrayList<String> = arrayListOf()

fun RunOnBackground(callingFunction: () -> Unit  ,  funID: String) {
    GlobalScope.launch(Dispatchers.IO) {
        println("fun id= $funID")
        if(!callQueue.contains(funID)) {
            callQueue.add(funID)
            callingFunction.invoke()
            callQueue.remove(funID)
        }
    }
}