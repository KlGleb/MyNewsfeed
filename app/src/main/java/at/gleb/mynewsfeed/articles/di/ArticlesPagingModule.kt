package at.gleb.mynewsfeed.articles.di

import at.gleb.mynewsfeed.articles.data.ArticleDataSourceFactory
import at.gleb.mynewsfeed.data.NewsApiService
import at.gleb.mynewsfeed.di.AppScope
import dagger.Module
import dagger.Provides

@Module(subcomponents = [ArticlesComponent::class])
class ArticlesPagingModule(private val sourceId: String) {
    @Provides
    @AppScope
    fun provideDataSourceFactory(api: NewsApiService): ArticleDataSourceFactory =
        ArticleDataSourceFactory(api, sourceId)
}
