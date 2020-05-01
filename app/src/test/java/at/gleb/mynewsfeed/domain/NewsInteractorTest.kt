package at.gleb.mynewsfeed.domain

import at.gleb.mynewsfeed.domain.entity.SourceVo
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito


internal class NewsInteractorTest {
    private lateinit var repository: NewsRepository
    private lateinit var interactor: NewsInteractor

    @Before
    fun setUp() {
        repository = Mockito.mock(NewsRepository::class.java)

        whenever(repository.getSources()).thenReturn(
            Observable.just(
                listOf(SourceVo("1", "title", "descr", "url"))
            )
        )

        whenever(repository.updateSources()).thenReturn(Completable.complete())

        interactor = NewsInteractor(repository)
    }

    @Test
    fun `Get sources`() {
        interactor.getSources().test().apply {
            awaitCount(1)
            assertValue(listOf(SourceVo("1", "title", "descr", "url")))
            dispose()
        }

    }

    @Test
    fun `Update sources`() {
        interactor.updateSources().test().apply {
            await()
            assertComplete()
            dispose()
        }

    }
}