package com.example.sriram

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sriram.Model.DataModel
import com.example.kotlinmvvmjson.Repository.Service
import com.example.sriram.Destination.Common
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {
    private val apiService: Service = Common.apiService
    private val _viewData = MutableLiveData<DataModel>()
    val viewData: LiveData<DataModel> = _viewData

    var inputValue: String? = null
        set(value) {
            field = value
        }

    fun makeServiceCall() {
        if (!inputValue.isNullOrEmpty()) {


            val headers = HashMap<String, String>()
            headers["Content-Type"] = "application/json"

            val queryParams = mutableMapOf(
                "q" to inputValue.orEmpty(),
                "appid" to "65d00499677e59496ca2f318eb68c049"
            )

            apiService.getData(headers, queryParams).enqueue(object : Callback<DataModel> {

                override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                    if (response.isSuccessful) {
                        _viewData.value = response.body()
                    } else {
                        _viewData.value = null
                    }
                }


                override fun onFailure(call: Call<DataModel>, t: Throwable) {
                    _viewData.value = null
                }

            })
        }
    }

    fun navigateToTempList() {

    }
}
