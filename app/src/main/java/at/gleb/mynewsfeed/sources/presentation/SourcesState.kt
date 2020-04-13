package at.gleb.mynewsfeed.sources.presentation

import at.gleb.mynewsfeed.domain.entity.SourceVo


sealed class SourcesState {
    data class ShowSources(val states: List<SourceVo>) : SourcesState()
    object Loading : SourcesState()
    object Error : SourcesState()
}