package ir.dehghani.kotlincrypto.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.dehghani.kotlincrypto.model.repository.api.utility.WebserviceCaller
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .callTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }


    @Provides
    fun provideApiService(client: OkHttpClient): WebserviceCaller {
        return WebserviceCaller(client = client)
    }

}