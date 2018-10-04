package com.example.fabiojojima.viewmodeltesting

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.widget.Toast
import android.support.v7.widget.RecyclerView.ViewHolder




class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: TaskViewModel
    var EXTRA_REPLY = "com.example.fjojima.viewmodeltesting.REPLY"
    var EXTRA_REPLY_DESC = "com.example.fjojima.viewmodeltesting.REPLY_DESC"
    val NEW_WORD_ACTIVITY_REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(TaskViewModel::class.java)

        val recyclerView : RecyclerView = recyclerview
        val adapter = TaskListAdapter(this, viewModel)
        recyclerView.apply {
            this.adapter = adapter
            this.layoutManager = LinearLayoutManager(this@MainActivity)
        }
        viewModel.getAllTasks().observe(this, Observer { tasks ->
            // Update the cached copy of the words in the adapter.
            adapter.setTasks(tasks)
        })

        val helper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: ViewHolder, target: ViewHolder): Boolean {
                adapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
                return true
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val myTask = adapter.getTaskAtPosition(position)
                Toast.makeText(this@MainActivity, "Deleting " + myTask.name + " from the list", Toast.LENGTH_LONG).show()
                viewModel.deleteTask(myTask)
            }
        })
        helper.attachToRecyclerView(recyclerView)

        addTask.setOnClickListener{ _ ->
            val intent = Intent(this@MainActivity, CreateTask::class.java)
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val task = Task(data!!.getStringExtra(EXTRA_REPLY), data!!.getStringExtra(EXTRA_REPLY_DESC),false)
            viewModel.insert(task)
            val liveData = viewModel.getAllTasks()
        } else {
            Toast.makeText(applicationContext, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
        }
    }
}