package ir.dehghani.kotlincrypto.model.repository.api

import com.google.gson.reflect.TypeToken
import ir.dehghani.kotlincrypto.BuildConfig
import ir.dehghani.kotlincrypto.model.FullModelImpl
import ir.dehghani.kotlincrypto.model.items.currency.pojo.CurrencyItem
import ir.dehghani.kotlincrypto.model.repository.api.utility.RequestTypeEnum
import ir.dehghani.kotlincrypto.model.repository.api.utility.WebserviceCaller
import ir.dehghani.kotlincrypto.model.repository.api.utility.WebserviceMiddleware
import ir.dehghani.kotlincrypto.model.repository.utils.RepoResultCallback
import java.lang.Exception
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class WebserviceRepo @Inject constructor(private val webserviceCaller: WebserviceCaller) : FullModelImpl {

    private val serviceMiddleware: WebserviceMiddleware = WebserviceMiddleware

    private val version1 = "v1"

    override suspend fun getAllCurrency(result: RepoResultCallback<List<CurrencyItem>>) {
        serviceMiddleware.call(
            callingFunction = { webserviceCaller.call("${BuildConfig.BASE_URL}/$version1/cryptocurrency/listings/latest", "", RequestTypeEnum.Get) },
            resultCallback = result,
            object : TypeToken<List<CurrencyItem>>() {}
        )
    }

    override suspend fun getCurrency(ID: String, result: RepoResultCallback<CurrencyItem>) {
        if (ID.isEmpty()) {
            result.onError(Exception("ID is empty!"))
        } else
            serviceMiddleware.call(callingFunction = { webserviceCaller.call("${BuildConfig.BASE_URL}/$version1", "", RequestTypeEnum.Get) }, result,
                object : TypeToken<CurrencyItem>() {})
    }
}