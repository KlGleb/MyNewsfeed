package at.gleb.mynewsfeed.data.dto

import com.fasterxml.jackson.annotation.JsonProperty

enum class ResponseStatus {
    @JsonProperty("ok")
    OK,
    @JsonProperty("error")
    ERROR
}