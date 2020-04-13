package at.gleb.mynewsfeed.domain

import at.gleb.mynewsfeed.domain.entity.SourceVo
import io.reactivex.rxjava3.core.Single

interface NewsRepository {
    fun getSources(): Single<List<SourceVo>>
}