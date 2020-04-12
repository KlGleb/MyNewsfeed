package at.gleb.mynewsfeed.data.dto

data class ResponseSourcesDto(
    val sources: List<SourceDto>? = null
) : BaseResponseDto()