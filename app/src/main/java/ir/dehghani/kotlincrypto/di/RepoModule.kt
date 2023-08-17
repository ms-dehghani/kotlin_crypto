package ir.dehghani.kotlincrypto.di.module

import ir.dehghani.kotlincrypto.model.FullModelImpl
import ir.dehghani.kotlincrypto.model.repository.Repository
import ir.dehghani.kotlincrypto.model.repository.api.WebserviceRepo
import ir.dehghani.kotlincrypto.model.repository.api.utility.WebserviceCaller
import org.koin.dsl.module


val repoModule = module {
    single { provideRepository(provideWebServiceRepo(get()), provideLocalRepo(get())) }
}

private fun provideWebServiceRepo(webserviceCaller: WebserviceCaller) = WebserviceRepo(webserviceCaller)
private fun provideLocalRepo(webserviceCaller: WebserviceCaller) = WebserviceRepo(webserviceCaller)

private fun provideRepository(serviceRepo: FullModelImpl, localRepo: FullModelImpl?): Repository {
    Repository.init(serviceRepo, localRepo)
    return Repository.getInstance()
}