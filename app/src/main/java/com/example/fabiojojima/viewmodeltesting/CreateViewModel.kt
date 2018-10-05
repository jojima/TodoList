package com.example.fabiojojima.viewmodeltesting

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.widget.EditText
import com.example.fabiojojima.viewmodeltesting.R.id.nameText


class CreateViewModel(application: Application): AndroidViewModel(application), NewTaskCallback{
    val changeNotifier = MutableLiveData<Task>()
    private val repository = TaskRepository(application)
    fun insert(title: String, description: String) {
        val task = Task(title, description)
        repository.insert(task)
    }
    fun canSaveNewToDoItem(text: String): Boolean {
        return if (text.isBlank()) {
//            callback?.onTitleEmpty()
            false
        } else true
    }

    override fun onTitleEmpty() {
        nameText!!.toString().isEmpty()
    }
}