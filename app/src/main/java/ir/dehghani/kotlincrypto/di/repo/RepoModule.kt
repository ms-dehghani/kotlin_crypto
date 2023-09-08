package ir.dehghani.kotlincrypto.di.repo

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.dehghani.kotlincrypto.di.repo.qualifires.LocalRepo
import ir.dehghani.kotlincrypto.di.repo.qualifires.ServiceRepo
import ir.dehghani.kotlincrypto.model.repository.Repository
import ir.dehghani.kotlincrypto.model.repository.api.WebserviceRepo
import ir.dehghani.kotlincrypto.model.repository.api.utility.WebserviceCaller


@Module
@InstallIn(SingletonComponent::class)
class RepoModule {

    @Provides
    @ServiceRepo
    fun provideWebServiceRepo(webserviceCaller: WebserviceCaller) = WebserviceRepo(webserviceCaller)

    @Provides
    @LocalRepo
    fun provideLocalRepo(webserviceCaller: WebserviceCaller) = WebserviceRepo(webserviceCaller)

    @Provides
    fun provideRepository(serviceRepo: WebserviceRepo, localRepo: WebserviceRepo?): Repository {
        return Repository(serviceRepo, localRepo)
    }

}