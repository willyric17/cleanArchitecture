package com.example.joblisting.injection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import com.example.joblisting.JobListActivity
import com.example.joblisting.external.gateway.JobApiGateway
import com.example.joblisting.internal.repository.CachedJobRepository
import com.example.joblisting.internal.repository.JobRepository
import com.example.joblisting.internal.usecase.JobListingUseCase
import com.example.joblisting.internal.usecase.JobListingUseCaseImpl
import com.example.joblisting.viewmodel.JobListViewModel
import com.example.joblisting.viewmodel.JobListViewModelImpl
import retrofit2.Call
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors

object JobInjection : ViewModelStore() {

    lateinit var retrofit: Retrofit
    fun initialize() {
        retrofit = Retrofit.Builder()
            .baseUrl("http://5cac1d41c85e05001452eef0.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun inject(activity: JobListActivity) {
        activity.viewmodel =
            ViewModelProvider(this, MainViewModelFactory).get(JobListViewModel::class.java)
    }

    fun getUseCase(): JobListingUseCase {
        return JobListingUseCaseImpl(getRepository())
    }

    private val jobRepository by lazy { CachedJobRepository(getGateway()) }
    private fun getRepository(): JobRepository {
        return jobRepository
    }

    private fun getGateway(): JobApiGateway {
        return retrofit.create(JobApiGateway::class.java)
    }

    fun getRunner(): Executor {
        return Executors.newSingleThreadExecutor()
    }

    object MainViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(JobListViewModel::class.java)) {
                return JobListViewModelImpl(getUseCase(), getRunner()) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
