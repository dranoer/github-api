package com.dranoer.abnamro

import android.content.Context
import com.dranoer.abnamro.domain.RepoRepository
import com.dranoer.abnamro.ui.MainViewModel
import com.dranoer.abnamro.ui.util.ViewState
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.hamcrest.CoreMatchers.`is`
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dranoer.abnamro.data.local.RepoDao
import com.dranoer.abnamro.data.remote.WebService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var webService: WebService

    @Mock
    private lateinit var dao: RepoDao

    @Mock
    private lateinit var repository: RepoRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun `When response is okay THEN should return success`() {
        testCoroutineRule.runBlockingTest {
            `when`(webService.getRepoList(1, 10)).thenReturn(emptyList())
            `when`(dao.getRepos()).thenReturn(emptyList())
        }

        val repository = RepoRepository(webService, dao)

        testCoroutineRule.pauseDispatcher()
        val viewModel = MainViewModel(repository)
        MatcherAssert.assertThat(viewModel.stateFlow.value, `is`(ViewState.Loading))
        testCoroutineRule.resumeDispatcher()
        MatcherAssert.assertThat(viewModel.stateFlow.value, `is`(ViewState.Success(emptyList())))
    }

    @Test
    fun `WHEN response is not okay THEN should return error`() {
        testCoroutineRule.runBlockingTest {
            `when`(repository.result).thenAnswer {
                flowOf(
                    ViewState.Loading,
                    ViewState.Error("error")
                )
            }
        }
        testCoroutineRule.pauseDispatcher()
        val viewModel = MainViewModel(repository)
        MatcherAssert.assertThat(viewModel.stateFlow.value, `is`(ViewState.Loading))
        testCoroutineRule.resumeDispatcher()
        MatcherAssert.assertThat(viewModel.stateFlow.value, `is`(ViewState.Error("error")))
    }
}