package com.example.sriram

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sriram.databinding.FragmentFirstBinding
import com.google.gson.Gson

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private var viewModel:MainActivityViewModel?=null

    private var _binding: FragmentFirstBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            androidx.appcompat.R.id.home -> {
                findNavController().popBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel?.let { vm ->

           binding.textInputValue.apply {
                setText(viewModel?.inputValue)
            }.doAfterTextChanged {
                vm.inputValue =it?.toString()?.trim()
            }

            vm.viewData.observe(viewLifecycleOwner){
                val mPrefs: SharedPreferences =
                    requireActivity().getSharedPreferences("Data", Context.MODE_PRIVATE)
                val prefsEditor = mPrefs.edit()
                val gson = Gson()
                val json = gson.toJson(it)
                prefsEditor.putString("weatherData", json)
                prefsEditor.apply()
                it?.apply {
                    findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment,savedInstanceState)
                }

            }
            binding.buttonFirst.apply {
                setOnClickListener {
                    vm.makeServiceCall()
                }
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
