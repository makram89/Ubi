package com.app.bricklist.data.models

import android.os.Parcelable
import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "categories")
@Parcelize
data class Categories(
    @PrimaryKey
    var id: Int,
    var Code: Int,
    var Name: String,
    @Nullable
    var NamePL: String? = null

) : Parcelable