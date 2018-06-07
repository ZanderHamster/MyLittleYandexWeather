package com.android.lekveishvili.mylittleyandexweather

import com.android.lekveishvili.mylittleyandexweather.viewmodel.ActivityViewModel

class MainActivityViewModel(private val router: Router)
    : ActivityViewModel() {

    init {
        router.openMain()
    }
}