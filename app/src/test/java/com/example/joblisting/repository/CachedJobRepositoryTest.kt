package com.example.joblisting.repository

import com.example.joblisting.external.gateway.JobApiGateway
import com.example.joblisting.external.pojo.JobListResponse
import com.example.joblisting.external.pojo.JobResponse
import com.example.joblisting.internal.repository.CachedJobRepository
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test

class CachedJobRepositoryTest {

    lateinit var cachedJobRepository: CachedJobRepository
    lateinit var jobApiGateway: MockJobApiGateway

    @Before
    fun setUp() {
        jobApiGateway = MockJobApiGateway()
        cachedJobRepository = CachedJobRepository(jobApiGateway)
    }

    @Test
    fun `test getJobList should return data from gateway`() {
        val result = cachedJobRepository.getJobList()

        jobApiGateway.response.jobs.forEachIndexed { index, jobResponse ->
            assertEquals(jobResponse, result[index])
        }
    }

    @Test
    fun `test getJobList should only get data from API once (cached)`() {
        repeat(10) {
            cachedJobRepository.getJobList()
        }

        assertEquals(1, jobApiGateway.callcount)
    }

}

class MockJobApiGateway : JobApiGateway {
    val response = JobListResponse(
        Array(5) {
            JobResponse(
                id = it,
                origin = "s$it",
                destination = "d$it",
                date = it.toLong(),
                price = it.toLong()
            )
        }.toList()
    )

    var callcount = 0
    override fun getJobList(): JobListResponse {
        callcount++

        return response
    }
}