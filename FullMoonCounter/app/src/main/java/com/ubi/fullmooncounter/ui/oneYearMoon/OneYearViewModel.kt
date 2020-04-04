package com.ubi.fullmooncounter.ui.oneYearMoon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ubi.fullmooncounter.utils.Algorithms

class OneYearViewModel(val algorithms: Algorithms) : ViewModel() {


    val _dates = MutableLiveData<ArrayList<String>>()
    val dates: LiveData<ArrayList<String>>
        get() = _dates


//   todo  Coroutine collector

    fun collector(year: Int) {

        val datesArrayList = ArrayList<String>()
        var score = 0
        for (x in 1..12) {
            score = algorithms.trig2(year, x, 1).toInt() + 1
            datesArrayList.add("$score.$x.$year")
        }
        _dates.value = datesArrayList

    }
}
