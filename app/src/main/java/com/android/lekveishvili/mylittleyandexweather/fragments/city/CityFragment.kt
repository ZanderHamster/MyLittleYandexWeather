package com.android.lekveishvili.mylittleyandexweather.fragments.city

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.lekveishvili.mylittleyandexweather.MainActivity
import com.android.lekveishvili.mylittleyandexweather.MainActivity.Companion.CITY_ID
import com.android.lekveishvili.mylittleyandexweather.MyApplication
import com.android.lekveishvili.mylittleyandexweather.R
import com.android.lekveishvili.mylittleyandexweather.data.NetDataRepository
import com.android.lekveishvili.mylittleyandexweather.databinding.FragmentCityBinding
import com.android.lekveishvili.mylittleyandexweather.viewmodel.BindingFragment
import javax.inject.Inject

class CityFragment : BindingFragment<CityFragmentViewModel>() {
    @Inject
    lateinit var repository: NetDataRepository
    private var cityId = ""
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        MyApplication.appComponent.inject(this)
        cityId = arguments?.getString(CITY_ID)!!
        viewModel = CityFragmentViewModel(repository, { removeCity(cityId) })
        val binding = DataBindingUtil.inflate<FragmentCityBinding>(
                inflater, R.layout.fragment_city, container, false)
        binding.viewModel = viewModel
        viewModel.cityId = cityId
        viewModel.loadData()
        setupToolbar(binding.root.findViewById(R.id.toolbar))

        return binding.root
    }

    private fun setupToolbar(toolbar: Toolbar) {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener {
            (activity as MainActivity).openMain()
        }
    }

    fun removeCity(id: String) {
        (activity as MainActivity).removeCity(id)
    }
}