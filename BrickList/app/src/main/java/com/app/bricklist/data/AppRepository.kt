package com.app.bricklist.data

import com.app.bricklist.data.db.AppDatabase
import com.app.bricklist.utils.SafeApiRequest

class AppRepository( var appDatabase: AppDatabase) : SafeApiRequest() {

    private val brickDao = appDatabase.brickDao()


//    DAO
    fun getProjects() = brickDao.getProjects()

    fun counter() = brickDao.countCodes()
}


