package com.android.lekveishvili.mylittleyandexweather.fragments

import android.databinding.ObservableArrayList
import com.android.lekveishvili.mylittleyandexweather.BR
import com.android.lekveishvili.mylittleyandexweather.CityViewModel
import com.android.lekveishvili.mylittleyandexweather.R
import com.android.lekveishvili.mylittleyandexweather.Router
import com.android.lekveishvili.mylittleyandexweather.data.NetDataRepository
import com.android.lekveishvili.mylittleyandexweather.viewmodel.FragmentViewModel
import me.tatarka.bindingcollectionadapter2.ItemBinding
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass

class SearchFragmentViewModel(private val repository: NetDataRepository,
                              val router: Router,
                              val hideKeyboard: () -> Unit)
    : FragmentViewModel() {
    val items = ObservableArrayList<Any>()
    private val itemBind: OnItemBindClass<*> = OnItemBindClass<Any>()
            .map(CityViewModel::class.java, BR.viewModel, R.layout.item_city)

    val itemBinding: ItemBinding<*> = ItemBinding.of(itemBind)

    fun loadData() {
        hideKeyboard()
    }

    fun openMain() {
        //TODO исправить краш: ввожу текст,жму назад
        router.openMain()
    }
}
