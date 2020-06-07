package com.example.checkit.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "check_table")
data class EntityCheckList(
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "check_box_list")
    var checkBoxes: ArrayList<String>,
    @ColumnInfo(name = "date")
    var date: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0
}