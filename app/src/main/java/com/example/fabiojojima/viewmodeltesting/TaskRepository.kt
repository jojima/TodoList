package com.example.fabiojojima.viewmodeltesting

import android.app.Application
import android.arch.lifecycle.LiveData
import android.os.AsyncTask

class TaskRepository(application: Application) {
    val db: TaskRoomDB? = TaskRoomDB.getDatabase(application)
    val dao: Dao = db!!.taskDao()
    var data: LiveData<List<Task>> = dao.getAllTaskObjects()

    fun getAllTasksObjects(): LiveData<List<Task>> {
        return this.data
    }

    fun insert(task: Task){
        InsertAsyncTask(dao).execute(task)
    }
    fun delete(task: Task){
        DeleteAsyncTask(dao).execute(task)
    }
    fun update(task: Task){
        UpdateAsyncTask(dao).execute(task)
    }

    private class InsertAsyncTask(private val mAsyncTaskDao: Dao): AsyncTask<Task, Void, Void>(){
        override fun doInBackground(vararg params: Task): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }

    private class getAllTasksObjectsAsyncTask(private val mAsyncTaskDao: Dao) : AsyncTask<Task, Void, LiveData<List<Task>>>() {
        override fun doInBackground(vararg params: Task?): LiveData<List<Task>> {
            return mAsyncTaskDao.getAllTaskObjects()
        }

        override fun onPostExecute(result: LiveData<List<Task>>?) {
            super.onPostExecute(result)
        }
    }

    private class UpdateAsyncTask(private val mAsyncTaskDao: Dao) : AsyncTask<Task, Void, Void>() {
        override fun doInBackground(vararg params: Task): Void? {
            mAsyncTaskDao.updateTask(params[0])
            return null
        }
    }

    private class DeleteAsyncTask(private val mAsyncTaskDao : Dao) : AsyncTask<Task, Void, Void>() {
        override fun doInBackground(vararg params: Task): Void? {
            mAsyncTaskDao.deleteTask(params[0])
            return null
        }
    }


}