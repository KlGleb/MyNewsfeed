package at.gleb.mynewsfeed.sources.presentation

sealed class NavigationState {
    data class Articles(val articleId: String) : NavigationState()
}