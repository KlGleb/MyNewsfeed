package at.gleb.mynewsfeed.data

import at.gleb.mynewsfeed.domain.NewsRepository
import at.gleb.mynewsfeed.domain.entity.SourceVo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApiService
) : NewsRepository {
    override fun getSources(): Single<List<SourceVo>> =
        api.getSources()
            .subscribeOn(Schedulers.io())
            .map { response ->
                response.sources?.map {
                    SourceVo(
                        id = it.id!!,
                        title = it.name.orEmpty(),
                        description = it.description.orEmpty(),
                        url = it.url.orEmpty()
                    )
                }.orEmpty()
            }
}