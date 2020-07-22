package com.example.joblisting.external.pojo

//{
//    "jobs":
//    [
//        { "id":1, "origin":"Jakarta", "destination":"Surabaya", "date":1555110000, "price":4000000 },
//        { "id":2, "origin":"Malang", "destination":"Yogyakarta", "date":1555330000, "price":1500000 },
//        { "id":3, "origin":"Bandung", "destination":"Semarang", "date":1554700000, "price":3000000 },
//        { "id":4, "origin":"Aceh", "destination":"Palembang", "date":1554100000, "price":5000000 },
//        { "id":5, "origin":"Padang", "destination":"Medan", "date":1554450000, "price":5000000 }
//    ]
//}


data class JobListResponse(
    val jobs: List<JobResponse>
)

data class JobResponse(
    val id: Int,
    val origin: String,
    val destination: String,
    val date: Long,
    val price: Long
)