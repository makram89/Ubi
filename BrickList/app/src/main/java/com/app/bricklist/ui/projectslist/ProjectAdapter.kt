package com.app.bricklist.ui.projectslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.bricklist.data.models.Inventories
import com.app.bricklist.databinding.RvItemProjectBinding

class ProjectsAdapter(
    var projectsDataset: ArrayList<Inventories>,
    private val listener: ProjectListener
) : RecyclerView.Adapter<ProjectsAdapter.ProjectItemHolder>() {
    private val fullDataset = projectsDataset

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectItemHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = RvItemProjectBinding.inflate(inflater, parent, false)

        return ProjectItemHolder(
            binding
        )

    }

    override fun onBindViewHolder(holder: ProjectItemHolder, position: Int) {
        holder.bind(projectsDataset[position], listener)
    }

    override fun getItemCount(): Int {
        return projectsDataset.size
    }

    class ProjectItemHolder(
        private val binding: RvItemProjectBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        //        TODO add model instead of inventories
        fun bind(item: Inventories, listener: ProjectListener) {
            binding.item = item
            binding.executePendingBindings()
            itemView.setOnClickListener { listener.onProjectClick(itemView, item) }
            itemView.setOnLongClickListener {
                listener.onLongClick(itemView, item)
                return@setOnLongClickListener true

            }
        }
    }

}