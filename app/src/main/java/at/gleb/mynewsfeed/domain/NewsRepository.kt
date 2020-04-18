package at.gleb.mynewsfeed.domain

import at.gleb.mynewsfeed.domain.entity.ArticleVo
import at.gleb.mynewsfeed.domain.entity.SourceVo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface NewsRepository {
    fun getSources(): Observable<List<SourceVo>>
    fun updateSources(): Completable
    fun getArticles(sources: List<String>): Observable<List<ArticleVo>>
}