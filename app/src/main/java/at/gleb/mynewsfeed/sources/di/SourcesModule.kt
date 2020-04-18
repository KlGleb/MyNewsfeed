package at.gleb.mynewsfeed.sources.di

import androidx.lifecycle.ViewModel
import at.gleb.mynewsfeed.di.modules.ViewModelKey
import at.gleb.mynewsfeed.sources.presentation.SourcesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SourcesModule {
    @Binds
    @IntoMap
    @ViewModelKey(SourcesViewModel::class)
    internal abstract fun sourcesViewModel(viewModel: SourcesViewModel): ViewModel
}