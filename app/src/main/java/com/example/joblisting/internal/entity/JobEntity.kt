package com.example.joblisting.internal.entity

data class JobEntity(
    val id: Int,
    val origin: String,
    val destination: String,
    val date: Long,
    val price: Long
)