package ir.dehghani.kotlincrypto

import android.app.Application
import ir.dehghani.kotlincrypto.di.module.appModule
import ir.dehghani.kotlincrypto.di.module.presenterModule
import ir.dehghani.kotlincrypto.di.module.repoModule
import ir.dehghani.kotlincrypto.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, repoModule, presenterModule, presenterModule, viewModelModule))
        }
    }

}