package com.example.fabiojojima.viewmodeltesting

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

@Dao
interface Dao {
    @Insert
    fun insert (task: Task)

    @Query("DELETE FROM task_table")
    fun clear()

/*    @Query("SELECT * FROM task_table ORDER BY task ASC")
    fun getAllTasksObjects(): MutableLiveData<ArrayList<Task>>*/

    @Query("SELECT * FROM task_table ORDER BY task ASC")
    fun getAllTaskObjects() : LiveData<List<Task>>

    @Delete
    fun deleteTask(task: Task)

    @Update
    fun updateTask(task : Task)

}