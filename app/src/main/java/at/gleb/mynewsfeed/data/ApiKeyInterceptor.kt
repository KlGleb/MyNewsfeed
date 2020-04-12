package at.gleb.mynewsfeed.data

import at.gleb.mynewsfeed.Config.API_KEY
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

const val X_API_KEY = "X-Api-Key"

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader(X_API_KEY, API_KEY)
            .build()

        return chain.proceed(newRequest)
    }
}