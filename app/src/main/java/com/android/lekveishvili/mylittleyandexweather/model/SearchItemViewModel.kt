package com.android.lekveishvili.mylittleyandexweather.model

import android.databinding.BaseObservable
import android.databinding.Bindable

class SearchItemViewModel(@Bindable val value: String,
                          val addToList: () -> Unit
) : BaseObservable()
