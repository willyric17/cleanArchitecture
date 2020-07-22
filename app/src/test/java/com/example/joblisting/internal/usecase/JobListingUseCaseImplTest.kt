package com.example.joblisting.internal.usecase

import com.example.joblisting.external.pojo.JobResponse
import com.example.joblisting.internal.repository.JobRepository
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Assert
import org.junit.Before

import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.random.Random

class JobListingUseCaseImplTest {

    lateinit var usecase: JobListingUseCaseImpl

    @Mock
    lateinit var repository: JobRepository

    val response = Array(Random.nextInt(100)) {
        JobResponse(
            id = it,
            date = Random.nextLong(0, Long.MAX_VALUE),
            destination = "D$it",
            origin = "O$it",
            price = Random.nextLong(0, Long.MAX_VALUE)
        )
    }.toList()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        usecase = JobListingUseCaseImpl(repository)

        whenever(repository.getJobList()).thenReturn(response)
    }

    @Test
    fun `test getNewestJobs should return job sorted by date`() {
        val result = usecase.getNewestJobs()

        var currentDate = Long.MIN_VALUE
        result.forEach {
            Assert.assertTrue(currentDate <= it.date)
            currentDate = it.date
        }
    }

    @Test
    fun `test getOldestJobs should return job sorted by date descending`() {
        val result = usecase.getOldestJobs()

        var currentDate = Long.MAX_VALUE
        result.forEach {
            Assert.assertTrue(currentDate >= it.date)
            currentDate = it.date
        }
    }

    @Test
    fun `test getLowestPrice should return job sorted by price ascending`() {
        val result = usecase.getLowestPriceJobs()

        var currentDate = Long.MIN_VALUE
        result.forEach {
            Assert.assertTrue(currentDate <= it.price)
            currentDate = it.price
        }
    }

    @Test
    fun `test getHighestPrice should return job sorted by price descending`() {
        val result = usecase.getHighestPriceJobs()

        var currentDate = Long.MAX_VALUE
        result.forEach {
            Assert.assertTrue(currentDate >= it.price)
            currentDate = it.price
        }
    }


}