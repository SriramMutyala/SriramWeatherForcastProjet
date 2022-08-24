package com.example.sriram

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sriram.Model.DataModel
import com.example.sriram.Model.DetailsModel
import com.example.sriram.Model.ItemList
import com.example.sriram.databinding.FragmentSecondBinding
import com.google.gson.Gson

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mPrefs: SharedPreferences =
            requireActivity().getSharedPreferences("Data", Context.MODE_PRIVATE)
        val json = mPrefs.getString("weatherData", "")
        val response = Gson().fromJson(json, DataModel::class.java)
        (requireActivity() as? AppCompatActivity)?.supportActionBar?.title = response.city?.name
        response?.list?.filterNotNull()?.let { list->
            val recyclerView = view.findViewById<RecyclerView>(R.id.weather_item_list)
            val manager =LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            val recyclerViewAdapter = WeatherItemListAdapter(list) { item: ItemList ->
                onAdapterRedesignItemClicked(item)
            }
            recyclerView.apply {
                layoutManager =manager
                adapter =recyclerViewAdapter
            }
        }

    }
    private fun onAdapterRedesignItemClicked(itemList: ItemList){
        val detailsModel = DetailsModel(
            temperature = itemList.main?.temp,
            feelsLikeText = itemList.main?.feels_like,
            weatherType = itemList.weather?.get(0)?.main,
            weatherTypeDesc = itemList.weather?.get(0)?.description
        )
        val mPrefs: SharedPreferences =
            requireActivity().getSharedPreferences("details", Context.MODE_PRIVATE)
        val prefsEditor = mPrefs.edit()
        val gson = Gson()
        val json = gson.toJson(detailsModel)
        prefsEditor.putString("climateDetails", json)
        prefsEditor.apply()
        findNavController().navigate(R.id.action_SecondFragment_to_WeatherDetailsFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
