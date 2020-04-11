package at.gleb.mynewsfeed.di.modules

import at.gleb.mynewsfeed.di.AppScope
import com.fasterxml.jackson.databind.ObjectMapper
import dagger.Module
import dagger.Provides
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Named

const val RETROFIT_BUILDER = "RETROFIT_BUILDER"
const val SERVER_URL = "todo"//todo

@Module
class RetrofitModule {

    @AppScope
    @Provides
    @Named(RETROFIT_BUILDER)
    fun retrofitBuilder(
        @Named(OK_HTTP_CLIENT) client: OkHttpClient,
        @Named(JACKSON_MAPPER) mapper: ObjectMapper
    ): Retrofit.Builder = Retrofit.Builder()
        .client(client)
        .addConverterFactory(JacksonConverterFactory.create(mapper))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .baseUrl(SERVER_URL)
}
