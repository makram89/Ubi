package com.ubi.fullmooncounter.ui.oneYearMoon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ubi.fullmooncounter.MainActivity
import com.ubi.fullmooncounter.utils.Algorithms

class OneYearViewModel(val algorithms: Algorithms) : ViewModel() {


    val _dates = MutableLiveData<ArrayList<String>>()
    val dates: LiveData<ArrayList<String>>
        get() = _dates


//   todo  Coroutine collector

    fun collector(year: Int) {

        val datesArrayList = ArrayList<String>()
        var moonPhase: Double
        for (x in 1..12) {
            moonPhase = when(MainActivity.algorithmName) {
                MainActivity.AlgorithmsNames.TRIG2 -> algorithms.trig2(year, x, 1)
                MainActivity.AlgorithmsNames.TRIG1 -> algorithms.trig1(year, x, 1)
                MainActivity.AlgorithmsNames.CONWAY -> algorithms.conway(year, x, 1)
                MainActivity.AlgorithmsNames.SIMPLE -> algorithms.simple(year, x, 1)
            }

            val daysTillFull = if (moonPhase <= 15) {
                15 - moonPhase
            } else {
                15 + (30 - moonPhase)
            }


            datesArrayList.add("${(daysTillFull+1).toInt()}.$x.$year")
        }
        _dates.value = datesArrayList

    }
}
