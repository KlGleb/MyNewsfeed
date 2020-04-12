package at.gleb.mynewsfeed.data.dto

open class BaseResponseDto(
    val status: ResponseStatus? = null,
    val code: ErrorCode? = null,
    val message: String? = null
)