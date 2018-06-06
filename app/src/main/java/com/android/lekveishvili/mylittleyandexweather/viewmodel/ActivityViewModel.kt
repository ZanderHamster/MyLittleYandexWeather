package com.android.lekveishvili.mylittleyandexweather.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.android.lekveishvili.mylittleyandexweather.BR

open class ActivityViewModel : BaseObservable() {

    var title = ""
        @Bindable get() = field
        @Bindable set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }

    var loading = false
        @Bindable get() = field
        @Bindable set(value) {
            field = value
            notifyPropertyChanged(BR.loading)
        }

    var error = false
        @Bindable get() = field
        @Bindable set(value) {
            field = value
            notifyPropertyChanged(BR.error)
        }

    open fun onResume() {}
    open fun onDestroy() {}
    open fun onPause() {}
}

