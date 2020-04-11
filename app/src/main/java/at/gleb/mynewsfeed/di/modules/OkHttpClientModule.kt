package at.gleb.mynewsfeed.di.modules

import android.annotation.SuppressLint
import at.gleb.mynewsfeed.BuildConfig
import at.gleb.mynewsfeed.di.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

const val OK_HTTP_CLIENT = "OK_HTTP_CLIENT"

@Module
class OkHttpClientModule {
    @AppScope
    @Provides
    @Named("default")
    fun okHttpClientBuilder() = OkHttpClient.Builder()

    @AppScope
    @Provides
    @Named(OK_HTTP_CLIENT)
    fun okHttpClient(
        @Named("default") defaultHttClientBuilder: OkHttpClient.Builder,
        @Named("unsafe") unsafeOkHttClientBuilder: OkHttpClient.Builder
    ): OkHttpClient = if (BuildConfig.DEBUG) {
        unsafeOkHttClientBuilder
    } else {
        defaultHttClientBuilder
    }.build()


    @Provides
    @Named("unsafe")
    fun unsafeOkHttpClientBuilder(): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()

        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                @SuppressLint("TrustAllX509TrustManager")
                override fun checkClientTrusted(
                    chain: Array<java.security.cert.X509Certificate>,
                    authType: String
                ) {
                }

                @SuppressLint("TrustAllX509TrustManager")
                override fun checkServerTrusted(
                    chain: Array<java.security.cert.X509Certificate>,
                    authType: String
                ) {
                }

                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                    return arrayOf()
                }
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory

            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier(HostnameVerifier { _, _ -> true })
            return builder
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}