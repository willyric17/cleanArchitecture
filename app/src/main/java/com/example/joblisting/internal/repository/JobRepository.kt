package com.example.joblisting.internal.repository

import com.example.joblisting.external.gateway.JobApiGateway
import com.example.joblisting.external.pojo.JobResponse
import java.lang.Exception

interface JobRepository {
    fun getJobList(): List<JobResponse>
}

class CachedJobRepository(
    private val gateway: JobApiGateway
) : JobRepository {

    var cachedResponse: List<JobResponse>? = null

    override fun getJobList(): List<JobResponse> {
        if (cachedResponse == null) {
            try {
                cachedResponse = gateway.getJobList().execute().body()!!.jobs
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return cachedResponse ?: emptyList()
    }
}