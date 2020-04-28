package at.gleb.mynewsfeed.data

import at.gleb.mynewsfeed.data.dto.ResponseEverythingDto
import at.gleb.mynewsfeed.data.dto.ResponseSourcesDto
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApiService {
    @GET("everything")
    fun getEverything(
        @Query("language") language: String? = null,
        @Query("page") page: Int? = null,
        @Query("pageSize") pageSize: Int? = null,
        @Query("sources") sources: String? = null
    ): Single<ResponseEverythingDto>

    @GET("sources")
    fun getSources(
        @Query("language") language: String? = null
    ): Single<ResponseSourcesDto>
}