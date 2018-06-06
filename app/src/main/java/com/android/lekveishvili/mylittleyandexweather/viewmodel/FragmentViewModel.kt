package com.android.lekveishvili.mylittleyandexweather.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.view.View
import com.android.lekveishvili.mylittleyandexweather.BR


open class FragmentViewModel : BaseObservable() {
    var title = ""
        @Bindable get() = field
        @Bindable set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }
    var loading = View.GONE
        @Bindable get() = field
        @Bindable set(value) {
            field = value
            notifyPropertyChanged(BR.loading)
        }

    var error = View.GONE
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
