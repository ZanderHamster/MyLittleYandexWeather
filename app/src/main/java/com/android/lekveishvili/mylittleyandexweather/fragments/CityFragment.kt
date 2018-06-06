package com.android.lekveishvili.mylittleyandexweather.fragments

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.lekveishvili.mylittleyandexweather.MyApplication
import com.android.lekveishvili.mylittleyandexweather.R
import com.android.lekveishvili.mylittleyandexweather.data.NetDataRepository
import com.android.lekveishvili.mylittleyandexweather.databinding.FragmentCityBinding
import com.android.lekveishvili.mylittleyandexweather.fragments.MainFragmentViewModel.Companion.MOSCOW
import com.android.lekveishvili.mylittleyandexweather.viewmodel.BindingFragment
import javax.inject.Inject

class CityFragment : BindingFragment<CityFragmentViewModel>() {
    @Inject
    lateinit var repository: NetDataRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        MyApplication.appComponent.inject(this)
        viewModel = CityFragmentViewModel(repository)
        val binding = DataBindingUtil.inflate<FragmentCityBinding>(
                inflater, R.layout.fragment_city, container, false)
        binding.viewModel = viewModel
        viewModel.loadData(MOSCOW)
        return binding.root

    }
}