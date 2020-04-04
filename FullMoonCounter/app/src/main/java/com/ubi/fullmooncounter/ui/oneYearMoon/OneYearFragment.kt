package com.ubi.fullmooncounter.ui.oneYearMoon

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.ubi.fullmooncounter.R
import com.ubi.fullmooncounter.utils.Algorithms
import kotlinx.android.synthetic.main.one_year_fragment.*
import java.time.LocalDate

class OneYearFragment : Fragment() {

    private lateinit var viewModel: OneYearViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.one_year_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val algorithms = Algorithms()

        viewModel = ViewModelProvider(this, OneYearViewModelFactory(algorithms)).get(
            OneYearViewModel::class.java)
        year_field.text = LocalDate.now().year.toString()

        viewModel.collector(year_field.text.toString().toInt())

        plus_year_button.setOnClickListener{onPlusClick()}
        minnus_year_button.setOnClickListener{onMinusClick()}

        viewModel.dates.observe(viewLifecycleOwner, Observer {dates ->
            rv_full_moon_dates!!.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
               it.adapter = DateItemsAdapter(dates)
            }
        })


    }


    fun onPlusClick(){
        val yearFiled = year_field
        var cYear = yearFiled.text.toString().toInt()
        if(cYear <2200)
        {
            yearFiled.text = (cYear+1).toString()
            viewModel.collector(cYear+1)
        }
        else
        {
            Toast.makeText(requireContext(),"Year must be in range 1900-2200",Toast.LENGTH_SHORT).show()
        }

    }

    fun onMinusClick(){
        val yearFiled = year_field
        var cYear = yearFiled.text.toString().toInt()
        if(cYear >1600)
        {
            yearFiled.text = (cYear-1).toString()
            viewModel.collector(cYear-1)
        }
        else
        {
            Toast.makeText(requireContext(),"Year must be in range 1900-2200",Toast.LENGTH_SHORT).show()
        }
    }

}
