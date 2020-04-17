package at.gleb.mynewsfeed.di

import android.app.Application
import at.gleb.mynewsfeed.di.modules.*
import at.gleb.mynewsfeed.sources.di.SourcesModule
import at.gleb.mynewsfeed.sources.presentation.SourcesFragment
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    modules = [
        JacksonMapperModule::class,
        RetrofitModule::class,
        NewsApiModule::class,
        SourcesModule::class,
        MyNewsfeedModule::class,
        AppDatabaseModule::class
    ]
)
interface AppComponent {
    fun inject(sourcesFragment: SourcesFragment)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder

    }
}