package com.ubi.fullmooncounter.ui.changeSettings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.ubi.fullmooncounter.MainActivity

import com.ubi.fullmooncounter.R
import kotlinx.android.synthetic.main.settings_fragment.*

class ChangeSettingsFragment : DialogFragment() {

    companion object {
        fun newInstance() = ChangeSettingsFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.settings_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setLetterHemi()
        context?.let { populateSpinner(it) }
        change_hemi.setOnClickListener { onHemiClick() }

    }

    fun onHemiClick() {
        MainActivity.Singletons.isNorthHemi = !MainActivity.Singletons.isNorthHemi
        setLetterHemi()
    }

    fun setLetterHemi() {
        if (MainActivity.Singletons.isNorthHemi) {
            change_hemi.text = "N"
        } else {
            change_hemi.text = "S"
        }
    }

    fun populateSpinner(context: Context){
        val list_of_algs = MainActivity.AlgorithmsNames.values()

        val adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, list_of_algs)
        // Set layout to use when the list of choices appear
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        algorithm_spinner.adapter = adapter
        val value = adapter.getPosition(MainActivity.Singletons.algorithmName)
        algorithm_spinner.setSelection(value)


    }


}
