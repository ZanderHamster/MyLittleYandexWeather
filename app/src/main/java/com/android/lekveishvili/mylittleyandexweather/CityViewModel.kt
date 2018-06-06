package com.android.lekveishvili.mylittleyandexweather

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.android.lekveishvili.mylittleyandexweather.model.WeatherFiveDaysRequest
import com.android.lekveishvili.tempToString

class CityViewModel(@Bindable val name: String = "",
                    @Bindable val description: String = "",
                    val threeDays: List<WeatherFiveDaysRequest.WeatherFiveDayItem>,
                    @Bindable val openCity: () -> Unit
) : BaseObservable() {

    var tempDay1 = ""
        @Bindable get() = threeDays[0].main.temp.tempToString()
        @Bindable set(value) {
            field = value
            notifyPropertyChanged(BR.tempDay1)
        }

    var tempDay2 = ""
        @Bindable get() = threeDays[1].main.temp.tempToString()
        @Bindable set(value) {
            field = value
            notifyPropertyChanged(BR.tempDay2)
        }

    var tempDay3 = ""
        @Bindable get() = threeDays[2].main.temp.tempToString()
        @Bindable set(value) {
            field = value
            notifyPropertyChanged(BR.tempDay3)
        }


}