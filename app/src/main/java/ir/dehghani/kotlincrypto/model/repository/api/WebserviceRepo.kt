package ir.dehghani.kotlincrypto.model.repository.api

import ir.dehghani.kotlincrypto.model.FullModelImpl
import ir.dehghani.kotlincrypto.pojo.CurrencyItem
import ir.dehghani.kotlincrypto.pojo.currencyItemEmpty
import ir.dehghani.kotlincrypto.model.repository.api.utility.RequestTypeEnum
import ir.dehghani.kotlincrypto.model.repository.api.utility.WebserviceCaller
import ir.dehghani.kotlincrypto.model.repository.api.utility.WebserviceMiddleware
import ir.dehghani.kotlincrypto.model.repository.utils.RepoMiddlewareFunc
import java.util.ArrayList

@Suppress("UNCHECKED_CAST")
class WebserviceRepo(private val webserviceCaller: WebserviceCaller) : FullModelImpl {

    private val serviceMiddleware: WebserviceMiddleware = WebserviceMiddleware

    override fun getAllCurrency(repoMiddlewareFunc: RepoMiddlewareFunc): List<CurrencyItem> {
        val result = serviceMiddleware.call(callingFunction = { webserviceCaller.call("", "", RequestTypeEnum.Get) }, repoMiddlewareFunc)
        if (result is List<*>)
            return result as List<CurrencyItem>
        return ArrayList<CurrencyItem>()
    }

    override fun getCurrency(ID: String, repoMiddlewareFunc: RepoMiddlewareFunc): CurrencyItem {
        val result = serviceMiddleware.call(callingFunction = { webserviceCaller.call("", "", RequestTypeEnum.Get) }, repoMiddlewareFunc)
        if (result is CurrencyItem)
            return result
        return currencyItemEmpty()
    }
}