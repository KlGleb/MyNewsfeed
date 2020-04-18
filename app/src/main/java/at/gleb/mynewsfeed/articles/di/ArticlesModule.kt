package at.gleb.mynewsfeed.articles.di

import androidx.lifecycle.ViewModel
import at.gleb.mynewsfeed.articles.presentation.ArticlesViewModel
import at.gleb.mynewsfeed.di.modules.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ArticlesModule {
    @Binds
    @IntoMap
    @ViewModelKey(ArticlesViewModel::class)
    internal abstract fun articlesViewModel(viewModel: ArticlesViewModel): ViewModel
}
