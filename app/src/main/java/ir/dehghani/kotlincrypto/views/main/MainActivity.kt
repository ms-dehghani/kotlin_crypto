package ir.dehghani.kotlincrypto.views.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ir.dehghani.kotlincrypto.base.arch.BaseActivity
import ir.dehghani.kotlincrypto.model.items.currency.pojo.CurrencyItem
import ir.dehghani.kotlincrypto.ui.theme.KotlinCryptoTheme
import ir.dehghani.kotlincrypto.views.main.presenter.MainPageVMP
import ir.dehghani.kotlincrypto.views.main.presenter.MainPageVMPContract
import org.koin.android.ext.android.getKoin


var count = 0

class MainActivity : BaseActivity<MainPageVMPContract>() {


    override fun setViewModel(): MainPageVMPContract {
        return getKoin().get<MainPageVMP>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            KotlinCryptoTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    listView(getViewModel())
                }
            }
        }

        getViewModel().getAllCurrency()

    }


}

@Composable
fun listView(pageState: MainPageVMPContract, modifier: Modifier = Modifier) {
    val state by pageState.getPageState()

    if (state.isLoading) {
        CircularProgressIndicator(modifier = modifier.requiredSize(48.dp, 48.dp))
    } else {
        LazyColumn {
            items(count = state.items.size) { index ->
                currencyItemView(state.items[index])
            }
        }
    }
}

@Composable
fun currencyItemView(item: CurrencyItem, modifier: Modifier = Modifier) {
    Card(modifier = modifier
        .fillMaxWidth()
        .height(48.dp)
        .padding(8.dp), content = {
        Text(text = item.name)
    })
}