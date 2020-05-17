package com.app.bricklist.ui.projectslist

import android.view.View

interface BrickListener{
    fun onProjectClick(itemView: View, item: Any)
    fun onLongClick()
}