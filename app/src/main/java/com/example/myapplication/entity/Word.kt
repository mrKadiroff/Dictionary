package com.example.myapplication.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Word :Serializable {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @ColumnInfo(name = "word_rasm")
    var word_photo: String? = null

    @ColumnInfo(name = "word_kategorya")
    var word_category: Int? = null

    @ColumnInfo(name = "soz")
    var word: String? = null

    @ColumnInfo(name = "tarjima")
    var translate: String? = null

    @ColumnInfo(name = "rangi")
    var color: Int? = null

    @ColumnInfo(name = "tanlangan")
    var selected: String? = null
}