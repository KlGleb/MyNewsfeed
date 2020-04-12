package at.gleb.mynewsfeed.data.dto

data class ResponseEverythingDto(
    val articles: List<ArticleDto>? = null
) : BaseResponseDto()