package at.gleb.mynewsfeed.articles.data

import at.gleb.mynewsfeed.data.dto.ArticleDto
import at.gleb.mynewsfeed.domain.entity.ArticleVo
import java.util.*

fun List<ArticleDto>?.toArticleVos() = this?.map { articleDto ->
    ArticleVo(
        title = articleDto.title.orEmpty(),
        description = articleDto.description.orEmpty(),
        thumbnail = articleDto.urlToImage.orEmpty(),
        author = articleDto.author.orEmpty(),
        publishedDate = articleDto.publishedAt ?: Date(0)
    )
} ?: arrayListOf()