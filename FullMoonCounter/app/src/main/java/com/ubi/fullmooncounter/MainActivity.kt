package com.ubi.fullmooncounter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


    companion object Singletons {
        var isNorthHemi = true
        val algorithmName = AlgorithmsNames.TRIG2
    }

    enum class AlgorithmsNames {
        TRIG2, TRIG1, CONWAY, SIMPLE
    }


}