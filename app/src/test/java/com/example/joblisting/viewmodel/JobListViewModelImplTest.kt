package com.example.joblisting.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.joblisting.internal.usecase.JobListingUseCase
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor

class JobListViewModelImplTest {
    private val executor = Executor { it.run() }

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var useCase: JobListingUseCase
    lateinit var viewModel: JobListViewModelImpl

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = JobListViewModelImpl(useCase, executor)
    }

    @Test
    fun `test displayNewest should call getNewestJobs`() {
        whenever(useCase.getNewestJobs()).thenReturn(emptyList())

        viewModel.displayNewest()

        verify(useCase).getNewestJobs()
    }

    @Test
    fun `test displayOldest should call getOldestJobs`() {
        whenever(useCase.getOldestJobs()).thenReturn(emptyList())

        viewModel.displayOldest()

        verify(useCase).getOldestJobs()
    }

    @Test
    fun `test displayLowestPrice should call getLowestPriceJob`() {
        whenever(useCase.getLowestPriceJobs()).thenReturn(emptyList())

        viewModel.displayLowestPrice()

        verify(useCase).getLowestPriceJobs()
    }

    @Test
    fun `test displayHighest should call getHighestPriceJob`() {
        whenever(useCase.getHighestPriceJobs()).thenReturn(emptyList())

        viewModel.displayHighestPrice()

        verify(useCase).getHighestPriceJobs()
    }
}