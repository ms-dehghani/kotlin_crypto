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
import ir.dehghani.kotlincrypto.base.arch.BaseActivity
import ir.dehghani.kotlincrypto.ui.theme.KotlinCryptoTheme
import ir.dehghani.kotlincrypto.views.main.presenter.MainPageVMP
import ir.dehghani.kotlincrypto.views.main.presenter.MainPageVMPContract
import kotlinx.coroutines.DelicateCoroutinesApi
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<MainPageVMPContract>() {

    var count = 0

    override fun setViewModel(): MainPageVMPContract {
        return getKoin().get<MainPageVMP>()
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
fun doInBackground(presenter: MainPageVMPContract?) {
    presenter!!.getAllCurrency()
}


@Composable
fun Greeting(presenter: MainPageVMPContract?, name: String, modifier: Modifier = Modifier) {
    ClickableText(
        text = AnnotatedString("Hello $name!"),
        modifier = modifier,
        onClick = {
            doInBackground(presenter)
        }
    )
}