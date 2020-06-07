package com.example.checkit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [EntityCheckList::class], version = 1)
abstract class CheckListDataBase : RoomDatabase() {

    abstract fun checkListDao(): Dao

    companion object {
        @Volatile
        private var INSTANCE: CheckListDataBase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): CheckListDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance =
                    Room.databaseBuilder(context, CheckListDataBase::class.java, "article_database")
                        .addCallback(CheckListDataBaseCallback(scope))
                        .build()
                INSTANCE = instance
                instance
            }
        }

        private class CheckListDataBaseCallback(private val scope: CoroutineScope) :
            RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database!!.checkListDao())
                    }
                }
            }
        }

        private fun populateDatabase(articleDao: Dao) {

        }
    }
}
