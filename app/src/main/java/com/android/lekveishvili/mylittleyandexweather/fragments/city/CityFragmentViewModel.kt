package com.android.lekveishvili.mylittleyandexweather.fragments.city

import android.databinding.Bindable
import android.view.View
import com.android.lekveishvili.mylittleyandexweather.BR
import com.android.lekveishvili.mylittleyandexweather.data.NetDataRepository
import com.android.lekveishvili.mylittleyandexweather.fragments.main.MainFragmentViewModel.Companion.MOSCOW
import com.android.lekveishvili.mylittleyandexweather.fragments.main.MainFragmentViewModel.Companion.SAINT_PETERSBURG
import com.android.lekveishvili.mylittleyandexweather.viewmodel.FragmentViewModel
import com.android.lekveishvili.mylittleyandexweather.tempToString
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

class CityFragmentViewModel(private val repository: NetDataRepository,
                            @Bindable val removeCity: () -> Unit)
    : FragmentViewModel() {

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

    var btnVisibility = View.VISIBLE
        @Bindable get() {
            return if (cityId == MOSCOW || cityId == SAINT_PETERSBURG) {
                View.GONE
            } else {
                View.VISIBLE
            }
        }
        @Bindable set(value) {
            field = value
            notifyPropertyChanged(BR.btnVisibility)
        }


    fun loadData() {

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
                    val firstDay = it.getFirstDay()
                    val secondDay = it.getSecondDay()
                    val thirdDay = it.getThirdDay()
                    tempDay1 = firstDay.main.temp.tempToString() + " " + firstDay.weather[0].description
                    tempDay2 = secondDay.main.temp.tempToString() + " " + secondDay.weather[0].description
                    tempDay3 = thirdDay.main.temp.tempToString() + " " + thirdDay.weather[0].description
                    day3Name = getFormatedName(thirdDay.dt)
                }) {}
    }

    private fun getFormatedName(dt: Long): String {
        val date = Date(dt * 1000)
        return SimpleDateFormat("EEE, MMM d", Locale("ru")).format(date)
    }
}
