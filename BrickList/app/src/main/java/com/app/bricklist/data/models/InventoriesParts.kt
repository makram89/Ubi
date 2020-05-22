package com.app.bricklist.data.models
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
@Entity(
    tableName = "inventoriesParts"
)
@Parcelize
data class InventoriesParts(
    var ColorID: Int,
    var Extra: String = "0",
    var InventoryID: Int,
    var ItemID: String,
    var QuantityInSet: Int,
    var QuantityInStore: Int = 0,
    var TypeID: String,
    @PrimaryKey
    var id: Int
) : Parcelable