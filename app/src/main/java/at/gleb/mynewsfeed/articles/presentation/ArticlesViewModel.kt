package at.gleb.mynewsfeed.articles.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toLiveData
import at.gleb.mynewsfeed.articles.data.ArticleDataSourceFactory
import at.gleb.mynewsfeed.articles.di.ArticlesComponent
import at.gleb.mynewsfeed.articles.di.ArticlesPagingModule
import at.gleb.mynewsfeed.di.AppScope
import at.gleb.mynewsfeed.domain.NewsInteractor
import at.gleb.mynewsfeed.domain.entity.ArticleVo
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject
import javax.inject.Provider

@AppScope
class ArticlesViewModel @Inject constructor(
    private val interactor: NewsInteractor,
    private val articleComponentProvider: Provider<ArticlesComponent.Builder>
) : ViewModel() {
    private val disposable = CompositeDisposable()

    @Inject
    lateinit var dataSourceFactory: ArticleDataSourceFactory

    lateinit var sourceId: String
    lateinit var articles: LiveData<PagedList<ArticleVo>>

    fun onGetSourceId(sourceId: String) {
        articleComponentProvider.get()
            .articlesPagingModule(ArticlesPagingModule(sourceId))
            .build()
            .inject(this)

        this.sourceId = sourceId
        articles = dataSourceFactory.toLiveData(1)
        updateData()
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    fun onRefresh() {
        updateData()
    }

    private fun updateData() {
        dataSourceFactory.sourceLiveData.value?.invalidate()
    }
}