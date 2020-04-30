package at.gleb.mynewsfeed.articles.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.toLiveData
import at.gleb.mynewsfeed.articles.data.ArticleDataSourceFactory
import at.gleb.mynewsfeed.domain.entity.ArticleVo
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.inject

class ArticlesViewModel : ViewModel() {
    lateinit var articles: LiveData<PagedList<ArticleVo>>

    private val disposable = CompositeDisposable()
    private val dataSourceFactory: ArticleDataSourceFactory by inject(ArticleDataSourceFactory::class.java) {
        parametersOf(sourceId)
    }

    private lateinit var sourceId: String

    fun onGetSourceId(sourceId: String) {
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