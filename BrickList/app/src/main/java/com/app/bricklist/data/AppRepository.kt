package com.app.bricklist.data

import com.app.bricklist.data.db.AppDatabase
import com.app.bricklist.data.models.Inventories
import com.app.bricklist.data.models.InventoriesParts
import com.app.bricklist.data.network.BrickApi
import com.app.bricklist.utils.SafeApiRequest

class AppRepository(private val appDatabase: AppDatabase, private val api: BrickApi) : SafeApiRequest() {

    private val brickDao = appDatabase.brickDao()


    fun getProject(id: Int) =  api.getProject(id)


    //    DAO
    fun getProjects() = brickDao.getProjects()

    fun counter() = brickDao.countProjects()
    fun partsCounter() = brickDao.countParts()


    fun addProject(inventories: Inventories) = brickDao.insertOrUpdateProject(inventories)

    fun addPart(inventoriesParts: InventoriesParts) = brickDao.insertOrUpdateParts(inventoriesParts)
}


