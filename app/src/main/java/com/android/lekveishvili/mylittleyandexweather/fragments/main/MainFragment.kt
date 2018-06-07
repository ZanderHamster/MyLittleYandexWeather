package com.android.lekveishvili.mylittleyandexweather.fragments.main

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.*
import com.android.lekveishvili.mylittleyandexweather.MainActivity
import com.android.lekveishvili.mylittleyandexweather.MyApplication
import com.android.lekveishvili.mylittleyandexweather.R
import com.android.lekveishvili.mylittleyandexweather.data.NetDataRepository
import com.android.lekveishvili.mylittleyandexweather.databinding.FragmentMainBinding
import com.android.lekveishvili.mylittleyandexweather.viewmodel.BindingFragment
import javax.inject.Inject

class MainFragment : BindingFragment<MainFragmentViewModel>() {
    @Inject
    lateinit var repository: NetDataRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        MyApplication.appComponent.inject(this)
        val binding = DataBindingUtil.inflate<FragmentMainBinding>(
                inflater, R.layout.fragment_main, container, false)
        val view = binding.root

        viewModel = MainFragmentViewModel(repository, (activity as MainActivity))
        viewModel.title = resources.getString(R.string.app_name)
        viewModel.list = getCitiesList()
        binding.viewModel = viewModel
        viewModel.loadData()
        setupToolbar()
        return view
    }

    private fun setupToolbar() {

    }

    private fun getCitiesList(): List<String> {
        val list: MutableList<String> = arrayListOf()
        val sharedPreferences = activity?.getSharedPreferences("PREFS", Context.MODE_PRIVATE)
        sharedPreferences?.let {
            sharedPreferences.all.forEach {
                list.add(it.value.toString())
            }
        }
        return list
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.router.openMain()
    }

}