package ir.dehghani.kotlincrypto

import android.app.Application
import ir.dehghani.kotlincrypto.model.repository.Repository
import ir.dehghani.kotlincrypto.model.repository.api.WebserviceRepo
import ir.dehghani.kotlincrypto.model.repository.api.utility.WebserviceCaller
import okhttp3.OkHttpClient
import java.time.Duration
import java.util.concurrent.TimeUnit

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        WebserviceCaller.init(getOkHttpClient())

        Repository.init(serviceRepo = WebserviceRepo(WebserviceCaller.getInstance()), null)

    }


    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .callTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

}