package com.app.bricklist.ui.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Brick(


    var ColorID: Int = 0,
    var colorName: String = "",
    var colorNamePL: String = "",

    var Extra: String = "0",
    var InventoryID: Int? = null,

    var ItemID: String = "0",
    var itemName: String? = null,
    var itemNamePL: String? = null,

    var QuantityInSet: Int = 1,
    var QuantityInStore: Int = 0,

    var TypeID: String? = null,
    var typeName: String? = null,
    var typeNamePL: String? = null,

    var code: Int? = null,

//  InventoryParts ID
    var id: Int
    ) : Parcelable