package at.gleb.mynewsfeed.articles.di

import at.gleb.mynewsfeed.articles.presentation.ArticlesViewModel
import dagger.Subcomponent

@Subcomponent(modules = [ArticlesPagingModule::class])
interface ArticlesComponent {
    fun inject(articlesViewModel: ArticlesViewModel)

    @Subcomponent.Builder
    interface Builder {
        fun articlesPagingModule(sourcesModule: ArticlesPagingModule): Builder
        fun build(): ArticlesComponent
    }
}