package com.app.bricklist.data.models
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
@Entity(
    tableName = "inventories"
)
@Parcelize
data class Inventories(
    var Active: Int = 1,
    var LastAccessed: Int,
    var Name: String,
    @PrimaryKey
    var id: Int
) : Parcelable