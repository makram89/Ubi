package com.app.bricklist.ui.models

import com.app.bricklist.data.models.InventoriesParts

data class Brick(


    val ColorID: Int,
    val colorName: String,
    var colorNamePL: String? = null,

    var Extra: Int = 0,
    val InventoryID: Int,

    val ItemID: Int,
    val itemName: String,
    var itemNamePL: String? = null,

    var QuantityInSet: Int,
    var QuantityInStore: Int = 0,

    var TypeID: Int,
    var typeName: String,
    var typeNamePL: String? = null,

//  InventoryParts ID
    var id: Int
    )