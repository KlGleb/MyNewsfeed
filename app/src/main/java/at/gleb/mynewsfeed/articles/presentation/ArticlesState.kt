package at.gleb.mynewsfeed.articles.presentation

import androidx.paging.PagedList
import at.gleb.mynewsfeed.domain.entity.ArticleVo


sealed class ArticlesState {
    data class ShowArticles(val list: PagedList<ArticleVo>) : ArticlesState()
    object Loading : ArticlesState()
    object Error : ArticlesState()
}