package com.android.lekveishvili.mylittleyandexweather

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import com.android.lekveishvili.mylittleyandexweather.data.NetDataRepository
import com.android.lekveishvili.mylittleyandexweather.databinding.ActivityMainBinding
import com.android.lekveishvili.mylittleyandexweather.fragments.CityFragment
import com.android.lekveishvili.mylittleyandexweather.fragments.MainFragment
import com.android.lekveishvili.mylittleyandexweather.fragments.SearchFragment
import com.android.lekveishvili.mylittleyandexweather.viewmodel.BindingActivity
import javax.inject.Inject

class MainActivity : BindingActivity<MainActivityViewModel>(), Router {
    @Inject
    lateinit var repository: NetDataRepository

    private var mainFragment = MainFragment()
    private var searchFragment = SearchFragment()
    private var cityFragment = CityFragment()
    private var currentFragment = mainFragment

    companion object {
        const val CITY_ID = "CITY_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MyApplication.appComponent.inject(this)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        viewModel = MainActivityViewModel(repository, this)
        binding.viewModel = viewModel
    }

    override fun openMain() {
        replaceFragment(mainFragment)
    }

    override fun openSearch() {
        replaceFragment(searchFragment)
    }

    override fun openCity(cityId: String) {
        cityFragment.arguments?.putString(CITY_ID, cityId)
        replaceFragment(cityFragment)
    }

    private fun replaceFragment(newFragment: Fragment) {
//        if (currentFragment === newFragment) return
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, newFragment)
        transaction.commit()
//        Handler().postDelayed({
//            try {
//                val transaction = supportFragmentManager.beginTransaction()
//                transaction.replace(R.id.fragment_container, newFragment)
//                transaction.commit()
//            } catch (e: Exception) {
//            }
//        }, 300)
    }
}
