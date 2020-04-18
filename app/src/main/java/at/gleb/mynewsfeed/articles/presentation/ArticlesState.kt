package at.gleb.mynewsfeed.articles.presentation

import at.gleb.mynewsfeed.domain.entity.ArticleVo


sealed class ArticlesState {
    data class ShowArticles(val list: List<ArticleVo>) : ArticlesState()
    object Loading : ArticlesState()
    object Error : ArticlesState()
}