package com.android.lekveishvili.mylittleyandexweather

import android.content.Context
import android.content.SharedPreferences
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.inputmethod.InputMethodManager
import com.android.lekveishvili.mylittleyandexweather.data.NetDataRepository
import com.android.lekveishvili.mylittleyandexweather.databinding.ActivityMainBinding
import com.android.lekveishvili.mylittleyandexweather.fragments.city.CityFragment
import com.android.lekveishvili.mylittleyandexweather.fragments.main.MainFragment
import com.android.lekveishvili.mylittleyandexweather.fragments.main.MainFragmentViewModel.Companion.MOSCOW
import com.android.lekveishvili.mylittleyandexweather.fragments.main.MainFragmentViewModel.Companion.SAINT_PETERSBURG
import com.android.lekveishvili.mylittleyandexweather.fragments.search.SearchFragment
import com.android.lekveishvili.mylittleyandexweather.viewmodel.BindingActivity
import javax.inject.Inject

class MainActivity : BindingActivity<MainActivityViewModel>(), Router {
    @Inject
    lateinit var repository: NetDataRepository

    private var mainFragment = MainFragment()
    private var searchFragment = SearchFragment()
    private var cityFragment = CityFragment()

    companion object {
        const val CITY_ID = "CITY_ID"
    }

    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApplication.appComponent.inject(this)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        viewModel = MainActivityViewModel(this)
        binding.viewModel = viewModel
        sharedPreferences = getSharedPreferences("PREFS", Context.MODE_PRIVATE)

        val cities = sharedPreferences.all
        if (!cities.containsValue(MOSCOW)) {
            sharedPreferences.edit().putString(SAINT_PETERSBURG, MOSCOW).apply()
        }
        if (!cities.containsValue(SAINT_PETERSBURG)) {
            sharedPreferences.edit().putString(SAINT_PETERSBURG, SAINT_PETERSBURG).apply()
        }
    }

    override fun openMain() {
        replaceFragment(mainFragment)
    }

    override fun openSearch() {
        replaceFragment(searchFragment)
    }

    override fun openCity(cityId: String) {
        val bundle = Bundle()
        bundle.putString(CITY_ID, cityId)
        cityFragment.arguments = bundle
        replaceFragment(cityFragment)
    }

    override fun addCity(cityId: Int) {
        sharedPreferences.edit().putString(cityId.toString(), cityId.toString()).apply()
        openMain()
    }

    override fun removeCity(cityId: String) {
        sharedPreferences.edit().remove(cityId).apply()
        openMain()
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun replaceFragment(newFragment: Fragment) {
        hideKeyboard()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, newFragment)
        transaction.commit()
    }
}
