package at.gleb.mynewsfeed.domain


class NewsInteractor(private val repository: NewsRepository) {
    fun getSources() = repository.getSources()

    fun updateSources() = repository.updateSources()

    fun getArticles(source: String) = repository.getArticles(sources = listOf(source))

}