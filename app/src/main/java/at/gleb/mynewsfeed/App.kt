package at.gleb.mynewsfeed

import android.app.Application
import at.gleb.mynewsfeed.di.AppComponent
import at.gleb.mynewsfeed.di.DaggerAppComponent

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        dagger = DaggerAppComponent.builder().application(this).build()
    }

    companion object {
        lateinit var dagger: AppComponent
    }
}