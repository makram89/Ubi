package com.app.bricklist.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.bricklist.data.models.Inventories
import com.app.bricklist.data.models.InventoriesParts

@Dao
interface BrickDao{

//    Get all projects
    @Query("SELECT * FROM inventories;")
    fun getProjects():List<Inventories>

//    Get active project
    @Query("SELECT * FROM inventories where inventories.active = 1;")
    fun getActiveProjects():List<Inventories>

//    Add project
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateProject(inventories: Inventories): Long

//    count projects
    @Query("SELECT count(*) FROM inventories")
    fun countProjects():Int
    //    count parts
    @Query("SELECT count(*) FROM inventoriesParts")
    fun countParts():Int

//    Bricks for project
    @Query("SELECT * from inventoriesParts where inventoriesParts.InventoryID = :id")
    fun getProjectBricks(id : Int) : List<InventoriesParts>

// Add inventories part
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateParts(inventoriesParts: InventoriesParts):Long

}