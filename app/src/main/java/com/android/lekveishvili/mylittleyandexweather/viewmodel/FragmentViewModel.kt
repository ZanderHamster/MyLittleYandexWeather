package com.android.lekveishvili.mylittleyandexweather.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.android.lekveishvili.mylittleyandexweather.BR


open class FragmentViewModel : BaseObservable() {
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

    open fun onActivityCreated() {}
    open fun onResume() {}
    open fun onDestroy() {}
    open fun onPause() {}
}
