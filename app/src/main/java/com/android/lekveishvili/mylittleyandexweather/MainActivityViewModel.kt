package com.android.lekveishvili.mylittleyandexweather

import android.databinding.Bindable
import android.databinding.ObservableArrayList
import com.android.lekveishvili.mylittleyandexweather.viewmodel.ActivityViewModel
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass

class MainActivityViewModel()
    : ActivityViewModel() {

    val items = ObservableArrayList<Any>()
    private val itemBind: OnItemBindClass<*> = OnItemBindClass<Any>()
            .map(CityViewModel::class.java, BR.viewModel, R.layout.item_city)

    val itemBinding: ItemBinding<*> = ItemBinding.of(itemBind)

    var test = "demo test"
        @Bindable get() = field
        @Bindable set(value) {
            field = value
            notifyPropertyChanged(BR.test)
        }
}