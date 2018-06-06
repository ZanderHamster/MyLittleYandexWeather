package com.android.lekveishvili.mylittleyandexweather.fragments

import android.databinding.Bindable
import com.android.lekveishvili.mylittleyandexweather.BR
import com.android.lekveishvili.mylittleyandexweather.data.NetDataRepository
import com.android.lekveishvili.mylittleyandexweather.viewmodel.FragmentViewModel
import com.android.lekveishvili.tempToString
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CityFragmentViewModel(private val repository: NetDataRepository)
    : FragmentViewModel() {

//    val threeDays: List<WeatherFiveDaysRequest.WeatherFiveDayItem> = emptyList()
//    getTempString(threeDays[0].main.temp)

    var tempDay1 = ""
        @Bindable get() = field
        @Bindable set(value) {
            field = value
            notifyPropertyChanged(BR.tempDay1)
        }

    var tempDay2 = ""
        @Bindable get() = field
        @Bindable set(value) {
            field = value
            notifyPropertyChanged(BR.tempDay2)
        }

    var tempDay3 = ""
        @Bindable get() = field
        @Bindable set(value) {
            field = value
            notifyPropertyChanged(BR.tempDay3)
        }


    fun loadData(cityId: String) {
        repository.getWeatherForFiveDays(cityId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    error = true
                }
                .doOnSubscribe({
                    error = false
                })
                .subscribe({
                    tempDay1 = it.getFirstDay().main.temp.tempToString()
                    tempDay2 = it.getSecondDay().main.temp.tempToString()
                    tempDay3 = it.getThirdDay().main.temp.tempToString()
                }) {}
    }
}
