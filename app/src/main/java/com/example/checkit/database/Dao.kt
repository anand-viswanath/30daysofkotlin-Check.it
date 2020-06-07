package com.example.checkit.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {

    @Query("select * from check_table")
    fun getCheckList(): LiveData<List<EntityCheckList>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(vararg checkList: EntityCheckList)

    @Query("delete from check_table where id=:key")
    fun delete(key: String)

    @Update
    fun updateList(vararg checkList: EntityCheckList)
}