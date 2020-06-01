package com.app.bricklist.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.bricklist.data.models.Colors
import com.app.bricklist.data.models.Inventories
import com.app.bricklist.data.models.InventoriesParts
import com.app.bricklist.data.models.Parts

@Dao
interface BrickDao {

    //    Get all projects
    @Query("SELECT * FROM inventories ORDER BY inventories.LastAccessed DESC;")
    fun getProjects(): List<Inventories>

    //    Get active project
    @Query("SELECT * FROM inventories where inventories.active = 1 ORDER BY inventories.LastAccessed DESC;")
    fun getActiveProjects(): List<Inventories>

    //    Add project
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateProject(inventories: Inventories): Long

    //    count projects
    @Query("SELECT count(*) FROM inventories")
    fun countProjects(): Int

    //    count parts
    @Query("SELECT count(*) FROM inventoriesParts")
    fun countParts(): Int


    // Add inventories part
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateParts(inventoriesParts: InventoriesParts): Long

    //    Bricks for project
    @Query("SELECT * from inventoriesParts where inventoriesParts.InventoryID = :id ORDER BY inventoriesParts.TypeID;")
    fun getProjectBricks(id: Int): List<InventoriesParts>

    //    Get color
    @Query("SELECT * from colors where Code = :code")
    fun getBrickColor(code: Int): Colors

//    get part name
    @Query("SELECT * from parts where Code = :code")
    fun getBrickName(code : String) : Parts

    @Query("UPDATE inventoriesParts SET QuantityInStore = :value where inventoriesParts.id = :id")
    fun updateInventoryPart(id : Int, value : Int)

    @Query("select Code from Codes where ItemID=:id and ColorID=:color;")
    fun getCode(id: Int, color: Int): Int


}