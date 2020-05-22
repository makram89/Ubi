package com.app.bricklist.data.models
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
@Entity(
    tableName = "inventories"
)

data class Inventories(
    var Active: Int,
    var LastAccessed: Int,
    var Name: String,
    @PrimaryKey
    var id: Int
)