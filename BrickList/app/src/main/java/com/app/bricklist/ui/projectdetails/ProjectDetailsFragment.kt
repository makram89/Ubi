package com.app.bricklist.ui.projectdetails

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager

import com.app.bricklist.R
import com.app.bricklist.data.AppRepository
import com.app.bricklist.data.db.AppDatabase
import com.app.bricklist.data.network.BrickApi

class ProjectDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = ProjectDetailsFragment()
    }

    private lateinit var viewModel: ProjectDetailsViewModel
    private lateinit var appDatabase: AppDatabase
    private lateinit var repository: AppRepository


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val url = preferences.getString("URL","")
        val api : BrickApi
        appDatabase = AppDatabase.getInstance(inflater.context)
        if ( url != null && url.isNotEmpty())
        {
            api = BrickApi(url as String)
            repository = AppRepository(appDatabase, api)
        }
        else{
            api = BrickApi(this.resources.getString(R.string.base_url))
            repository = AppRepository(appDatabase, api)
            Log.d("ERROR", "Incorrect URL, using default")
        }
        return inflater.inflate(R.layout.project_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(
                this,
                ProjectDetailsVMFactory(repository)
            ).get(ProjectDetailsViewModel::class.java)
        val arg = arguments?.get("InventoryID")
        Log.d("WATCHA", arg.toString())
    }

}
