package com.example.joblisting.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.joblisting.internal.entity.JobEntity
import com.example.joblisting.internal.usecase.JobListingUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import java.util.concurrent.Executor

abstract class JobListViewModel : ViewModel() {
    abstract fun getJobsLiveData(): LiveData<List<JobEntity>>
    abstract fun displayNewest()
    abstract fun displayOldest()
    abstract fun displayLowestPrice()
    abstract fun displayHighestPrice()
}

class JobListViewModelImpl(
    private val useCase: JobListingUseCase,
    runner: Executor
) : JobListViewModel(), CoroutineScope by CoroutineScope(runner.asCoroutineDispatcher()) {
    private val jobs = MutableLiveData<List<JobEntity>>().apply {
        value = emptyList()
    }

    override fun getJobsLiveData(): LiveData<List<JobEntity>> = jobs

    override fun displayNewest() {
        launch {
            jobs.postValue(useCase.getNewestJobs())
        }
    }

    override fun displayOldest() {
        launch {
            jobs.postValue(useCase.getOldestJobs())
        }
    }

    override fun displayLowestPrice() {
        launch {
            jobs.postValue(useCase.getLowestPriceJobs())
        }
    }

    override fun displayHighestPrice() {
        launch {
            jobs.postValue(useCase.getHighestPriceJobs())
        }
    }

}