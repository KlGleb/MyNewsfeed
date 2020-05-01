package at.gleb.mynewsfeed.data

import at.gleb.mynewsfeed.data.db.dao.SourceDao
import at.gleb.mynewsfeed.data.db.entity.SourceEntity
import at.gleb.mynewsfeed.data.dto.ResponseSourcesDto
import at.gleb.mynewsfeed.data.dto.ResponseStatus
import at.gleb.mynewsfeed.data.dto.SourceDto
import at.gleb.mynewsfeed.domain.entity.SourceVo
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.util.concurrent.TimeUnit


internal class NewsRepositoryImplTest {
    private lateinit var repositoryImpl: NewsRepositoryImpl

    private val testSources: List<SourceDto> = arrayListOf(
        SourceDto(
            id = "1",
            name = "title",
            description = "description",
            url = "url",
            category = "category",
            language = "lang",
            country = "country"
        ),
        SourceDto(id = "2"),
        SourceDto(
            id = "3",
            name = "title",
            description = "description"
        )
    )

    private val testSourceEntities: List<SourceEntity> = arrayListOf(
        SourceEntity(id = "1", title = "title", description = "description", url = "url"),
        SourceEntity(id = "2", title = "title", description = "description", url = "url"),
        SourceEntity(id = "3", title = "title", description = "description", url = "url")
    )

    @Before
    fun before() {
        val apiService = Mockito.mock(NewsApiService::class.java)
        whenever(apiService.getSources(any())).thenReturn(
            Single.just(
                ResponseSourcesDto(testSources).apply {
                    status = ResponseStatus.OK
                }
            )
        )

        val sourceDao: SourceDao = Mockito.mock(SourceDao::class.java)

        whenever(sourceDao.getAll()).thenReturn(testSourceEntities)

        repositoryImpl = NewsRepositoryImpl(apiService, sourceDao)
    }

    @Test
    fun `getSources() from cache`() {
        val testObserver = repositoryImpl.getSources().test()
        testObserver.await(1000, TimeUnit.MILLISECONDS)

        testObserver.assertValues(
            listOf(
                SourceVo("1", "title", "description", "url"),
                SourceVo("2", "title", "description", "url"),
                SourceVo("3", "title", "description", "url")
            )
        )
    }


    @Test
    fun `getSources() from cache`() {

    }
}