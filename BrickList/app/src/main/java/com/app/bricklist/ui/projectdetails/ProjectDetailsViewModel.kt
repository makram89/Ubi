package com.app.bricklist.ui.projectdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.bricklist.data.AppRepository
import com.app.bricklist.ui.models.Brick
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProjectDetailsViewModel(val repository: AppRepository) : ViewModel() {

    private var bricksMutable = MutableLiveData<ArrayList<Brick>>()
    val liveBricks: LiveData<ArrayList<Brick>>
        get() = bricksMutable

    fun fetchBricks(id: Int) {


        GlobalScope.launch {

//            First downland IP
            val parts = repository.getProjectBricks(id)
            val bricks = ArrayList<Brick>()

            for (part in parts) {
                bricks.add(
                    Brick(
                        id = part.id,
                        ColorID = part.ColorID,
                        Extra = part.Extra,
                        ItemID = part.ItemID,
                        InventoryID = part.InventoryID,
                        QuantityInSet = part.QuantityInSet,
                        QuantityInStore = part.QuantityInStore,
                        TypeID = part.TypeID
                    )
                )
            }


//            Sec additional info
            for (i in 0 until bricks.size) {

                // Get part values
                val name = repository.getBrickName(bricks[i].ItemID)
//                IDE IS WRONG :)
                if (name == null) {
                    continue
                }
                //Get color
                val color = repository.getBrickColor(bricks[i].ColorID)


                if (!color.NamePL.isNullOrEmpty()) {
                    bricks[i].colorNamePL = color.NamePL!!
                }
                bricks[i].colorName = color.Name

                if (!name.NamePL.isNullOrEmpty()) {
                    bricks[i].itemNamePL = name.NamePL
//                    Log.d("ERROR", "No Polish name")
                }
                bricks[i].itemName = name.Name

                val code = repository.getCode(name.id, bricks[i].ColorID)

                if (code != null) {
                    bricks[i].code = code
                }

//            Create Bricks array and postValue() or value = xxx
                bricksMutable.postValue(bricks)

            }


        }
    }

    fun updateBrick(brick: Brick) {
        GlobalScope.launch {
            repository.updateInventoryPart(brick.id, brick.QuantityInStore)
        }
    }
}
