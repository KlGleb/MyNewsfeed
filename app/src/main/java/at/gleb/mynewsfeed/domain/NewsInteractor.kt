package at.gleb.mynewsfeed.domain

import javax.inject.Inject

class NewsInteractor @Inject constructor(private val repository: NewsRepository) {
    fun getSources() = repository.getSources()

    fun updateSources() = repository.updateSources()

    fun getArticles(source: String) = repository.getArticles(sources = listOf(source))

}