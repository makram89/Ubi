package com.app.bricklist.ui.projectslist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.bricklist.R
import com.app.bricklist.data.AppRepository
import com.app.bricklist.data.db.AppDatabase

class ProjectsListFragment : Fragment(), BrickListener {

    private lateinit var viewModel: ProjectsListViewModel
    private lateinit var appDatabase: AppDatabase
    private lateinit var repository: AppRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        val api = PlaceholderApi(this.resources.getString(R.string.base_url))
        appDatabase = AppDatabase.getInstance(inflater.context)
        repository = AppRepository(appDatabase)

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

        viewModel.liveProjects.observe(viewLifecycleOwner, Observer { projectList ->
            view?.findViewById<RecyclerView>(R.id.rv_projects).also {
                it?.adapter = ProjectsAdapter(projectList, this@ProjectsListFragment)
                it?.layoutManager = LinearLayoutManager(requireContext())
            }
            Log.d("CHECK",projectList.size.toString())
        })

    }

    override fun onProjectClick(itemView: View, item: Any) {
//        TODO("Not yet implemented")
        Log.d("CHECK", "Im In")
    }

    override fun onLongClick() {
        Log.d("CHECK", "Im In long")
//        TODO("Not yet implemented")
    }

}
