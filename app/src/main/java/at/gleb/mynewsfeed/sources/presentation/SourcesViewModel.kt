package at.gleb.mynewsfeed.sources.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import at.gleb.mynewsfeed.domain.NewsInteractor
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

class SourcesViewModel @Inject constructor(
    private val interactor: NewsInteractor
) : ViewModel() {
    private val disposable = CompositeDisposable()

    private val _sources = MutableLiveData<SourcesState>()
    val sources: LiveData<SourcesState> = _sources

    init {
        disposable += interactor.getSources()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    _sources.value = SourcesState.ShowSources(states = it)
                },
                onError = {
                    _sources.value = SourcesState.Error
                }
            )

        updateData()
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

    fun onRefresh() {
        updateData()
    }

    private fun updateData() {
        disposable += interactor
            .updateSources()
            .doOnSubscribe {
                _sources.value = SourcesState.Loading
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = { },
                onError = {
                    _sources.value = SourcesState.Error
                }
            )
    }
}