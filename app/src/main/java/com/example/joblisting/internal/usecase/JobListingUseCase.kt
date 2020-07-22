package com.example.joblisting.internal.usecase

import com.example.joblisting.external.pojo.JobResponse
import com.example.joblisting.internal.entity.JobEntity
import com.example.joblisting.internal.repository.JobRepository
import com.example.joblisting.internal.usecase.JobMapper.toEntity

interface JobListingUseCase {
    fun getNewestJobs(): List<JobEntity>
    fun getOldestJobs(): List<JobEntity>
    fun getLowestPriceJobs(): List<JobEntity>
    fun getHighestPriceJobs(): List<JobEntity>
}

object JobMapper {
    fun JobResponse.toEntity() = JobEntity(
        id = id,
        price = price,
        origin = origin,
        destination = destination,
        date = date
    )
}

class JobListingUseCaseImpl(
    private val repository: JobRepository
) : JobListingUseCase {
    override fun getNewestJobs(): List<JobEntity> {
        return getData().sortedBy { it.date }
    }

    override fun getOldestJobs(): List<JobEntity> {
        return getData().sortedByDescending { it.date }
    }

    override fun getLowestPriceJobs(): List<JobEntity> {
        return getData().sortedBy { it.price }
    }

    override fun getHighestPriceJobs(): List<JobEntity> {
        return getData().sortedByDescending { it.price }
    }

    private fun getData() = repository.getJobList().map { it.toEntity() }

}
