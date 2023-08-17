package ir.dehghani.kotlincrypto.di.module

import ir.dehghani.kotlincrypto.model.repository.api.utility.WebserviceCaller
import okhttp3.OkHttpClient
import org.koin.dsl.module
import java.util.concurrent.TimeUnit


val appModule = module {
    single { provideOkHttpClient() }
    single { provideApiService(get()) }
}


private fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .callTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .build()
}

private fun provideApiService(client: OkHttpClient): WebserviceCaller {
    WebserviceCaller.init(client)
    return WebserviceCaller.getInstance()
}
