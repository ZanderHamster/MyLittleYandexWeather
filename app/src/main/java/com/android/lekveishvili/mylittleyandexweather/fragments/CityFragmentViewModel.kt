package com.android.lekveishvili.mylittleyandexweather.fragments

import android.databinding.Bindable
import android.view.View
import com.android.lekveishvili.mylittleyandexweather.BR
import com.android.lekveishvili.mylittleyandexweather.data.NetDataRepository
import com.android.lekveishvili.mylittleyandexweather.viewmodel.FragmentViewModel
import com.android.lekveishvili.mylittleyandexweather.tempToString
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

class CityFragmentViewModel(private val repository: NetDataRepository)
    : FragmentViewModel() {

    //    val threeDays: List<WeatherFiveDaysRequest.WeatherFiveDayItem> = emptyList()
//    getTempString(threeDays[0].main.temp)

    var cityId = ""
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

    var day3Name = ""
        @Bindable get() = field
        @Bindable set(value) {
            field = value
            notifyPropertyChanged(BR.day3Name)
        }


    fun loadData() {
        if (cityId == null) {
            error = View.VISIBLE
        } else {
            repository.getWeatherForFiveDays(cityId)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        loading = View.GONE
                        error = View.VISIBLE
                    }
                    .doOnSubscribe({
                        loading = View.VISIBLE
                        error = View.GONE
                    })
                    .subscribe({
                        loading = View.GONE
                        title = it.city.name
                        tempDay1 = it.getFirstDay().main.temp.tempToString()
                        tempDay2 = it.getSecondDay().main.temp.tempToString()
                        val thirdDay = it.getThirdDay()
                        tempDay3 = thirdDay.main.temp.tempToString()
                        day3Name = getFormatedName(thirdDay.dt)
                    }) {}
        }
    }

    private fun getFormatedName(dt: Long): String {
        val date = Date(dt * 1000)
        return SimpleDateFormat("EEE, MMM d" , Locale("ru")).format(date)
    }
}
