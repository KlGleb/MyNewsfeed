package at.gleb.mynewsfeed.di.modules

import at.gleb.mynewsfeed.data.NewsApi
import at.gleb.mynewsfeed.di.AppScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named

@Module
open class NewsApiModule {

    @Provides
    @AppScope
    fun provideApiService(@Named(RETROFIT_BUILDER) retrofitBuilder: Retrofit.Builder): NewsApi =
        retrofitBuilder
            .build()
            .create(NewsApi::class.java)

}