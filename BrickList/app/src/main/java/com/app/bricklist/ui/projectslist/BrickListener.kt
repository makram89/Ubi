package com.app.bricklist.ui.projectslist

import android.view.View
import com.app.bricklist.data.models.Inventories

interface BrickListener{
    fun onProjectClick(itemView: View, item: Inventories)
    fun onLongClick(itemView: View, item: Inventories)
}