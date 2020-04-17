package at.gleb.mynewsfeed.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SourceEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val url: String
)