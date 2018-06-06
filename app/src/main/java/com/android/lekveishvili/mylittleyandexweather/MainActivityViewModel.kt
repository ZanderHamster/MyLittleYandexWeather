package com.android.lekveishvili.mylittleyandexweather

import com.android.lekveishvili.mylittleyandexweather.data.NetDataRepository
import com.android.lekveishvili.mylittleyandexweather.viewmodel.ActivityViewModel

class MainActivityViewModel(private val repository: NetDataRepository,
                            private val router: Router)
    : ActivityViewModel() {

    init {
        router.openMain()
    }
}