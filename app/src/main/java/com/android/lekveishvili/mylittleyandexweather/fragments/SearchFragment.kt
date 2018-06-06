package com.android.lekveishvili.mylittleyandexweather.fragments

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.lekveishvili.mylittleyandexweather.MainActivity
import com.android.lekveishvili.mylittleyandexweather.MyApplication
import com.android.lekveishvili.mylittleyandexweather.R
import com.android.lekveishvili.mylittleyandexweather.data.NetDataRepository
import com.android.lekveishvili.mylittleyandexweather.databinding.FragmentSearchBinding
import com.android.lekveishvili.mylittleyandexweather.viewmodel.BindingFragment
import javax.inject.Inject

class SearchFragment : BindingFragment<SearchFragmentViewModel>() {
    @Inject
    lateinit var repository: NetDataRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        MyApplication.appComponent.inject(this)
        viewModel = SearchFragmentViewModel(repository, (activity as MainActivity), { hideKeyboard() })
        val binding = DataBindingUtil.inflate<FragmentSearchBinding>(
                inflater, R.layout.fragment_search, container, false)
        binding.viewModel = viewModel
        viewModel.loadData()

        return binding.root
    }

    private fun hideKeyboard() {
        (activity as MainActivity).hideKeyboard()
    }

    override fun onDestroy() {
        hideKeyboard()
        super.onDestroy()
    }

}