package com.android.lekveishvili.mylittleyandexweather.fragments.search

import android.databinding.Bindable
import android.databinding.ObservableArrayList
import android.view.View
import android.widget.EditText
import com.android.lekveishvili.mylittleyandexweather.BR
import com.android.lekveishvili.mylittleyandexweather.R
import com.android.lekveishvili.mylittleyandexweather.Router
import com.android.lekveishvili.mylittleyandexweather.data.NetDataRepository
import com.android.lekveishvili.mylittleyandexweather.data.model.City
import com.android.lekveishvili.mylittleyandexweather.model.SearchItemViewModel
import com.android.lekveishvili.mylittleyandexweather.tempToString
import com.android.lekveishvili.mylittleyandexweather.viewmodel.FragmentViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass

class SearchFragmentViewModel(private val repository: NetDataRepository,
                              private val router: Router,
                              private val hideKeyboard: () -> Unit,
                              private val editText: EditText)
    : FragmentViewModel() {
    val items = ObservableArrayList<Any>()
    private val itemBind: OnItemBindClass<*> = OnItemBindClass<Any>()
            .map(SearchItemViewModel::class.java, BR.viewModel, R.layout.item_search)

    val itemBinding: ItemBinding<*> = ItemBinding.of(itemBind)

    var listIsEmpty = View.GONE
        @Bindable get() = field
        @Bindable set(value) {
            field = value
            notifyPropertyChanged(BR.listIsEmpty)
        }

    fun loadData() {
        items.clear()
        hideKeyboard()
        repository.getCityByName(editText.text.toString())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    error = View.VISIBLE
                    loading = View.GONE
                    listIsEmpty = View.GONE

                }
                .doOnSubscribe {
                    error = View.GONE
                    listIsEmpty = View.GONE
                    loading = View.VISIBLE
                }
                .subscribe({
                    loading = View.GONE
                    listIsEmpty = if (it.cities.isEmpty()) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
                    it.cities.forEach {
                        items.add(SearchItemViewModel(formatCityInfoToString(it), { addToList(it.id) }))
                    }
                }) {
                }
    }

    private fun formatCityInfoToString(city: City): String {
        var str = ""
        str += city.name
        str += ", " + city.sys.country
        str += " " + city.main.temp.tempToString()
        str += " " + getPosition(city.coord)
        return str
    }

    private fun getPosition(coord: City.Coord): String {
        val lat = coord.lat.toInt().toString()
        val lon = coord.lon.toInt().toString()
        return "[$lat,$lon]"
    }

    private fun addToList(cityId: Int) {
        router.addCity(cityId)
    }

    fun openMain() {
        router.openMain()
    }
}
