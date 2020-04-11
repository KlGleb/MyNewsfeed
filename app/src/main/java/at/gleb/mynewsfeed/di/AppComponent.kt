package at.gleb.mynewsfeed.di

import android.app.Application
import at.gleb.mynewsfeed.di.modules.JacksonMapperModule
import at.gleb.mynewsfeed.di.modules.NewsApiModule
import at.gleb.mynewsfeed.di.modules.OkHttpClientModule
import at.gleb.mynewsfeed.di.modules.RetrofitModule
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    modules = [
        JacksonMapperModule::class,
        OkHttpClientModule::class,
        RetrofitModule::class,
        NewsApiModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder

    }
}