package at.gleb.mynewsfeed.data.dto

data class SourceDto(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val url: String? = null,
    val category: String? = null,
    val language: String? = null,
    val country: String? = null
)