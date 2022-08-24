package com.example.sriram

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.sriram.Model.DetailsModel
import com.google.gson.Gson

class WeatherDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.weather_view_details, container, false)
        configureView(view)
        return view
    }

    private fun configureView(view: View) {
        val mPrefs: SharedPreferences =
            requireActivity().getSharedPreferences("details", Context.MODE_PRIVATE)
        val json = mPrefs.getString("climateDetails", "")
        val data = Gson().fromJson(json, DetailsModel::class.java)
        view.apply {
            val feels = data.feelsLikeText
            findViewById<TextView>(R.id.temp_text).text = data.temperature
            findViewById<TextView>(R.id.feels_like_text).text = "Fells like : $feels"
            findViewById<TextView>(R.id.weather_type).text = data.weatherType
            findViewById<TextView>(R.id.weather_type_desc).text = data.weatherTypeDesc
        }

    }
}
