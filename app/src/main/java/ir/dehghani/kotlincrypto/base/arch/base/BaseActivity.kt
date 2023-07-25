package ir.dehghani.kotlincrypto.base.arch.base

import androidx.activity.ComponentActivity

abstract class BaseActivity<T> : ComponentActivity() {

    val presenter: T = this.setPresenter()

    abstract fun setPresenter(): T
}