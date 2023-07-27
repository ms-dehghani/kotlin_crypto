package ir.dehghani.kotlincrypto.base.arch.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel

abstract class BaseActivity<T : BaseViewModel> : ComponentActivity() {

    private lateinit var viewModel: T

    abstract fun setViewModel(): T

    fun getViewModel(): T {
        return viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = setViewModel();
    }

}