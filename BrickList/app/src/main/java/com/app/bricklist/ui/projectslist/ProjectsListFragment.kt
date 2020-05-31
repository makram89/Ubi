package com.app.bricklist.ui.projectslist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.bricklist.R
import com.app.bricklist.data.AppRepository
import com.app.bricklist.data.db.AppDatabase
import com.app.bricklist.data.models.Inventories
import com.app.bricklist.data.network.BrickApi
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProjectsListFragment : Fragment(), ProjectListener {

    private lateinit var viewModel: ProjectsListViewModel
    private lateinit var appDatabase: AppDatabase
    private lateinit var repository: AppRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val url = preferences.getString("URL", "")
        val api: BrickApi
        appDatabase = AppDatabase.getInstance(inflater.context)
        if (url != null && url.isNotEmpty()) {
            api = BrickApi(url)
            repository = AppRepository(appDatabase, api)
        } else {
            api = BrickApi(this.resources.getString(R.string.base_url))
            repository = AppRepository(appDatabase, api)
            Log.d("ERROR", "Incorrect URL, using default")
        }




        return inflater.inflate(R.layout.projects_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(
                this,
                ProjectListVMFactory(repository)
            ).get(ProjectsListViewModel::class.java)

        viewModel.fetchProjects()

        val fab: FloatingActionButton = requireView().findViewById(R.id.fab)
        fab.setOnClickListener {
            findNavController().navigate(R.id.action_projectsList_to_addProject)
        }

        viewModel.liveProjects.observe(viewLifecycleOwner, Observer { projectList ->
            view?.findViewById<RecyclerView>(R.id.rv_projects).also {
                it?.adapter = ProjectsAdapter(projectList, this@ProjectsListFragment)
                it?.layoutManager = LinearLayoutManager(requireContext())
            }
            Log.d("CHECK", projectList.size.toString())
        })

    }

    override fun onProjectClick(itemView: View, item: Inventories) {
        val bundle = bundleOf("InventoryID" to item.id)
        findNavController().navigate(R.id.action_projectsList_to_projectDetails, bundle)
        Log.d("CHECK", "Im In")
    }

    override fun onLongClick(
        itemView: View,
        item: Inventories
    ) {
        Log.d("CHECK", "Im In long")
//        TODO("Not yet implemented")
    }

}
