package at.gleb.mynewsfeed.di.modules

import at.gleb.mynewsfeed.BuildConfig
import at.gleb.mynewsfeed.Config
import at.gleb.mynewsfeed.data.ApiKeyInterceptor
import at.gleb.mynewsfeed.data.NewsApiService
import at.gleb.mynewsfeed.di.AppScope
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named

const val API_KEY_INTERCEPTOR = "API_KEY_INTERCEPTOR"
const val DEFAULT_OK_HTTP_BUILDER = "DEFAULT_OK_HTTP_BUILDER"
const val OK_HTTP_CLIENT = "OK_HTTP_CLIENT"

@Module
open class NewsApiModule {

    @Provides
    @AppScope
    fun provideApiService(@Named(RETROFIT_BUILDER) retrofitBuilder: Retrofit.Builder): NewsApiService =
        retrofitBuilder
            .baseUrl(Config.SERVER_URL)
            .build()
            .create(NewsApiService::class.java)

    @AppScope
    @Provides
    @Named(DEFAULT_OK_HTTP_BUILDER)
    fun okHttpClientBuilder() = OkHttpClient.Builder()

    @AppScope
    @Provides
    @Named(OK_HTTP_CLIENT)
    fun okHttpClient(
        @Named(DEFAULT_OK_HTTP_BUILDER) defaultHttClientBuilder: OkHttpClient.Builder,
        @Named(API_KEY_INTERCEPTOR) interceptor: Interceptor
    ): OkHttpClient =
        defaultHttClientBuilder
            .addNetworkInterceptor(interceptor)
            .apply {
                if (BuildConfig.DEBUG) {
                    addNetworkInterceptor(StethoInterceptor())
                }
            }
            .build()

    @AppScope
    @Provides
    @Named(API_KEY_INTERCEPTOR)
    fun apiKeyInterceptor(): Interceptor = ApiKeyInterceptor()
}