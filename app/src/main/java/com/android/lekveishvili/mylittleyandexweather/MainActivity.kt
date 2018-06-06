package com.android.lekveishvili.mylittleyandexweather

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.android.lekveishvili.mylittleyandexweather.databinding.ActivityMainBinding
import com.android.lekveishvili.mylittleyandexweather.viewmodel.BindingActivity

class MainActivity : BindingActivity<MainActivityViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApplication.appComponent.inject(this)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        viewModel = MainActivityViewModel()
        binding.viewModel = viewModel

    }
}
