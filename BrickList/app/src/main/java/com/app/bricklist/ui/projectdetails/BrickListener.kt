package com.app.bricklist.ui.projectdetails

import com.app.bricklist.ui.models.Brick

interface BrickListener {
    fun onPlusClick(brick : Brick)
    fun onMinusClick(brick : Brick)
    fun valueUpdate(brick : Brick)
    fun onSave()

}