package com.app.bricklist.data

import com.app.bricklist.data.db.AppDatabase
import com.app.bricklist.data.models.Colors
import com.app.bricklist.data.models.Inventories
import com.app.bricklist.data.models.InventoriesParts
import com.app.bricklist.data.network.BrickApi
import com.app.bricklist.utils.SafeApiRequest

class AppRepository(private val appDatabase: AppDatabase, private val api: BrickApi) : SafeApiRequest() {

    private val brickDao = appDatabase.brickDao()


    fun getProject(id: Int) =  api.getProject(id)


    //    DAO
    fun getProjects() = brickDao.getProjects()
    fun getActiveProjects() = brickDao.getActiveProjects()

    fun getProjectBricks(id:Int) = brickDao.getProjectBricks(id)

    fun updateInventoryPart(id:Int, value:Int) = brickDao.updateInventoryPart(id, value)

    fun counter() = brickDao.countProjects()
    fun partsCounter() = brickDao.countParts()


    fun addProject(inventories: Inventories) = brickDao.insertOrUpdateProject(inventories)

    fun addPart(inventoriesParts: InventoriesParts) = brickDao.insertOrUpdateParts(inventoriesParts)



    fun getBrickName(code : String) = brickDao.getBrickName(code)

    fun getBrickColor(id: Int) =brickDao.getBrickColor(id)

    fun updateProject(inventories: Inventories) = brickDao.insertOrUpdateProject(inventories)
}


