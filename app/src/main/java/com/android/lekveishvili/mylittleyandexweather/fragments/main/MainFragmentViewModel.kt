package com.android.lekveishvili.mylittleyandexweather.fragments.main

import android.databinding.Bindable
import android.databinding.ObservableArrayList
import android.view.View
import com.android.lekveishvili.mylittleyandexweather.BR
import com.android.lekveishvili.mylittleyandexweather.model.CityItemViewModel
import com.android.lekveishvili.mylittleyandexweather.R
import com.android.lekveishvili.mylittleyandexweather.Router
import com.android.lekveishvili.mylittleyandexweather.data.NetDataRepository
import com.android.lekveishvili.mylittleyandexweather.tempToString
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

    lateinit var list: List<String>

    val items = ObservableArrayList<Any>()
    private val itemBind: OnItemBindClass<*> = OnItemBindClass<Any>()
            .map(CityItemViewModel::class.java, BR.viewModel, R.layout.item_city)

    val itemBinding: ItemBinding<*> = ItemBinding.of(itemBind)

    fun loadData() {
        items.clear()
        val ids = list.toString()
                .replace(" ", "")
                .removePrefix("[")
                .removeSuffix("]")

        repository.getCurrentWeatherGroup(ids)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    error = View.VISIBLE
                    loading = View.GONE
                }
                .doOnSubscribe {
                    error = View.GONE
                    loading = View.VISIBLE
                }
                .subscribe({
                    loading = View.GONE
                    it.list.forEach {
                        items.add(CityItemViewModel(it.name,
                                it.weather[0].description,
                                it.main.temp.tempToString(),
                                it.id,
                                { openCity(it.id) }))
                    }
                }) {}
    }

    fun openCity(cityId: Int) {
        router.openCity(cityId.toString())
    }

    var isLoading = false
        @Bindable get() = loading == View.VISIBLE
        @Bindable set(value) {
            field = value
            notifyPropertyChanged(BR.day3Name)
        }

}
