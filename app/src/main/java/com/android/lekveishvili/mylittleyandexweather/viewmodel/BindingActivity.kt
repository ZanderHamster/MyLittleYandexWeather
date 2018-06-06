package com.android.lekveishvili.mylittleyandexweather.viewmodel

import android.support.v7.app.AppCompatActivity


abstract class BindingActivity<VM : ActivityViewModel> : AppCompatActivity() {
    protected lateinit var viewModel: VM

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

    public override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }


    public override fun onPause() {
        super.onPause()
        viewModel.onPause()
    }
}
