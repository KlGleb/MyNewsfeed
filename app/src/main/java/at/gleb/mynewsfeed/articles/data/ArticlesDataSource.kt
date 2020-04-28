package at.gleb.mynewsfeed.articles.data

import androidx.paging.PageKeyedDataSource
import at.gleb.mynewsfeed.data.NewsApiService
import at.gleb.mynewsfeed.domain.entity.ArticleVo
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

const val TOTAL_COUNT_LIMIT = 100 //Developer accounts are limited to a max of 100 results
const val ITEMS_PER_PAGE = 20

class ArticlesDataSource constructor(
    private val api: NewsApiService,
    private val sourceId: String
) : PageKeyedDataSource<Int, ArticleVo>() {

    private val disposable = CompositeDisposable()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, ArticleVo>
    ) {
        disposable += api.getEverything(sources = sourceId, pageSize = ITEMS_PER_PAGE)
            .subscribeOn(Schedulers.io())
            .retry(3)
            .subscribeBy(
                onSuccess = {
                    callback.onResult(
                        it.articles.toArticleVos(),
                        0,
                        (it.totalResults ?: 0).coerceAtMost(TOTAL_COUNT_LIMIT),
                        null,
                        2
                    )
                },
                onError = { }
            )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, ArticleVo>) {
        disposable += api.getEverything(
            sources = sourceId,
            page = params.key,
            pageSize = ITEMS_PER_PAGE
        )
            .subscribeOn(Schedulers.io())
            .retry(2)
            .subscribeBy(
                onSuccess = {
                    val list = it.articles.toArticleVos()
                    val nextPageKey =
                        if (list.isNotEmpty() && params.key < (TOTAL_COUNT_LIMIT / ITEMS_PER_PAGE)) params.key + 1 else null
                    callback.onResult(list, nextPageKey)
                },
                onError = { }
            )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, ArticleVo>) {
        disposable += api.getEverything(sources = sourceId, page = params.key)
            .subscribeOn(Schedulers.io())
            .retry(2)
            .subscribeBy(
                onSuccess = {
                    val list = it.articles.toArticleVos()
                    val nextPageKey = if (params.key > 1) params.key - 1 else null
                    callback.onResult(list, nextPageKey)
                },
                onError = { }
            )
    }

    override fun invalidate() {
        disposable.dispose()
        super.invalidate()
    }
}