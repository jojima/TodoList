package com.example.fabiojojima.viewmodeltesting

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(
        entities = [Task::class],
        version = 1
)
abstract class TaskRoomDB: RoomDatabase() {

    abstract fun taskDao(): Dao

    companion object {
        private var instance: TaskRoomDB? = null

        private val sRoomDatabaseCallback = object : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                //PopulateDbAsync(instance).execute()
            }
        }

        fun getDatabase(context: Context): TaskRoomDB? {
            if (instance == null){
                synchronized(TaskRoomDB::class.java) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(context.applicationContext, TaskRoomDB::class.java!!, "task_database").addCallback(sRoomDatabaseCallback).addCallback(sRoomDatabaseCallback).build()
                    }
                }
            }
            return instance
        }
    }
}