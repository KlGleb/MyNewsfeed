package at.gleb.mynewsfeed.articles.data

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import at.gleb.mynewsfeed.data.NewsApiService
import at.gleb.mynewsfeed.domain.entity.ArticleVo
import javax.inject.Inject

class ArticleDataSourceFactory @Inject constructor(
    private val api: NewsApiService,
    private val sourceId: String
) : DataSource.Factory<Int, ArticleVo>() {
    val sourceLiveData = MutableLiveData<ArticlesDataSource>()
    override fun create(): DataSource<Int, ArticleVo> {
        val source = ArticlesDataSource(api, sourceId)
        sourceLiveData.postValue(source)
        return source
    }
}