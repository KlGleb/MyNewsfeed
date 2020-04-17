package at.gleb.mynewsfeed.data

import at.gleb.mynewsfeed.data.db.entity.SourceEntity
import at.gleb.mynewsfeed.domain.entity.SourceVo

fun List<SourceEntity>.toVoList() = map {
    it.toSourceVo()
}

fun SourceEntity.toSourceVo(): SourceVo = SourceVo(
    id = id,
    title = title,
    description = description,
    url = url
)