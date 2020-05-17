package com.app.bricklist.data.models
import android.os.Parcelable
import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(
    tableName = "parts"
)
@Parcelize
data class Parts(
    var CategoryID: Int,
    var Code: String,
    var Name: String,
    @Nullable
    var NamePL: String? = null,
    var TypeID: Int,
    @PrimaryKey
    var id: Int
) : Parcelable