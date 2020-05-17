package com.app.bricklist.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.bricklist.data.models.Inventories

@Dao
interface BrickDao{

//    Get all projects
    @Query("SELECT * FROM inventories;")
    fun getProjects():List<Inventories>

//    Get active project
    @Query("SELECT * FROM inventories where inventories.active =1;")
    fun getActiveProjects():List<Inventories>

//    Add project
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateProject(inventories: Inventories): Long

//    count bricks
    @Query("SELECT count(*) FROM codes")
    fun countCodes():Int

}