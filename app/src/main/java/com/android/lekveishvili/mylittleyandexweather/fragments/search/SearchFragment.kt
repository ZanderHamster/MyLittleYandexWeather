package com.android.lekveishvili.mylittleyandexweather.fragments.search

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
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
        val binding = DataBindingUtil.inflate<FragmentSearchBinding>(
                inflater, R.layout.fragment_search, container, false)
        val editText = binding.root.findViewById<EditText>(R.id.ed_search)
        viewModel = SearchFragmentViewModel(repository, (activity as MainActivity), { hideKeyboard() }, editText)
        binding.viewModel = viewModel

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