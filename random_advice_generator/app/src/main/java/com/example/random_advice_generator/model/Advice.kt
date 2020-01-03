package com.example.random_advice_generator.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "adviceTable")
data class Advice (

    @ColumnInfo(name = "advice")
    var advice: String,

    @ColumnInfo(name = "rating")
    var rating: Int,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
) : Parcelable