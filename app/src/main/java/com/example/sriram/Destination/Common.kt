package com.example.sriram.Destination

import com.example.kotlinmvvmjson.Network.RetrofitService
import com.example.kotlinmvvmjson.Repository.Service

object Common {
    private const val BASE_URL ="https://api.openweathermap.org/"

    val apiService: Service
    get() = RetrofitService.getRetrofitClient(BASE_URL).create(Service::class.java)
}
