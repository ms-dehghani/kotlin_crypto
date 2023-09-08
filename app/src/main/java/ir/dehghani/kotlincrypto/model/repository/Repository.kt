package ir.dehghani.kotlincrypto.model.repository

import ir.dehghani.kotlincrypto.model.FullModelImpl
import ir.dehghani.kotlincrypto.model.items.currency.pojo.CurrencyItem
import ir.dehghani.kotlincrypto.model.repository.utils.RepoResultCallback
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(val serviceRepo: FullModelImpl, val localRepo: FullModelImpl?) : RepoImpl {

    override suspend fun getAllCurrency(result: RepoResultCallback<List<CurrencyItem>>) {
        return serviceRepo.getAllCurrency(result)
    }

    override suspend fun getCurrency(ID: String, result: RepoResultCallback<CurrencyItem>) {
        return serviceRepo.getCurrency(ID, result)
    }
}