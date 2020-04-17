package at.gleb.mynewsfeed.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import at.gleb.mynewsfeed.data.db.dao.SourceDao
import at.gleb.mynewsfeed.data.db.entity.SourceEntity

@Database(entities = [SourceEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): SourceDao
}