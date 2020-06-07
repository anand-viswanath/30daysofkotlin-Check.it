package com.example.checkit.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class Repository(private var checklistDao: Dao) {

    val allCheckList: LiveData<List<EntityCheckList>> = checklistDao.getCheckList()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(checkEntity: EntityCheckList) {
        checklistDao.insertData(checkEntity)
    }
}