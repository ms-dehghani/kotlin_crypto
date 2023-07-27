package ir.dehghani.kotlincrypto

import android.app.Application
import ir.dehghani.kotlincrypto.model.repository.Repository

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Repository.init(null , null)

    }

}