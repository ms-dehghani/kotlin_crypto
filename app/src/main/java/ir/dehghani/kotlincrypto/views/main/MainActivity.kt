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
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import ir.dehghani.kotlincrypto.base.arch.base.BaseActivity
import ir.dehghani.kotlincrypto.base.arch.currency.item.presenter.CurrencyItemPresenter
import ir.dehghani.kotlincrypto.base.arch.currency.list.presenter.CurrencyListPresenter
import ir.dehghani.kotlincrypto.ui.theme.KotlinCryptoTheme
import ir.dehghani.kotlincrypto.views.main.presenter.MainPagePresenter
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<MainPagePresenter>() {

    var count = 0
    override fun setPresenter(): MainPagePresenter {
        return MainPagePresenter(CurrencyListPresenter.getInstance() , CurrencyItemPresenter.getInstance())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KotlinCryptoTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Greeting(presenter,"$count")
                }
            }
        }

        presenter.getCurrencyListLiveData().observe(this as LifecycleOwner) {
            println(it.size)
        }

    }


}

@OptIn(DelicateCoroutinesApi::class)
fun doInBackground(presenter: MainPagePresenter) {
    println("before call")
    presenter.getAllCurrency()
    println("after call")
}


@Composable
fun Greeting(presenter: MainPagePresenter , name: String, modifier: Modifier = Modifier) {
    ClickableText(
        text = AnnotatedString("Hello $name!"),
        modifier = modifier,
        onClick = {
            doInBackground(presenter)
        }
    )
}