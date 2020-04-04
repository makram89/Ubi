package com.ubi.fullmooncounter.ui.currentMoonState


import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import com.ubi.fullmooncounter.MainActivity

import com.ubi.fullmooncounter.R
import com.ubi.fullmooncounter.ui.oneYearMoon.OneYearFragment
import com.ubi.fullmooncounter.utils.Algorithms
import kotlinx.android.synthetic.main.current_moon_state_frament_fragment.*
import java.time.LocalDate

class CurrentMoonStateFragment : Fragment() {

    private lateinit var viewModel: CurrentMoonStateViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_moon_state_frament_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val algorithms = Algorithms()

        viewModel = ViewModelProvider(this, CurrentMoonStateViewModelFactory(algorithms)).get(CurrentMoonStateViewModel::class.java)

        val currentDate = LocalDate.now()

        val moonPhase = viewModel.getMoonPhase(currentDate.year, currentDate.monthValue, currentDate.dayOfMonth).toInt()
        bind(moonPhase,currentDate)

        full_moons_button.setOnClickListener{
            onClickButton()
        }

    }

    private fun bind(moonPhase : Int, currentDate : LocalDate)
    {
        val percent= ((moonPhase.toLong()/30.0)*100.0).toInt().toString()

        val daysTillFull = if(moonPhase <=15) {
            15 - moonPhase
        } else{
            15 + (30-moonPhase)
        }
        full_moon_progress.text = "$percent%"
        last_new_moon.text = currentDate.minusDays(moonPhase.toLong()).toString()
        next_full_moon.text = currentDate.plusDays(daysTillFull.toLong()).toString()
        //TODO check north or south
        //setting image
        val picName = "n$moonPhase"
        context?.resources?.getIdentifier(picName,"drawable", context?.packageName)?.let {
            moon_pic.setImageResource(
                it
            )
        }
    }

    private fun onClickButton(){
        val fragmentTransaction =
            (context as MainActivity).supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(id, OneYearFragment())
            .addToBackStack("app")
            .commit()
    }


}
