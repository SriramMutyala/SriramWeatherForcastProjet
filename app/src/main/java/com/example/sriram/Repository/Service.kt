package com.example.kotlinmvvmjson.Repository

import com.example.sriram.Model.DataModel
import retrofit2.Call
import retrofit2.http.*

interface Service {
    @GET("data/2.5/forecast")
   fun getData(@HeaderMap request:Map<String,String>,@QueryMap data:Map<String,String>  ): Call<DataModel>
}
