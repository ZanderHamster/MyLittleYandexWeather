package com.android.lekveishvili.mylittleyandexweather.model

import android.databinding.BaseObservable
import android.databinding.Bindable

class CityItemViewModel(@Bindable val name: String = "",
                        @Bindable val description: String = "",
                        @Bindable val temp: String,
                        @Bindable val id: Int,
                        @Bindable val openCity: () -> Unit
) : BaseObservable()