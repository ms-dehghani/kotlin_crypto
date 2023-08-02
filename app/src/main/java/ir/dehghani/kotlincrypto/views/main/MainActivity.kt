package ir.dehghani.kotlincrypto.views.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import ir.dehghani.kotlincrypto.base.arch.BaseActivity
import ir.dehghani.kotlincrypto.ui.theme.KotlinCryptoTheme
import ir.dehghani.kotlincrypto.views.main.presenter.MainPagePresenter
import ir.dehghani.kotlincrypto.views.main.presenter.MainPagePresenterContract
import kotlinx.coroutines.DelicateCoroutinesApi

class MainActivity : BaseActivity<MainPagePresenterContract>() {

    var count = 0
    override fun setViewModel(): MainPagePresenterContract {
        return ViewModelProvider(this)[MainPagePresenter::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KotlinCryptoTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting(getViewModel(), "$count")
                }
            }
        }

        getViewModel().getCurrencyListLiveData().observe(this as LifecycleOwner) {
            println(it.size)
        }

    }


}

@OptIn(DelicateCoroutinesApi::class)
fun doInBackground(presenter: MainPagePresenterContract) {
    presenter.getAllCurrency()
}


@Composable
fun Greeting(presenter: MainPagePresenterContract, name: String, modifier: Modifier = Modifier) {
    ClickableText(
        text = AnnotatedString("Hello $name!"),
        modifier = modifier,
        onClick = {
            doInBackground(presenter)
        }
    )
}