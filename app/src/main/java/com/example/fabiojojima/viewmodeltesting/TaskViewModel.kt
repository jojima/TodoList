package com.example.fabiojojima.viewmodeltesting

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

class TaskViewModel(application: Application): AndroidViewModel(application){
    private val mRepository: TaskRepository = TaskRepository(application)
    private val allTasks: LiveData<List<Task>> = mRepository.getAllTasksObjects()

    fun getAllTasks(): LiveData<List<Task>> {
        return allTasks
    }

    fun insert(task: Task) {
        mRepository.insert(task)
    }

    fun deleteTask(task : Task) {
        mRepository.delete(task)
    }

    fun update(task : Task) {
        mRepository.update(task)
    }
}