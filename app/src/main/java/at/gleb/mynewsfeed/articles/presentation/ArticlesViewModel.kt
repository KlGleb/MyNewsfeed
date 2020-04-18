package at.gleb.mynewsfeed.articles.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import at.gleb.mynewsfeed.domain.NewsInteractor
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import javax.inject.Inject

class ArticlesViewModel @Inject constructor(
    private val interactor: NewsInteractor
) : ViewModel() {
    private val disposable = CompositeDisposable()

    private val _articles = MutableLiveData<ArticlesState>()
    val articles: LiveData<ArticlesState> = _articles

    fun onGetSourceId(sourceId: String) {
        disposable += interactor.getArticles(sourceId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    _articles.value = ArticlesState.ShowArticles(list = it)
                },
                onError = {
                    _articles.value = ArticlesState.Error
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
        /* disposable += interactor
             .updateSources()
             .doOnSubscribe {
                 _sources.value = ArticlesState.Loading
             }
             .observeOn(AndroidSchedulers.mainThread())
             .subscribeBy(
                 onComplete = { },
                 onError = {
                     _sources.value = ArticlesState.Error
                 }
             )*/
    }
}