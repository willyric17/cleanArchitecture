package com.example.joblisting.external.gateway

import com.example.joblisting.external.pojo.JobListResponse
import retrofit2.Call
import retrofit2.http.GET

interface JobApiGateway {
    @GET("jobs")
    fun getJobList(): Call<JobListResponse>
}