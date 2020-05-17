package com.app.bricklist.data.models

import android.os.Parcelable
import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(
    tableName = "itemTypes"
)
@Parcelize
data class ItemTypes(
    var Code: String,
    var Name: String,
    @Nullable
    var NamePL: String? = null,
    @PrimaryKey
    var id: Int
) : Parcelable