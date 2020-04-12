package at.gleb.mynewsfeed.data.dto

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue
import com.fasterxml.jackson.annotation.JsonProperty

enum class ErrorCode {
    @JsonProperty("apiKeyDisabled")
    API_KEY_DISABLED,
    @JsonProperty("apiKeyExhausted")
    API_KEY_EXHAUSTED,
    @JsonProperty("apiKeyInvalid")
    API_KEY_INVALID,
    @JsonProperty("apiKeyMissing")
    API_KEY_MISSING,
    @JsonProperty("parameterInvalid")
    PARAMETER_INVALID,
    @JsonProperty("parametersMissing")
    PARAMETERS_MISSING,
    @JsonProperty("rateLimited")
    RATE_LIMITED,
    @JsonProperty("sourcesTooMany")
    SOURCES_TOO_MANY,
    @JsonProperty("sourceDoesNotExist")
    SOURCE_DOES_NOT_EXIST,
    @JsonProperty("unexpectedError")
    @JsonEnumDefaultValue
    UNEXPECTED_ERROR
}