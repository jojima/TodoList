package com.example.fabiojojima.viewmodeltesting

import android.os.AsyncTask

class PopulateDbAsync(instance: TaskRoomDB?): AsyncTask<Void, Void, Void>() {
    var dao: Dao = instance!!.taskDao()
    override fun doInBackground(vararg params: Void): Void? {
        dao.clear()
        var task = Task("Hello", "First Task", false)
        dao.insert(task)
        task = Task("World", "seccond task", false)
        dao.insert(task)
        return null
    }
}
