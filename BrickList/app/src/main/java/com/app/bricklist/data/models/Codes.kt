package com.app.bricklist.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "codes"
)
data class Codes(
    @PrimaryKey
    var id: Int,
    var ItemID: Int,
    var ColorID: Int?,
    var Code: Int?,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var Image: ByteArray? = null
)