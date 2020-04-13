package at.gleb.mynewsfeed.di.modules

import at.gleb.mynewsfeed.data.NewsRepositoryImpl
import at.gleb.mynewsfeed.di.AppScope
import at.gleb.mynewsfeed.domain.NewsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class MyNewsfeedModule {
    @Binds
    @AppScope
    internal abstract fun bindRepository(repositoryImpl: NewsRepositoryImpl): NewsRepository
}