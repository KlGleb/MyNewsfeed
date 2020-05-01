package at.gleb.mynewsfeed.domain.entity

import java.util.*

data class ArticleVo(
    val title: String,
    val description: String,
    val thumbnail: String,
    val author: String,
    val publishedDate: Date,
    val url: String?
)