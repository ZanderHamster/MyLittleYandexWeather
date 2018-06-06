package com.android.lekveishvili.mylittleyandexweather.fragments

import android.databinding.ObservableArrayList
import android.view.View
import com.android.lekveishvili.mylittleyandexweather.BR
import com.android.lekveishvili.mylittleyandexweather.CityViewModel
import com.android.lekveishvili.mylittleyandexweather.R
import com.android.lekveishvili.mylittleyandexweather.Router
import com.android.lekveishvili.mylittleyandexweather.data.NetDataRepository
import com.android.lekveishvili.mylittleyandexweather.viewmodel.FragmentViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass

class MainFragmentViewModel(private val repository: NetDataRepository,
                            val router: Router) : FragmentViewModel() {
    companion object {
        const val MOSCOW = "524901"
        const val SAINT_PETERSBURG = "498817"
    }

    val items = ObservableArrayList<Any>()
    private val itemBind: OnItemBindClass<*> = OnItemBindClass<Any>()
            .map(CityViewModel::class.java, BR.viewModel, R.layout.item_city)

    val itemBinding: ItemBinding<*> = ItemBinding.of(itemBind)

    fun loadData() {
//        getCityModelForRequest("Брянск")
        getFiveDaysWeather(MOSCOW)
        getFiveDaysWeather(SAINT_PETERSBURG)

//        repository.getCurrentWeatherByCity("Moscow")
//        repository.getCurrentWeatherByCitiesId("Saint Petersburg")
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnError {
//                    error = true
//                }
//                .doOnSubscribe({
//                    error = false
//                })
//                .subscribe({
//                    it.list.forEach {
//                        items.add(CityViewModel(it.main.currentTemp.toString()))
//                    }
//                }) {}
//
//
//        repository.getCurrentWeatherByCity("Saint Petersburg")
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnError {
//                    error = true
//                }
//                .doOnSubscribe({
//                    error = false
//                })
//                .subscribe({
//                    items.add(CityViewModel(it.main.currentTemp.toString()))
//                }) {}

    }

//    private fun getCityModelForRequest(cityName: String) {
//        repository.getCityByName(cityName)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnError {
//                    error = true
//                }
//                .doOnSubscribe({
//                    error = false
//                })
//                .subscribe({
//                    val city = it.cities[0]
//                    listOf<WeatherFiveDaysRequest.WeatherFiveDayItem>()
//                    items.add(CityViewModel(
//                            description = city.weather[0].description,
//                            name = city.name,
//                            ))
//                }) {}
//    }

    private fun getFiveDaysWeather(cityId: String) {
        repository.getWeatherForFiveDays(cityId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    error = View.VISIBLE
                }
                .doOnSubscribe({
                    error = View.GONE
                })
                .subscribe({
                    val weather = it.getFirstDay().weather
                    var description = ""
                    val tmpDescription = weather.getOrNull(weather.lastIndex)?.description
                    if (tmpDescription != null) {
                        description = tmpDescription
                    }
                    items.add(CityViewModel(
                            it.city.name,
                            description,
                            listOf(it.getFirstDay(), it.getSecondDay(), it.getThirdDay()),
                            { openCity(it.city.id.toString()) }
                    ))
                }) {}
    }

    fun openCity(cityId: String) {
        router.openCity(cityId)
    }
}
