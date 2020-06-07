package com.example.checkit.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: Repository
    val allCheckList: LiveData<List<EntityCheckList>>

    init {
        val checkDAO =
            CheckListDataBase.getDatabase(application, viewModelScope).checkListDao()
        repository = Repository(checkDAO)
        allCheckList = repository.allCheckList
    }

    fun insert(checkEntity: EntityCheckList) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(checkEntity)
    }
}