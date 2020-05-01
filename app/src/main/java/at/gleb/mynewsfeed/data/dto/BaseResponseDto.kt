package at.gleb.mynewsfeed.data.dto

open class BaseResponseDto(
    var status: ResponseStatus? = null,
    var code: ErrorCode? = null,
    var message: String? = null
)