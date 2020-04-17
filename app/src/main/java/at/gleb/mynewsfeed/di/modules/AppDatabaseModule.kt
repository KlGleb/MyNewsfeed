package at.gleb.mynewsfeed.di.modules

import android.app.Application
import androidx.room.Room
import at.gleb.mynewsfeed.data.db.AppDatabase
import at.gleb.mynewsfeed.di.AppScope
import dagger.Module
import dagger.Provides

const val DB_NAME = "mynewsfeed"

@Module
class AppDatabaseModule {
    @AppScope
    @Provides
    fun provideDb(context: Application) =
        Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()

}
