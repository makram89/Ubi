package com.app.bricklist.data

import com.app.bricklist.data.db.AppDatabase
import com.app.bricklist.data.network.BrickApi
import com.app.bricklist.utils.SafeApiRequest

class AppRepository(private val appDatabase: AppDatabase, private val api: BrickApi) : SafeApiRequest() {

    private val brickDao = appDatabase.brickDao()


    fun getProject(id: Int) =  api.getProject(id)


    //    DAO
    fun getProjects() = brickDao.getProjects()

    fun counter() = brickDao.countCodes()
}


