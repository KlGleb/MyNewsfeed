package at.gleb.mynewsfeed.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import at.gleb.mynewsfeed.data.db.entity.SourceEntity

@Dao
interface SourceDao {
    @Query("SELECT * FROM sourceentity")
    fun getAll(): List<SourceEntity>

    @Insert
    fun insertAll(entities: List<SourceEntity>)

    @Query("DELETE FROM sourceentity")
    fun clear()

    @Transaction
    fun clearAndInsert(entities: List<SourceEntity>) {
        clear()
        insertAll(entities)
    }
}