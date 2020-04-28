package at.gleb.mynewsfeed.di

import androidx.room.Room
import at.gleb.mynewsfeed.BuildConfig
import at.gleb.mynewsfeed.Config
import at.gleb.mynewsfeed.articles.data.ArticleDataSourceFactory
import at.gleb.mynewsfeed.articles.presentation.ArticlesViewModel
import at.gleb.mynewsfeed.data.ApiKeyInterceptor
import at.gleb.mynewsfeed.data.NewsApiService
import at.gleb.mynewsfeed.data.NewsRepositoryImpl
import at.gleb.mynewsfeed.data.db.AppDatabase
import at.gleb.mynewsfeed.domain.NewsInteractor
import at.gleb.mynewsfeed.domain.NewsRepository
import at.gleb.mynewsfeed.sources.presentation.SourcesViewModel
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

const val DB_NAME = "mynewsfeed"
const val JACKSON_MAPPER = "JACKSON_MAPPER"
const val API_KEY_INTERCEPTOR = "API_KEY_INTERCEPTOR"
const val DEFAULT_OK_HTTP_BUILDER = "DEFAULT_OK_HTTP_BUILDER"
const val OK_HTTP_CLIENT = "OK_HTTP_CLIENT"
const val RETROFIT_BUILDER = "RETROFIT_BUILDER"

val appModule = module {
    single { Room.databaseBuilder(get(), AppDatabase::class.java, DB_NAME).build() }
    single(named(JACKSON_MAPPER)) { jacksonObjectMapper().registerKotlinModule() }

    single {
        get<Retrofit.Builder>(named(RETROFIT_BUILDER))
            .baseUrl(Config.SERVER_URL)
            .build()
            .create(NewsApiService::class.java)
    }

    single(named(DEFAULT_OK_HTTP_BUILDER)) {
        OkHttpClient.Builder()
    }

    single(named(OK_HTTP_CLIENT)) {
        get<OkHttpClient.Builder>(named(DEFAULT_OK_HTTP_BUILDER))
            .addNetworkInterceptor(get<Interceptor>(named(API_KEY_INTERCEPTOR)))
            .apply {
                if (BuildConfig.DEBUG) {
                    addNetworkInterceptor(StethoInterceptor())
                }
            }
            .build()
    }

    single(named(RETROFIT_BUILDER)) {
        Retrofit.Builder()
            .client(get(named(OK_HTTP_CLIENT)))
            .addConverterFactory(JacksonConverterFactory.create(get(named(JACKSON_MAPPER))))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    }

    single<Interceptor>(named(API_KEY_INTERCEPTOR)) {
        ApiKeyInterceptor()
    }

    single<NewsRepository> { NewsRepositoryImpl(get(), get()) }
    single { NewsInteractor(get()) }
    viewModel { SourcesViewModel(get()) }
    viewModel { ArticlesViewModel(get()) }

    factory { (sourceId: String) ->
        ArticleDataSourceFactory(get(), sourceId)
    }
}