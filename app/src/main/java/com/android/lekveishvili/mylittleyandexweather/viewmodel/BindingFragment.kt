package com.android.lekveishvili.mylittleyandexweather.viewmodel

import android.os.Bundle
import android.support.v4.app.Fragment

abstract class BindingFragment<VM : FragmentViewModel> : Fragment() {

    lateinit var viewModel: VM

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.onActivityCreated()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

}