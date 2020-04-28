package at.gleb.mynewsfeed

import android.app.Application
import at.gleb.mynewsfeed.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        startKoin {
            androidContext(this@App)
            modules(appModule)
            androidLogger()
        }
    }
}