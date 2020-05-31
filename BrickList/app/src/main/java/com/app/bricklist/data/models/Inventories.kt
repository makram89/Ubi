package com.app.bricklist.data.models
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.sql.Date
import java.sql.Timestamp

@Entity(
    tableName = "inventories"
)

data class Inventories(
    var Active: Int,
    var LastAccessed: Long,
    var Name: String,
    @PrimaryKey
    var id: Int
)