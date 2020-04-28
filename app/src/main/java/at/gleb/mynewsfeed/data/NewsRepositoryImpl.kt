package at.gleb.mynewsfeed.data

import at.gleb.mynewsfeed.data.db.AppDatabase
import at.gleb.mynewsfeed.data.db.entity.SourceEntity
import at.gleb.mynewsfeed.domain.NewsRepository
import at.gleb.mynewsfeed.domain.entity.ArticleVo
import at.gleb.mynewsfeed.domain.entity.SourceVo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.*

class NewsRepositoryImpl(
    private val api: NewsApiService,
    private val db: AppDatabase
) : NewsRepository {

    private val _sources = BehaviorSubject.create<List<SourceVo>>()

    override fun getSources(): Observable<List<SourceVo>> = _sources
        .mergeWith(
            Maybe.fromCallable { db.userDao().getAll() }
                .map { it.toVoList() }
                .flatMap {
                    if (it.isNotEmpty()) {
                        Maybe.just(it)
                    } else {
                        Maybe.empty()
                    }
                }
        )
        .subscribeOn(Schedulers.io())

    override fun updateSources(): Completable =
        api.getSources()
            .subscribeOn(Schedulers.io())
            .map { response ->
                response.sources?.map {
                    SourceEntity(
                        id = it.id!!,
                        title = it.name.orEmpty(),
                        description = it.description.orEmpty(),
                        url = it.url.orEmpty()
                    )
                }.orEmpty()
            }
            .doOnSuccess {
                _sources.onNext(it.toVoList())
                db.userDao().clearAndInsert(it)
            }
            .ignoreElement()

    override fun getArticles(sources: List<String>): Observable<List<ArticleVo>> {
        return api.getEverything(sources = sources.joinToString())
            .subscribeOn(Schedulers.io())
            .map {
                it.articles?.map { articleDto ->
                    ArticleVo(
                        title = articleDto.title.orEmpty(),
                        description = articleDto.description.orEmpty(),
                        thumbnail = articleDto.urlToImage.orEmpty(),
                        author = articleDto.author.orEmpty(),
                        publishedDate = articleDto.publishedAt ?: Date(0)
                    )
                } ?: arrayListOf()
            }
            .toObservable()
    }

}
