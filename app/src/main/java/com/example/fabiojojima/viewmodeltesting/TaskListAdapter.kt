package com.example.fabiojojima.viewmodeltesting

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.content.DialogInterface
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import java.nio.file.Files.size
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.example.fabiojojima.viewmodeltesting.R.drawable.*
import java.util.*
import java.util.Collections.swap




class TaskListAdapter (context: Context, viewModel: TaskViewModel) : RecyclerView.Adapter<TaskListAdapter.TaskViewHolder>() {

    private val mInflater: LayoutInflater
    private var mTasks: List<Task>? = null // Cached copy of words
    private var itemPos = RecyclerView.NO_POSITION
    private var mTaskViewModel : TaskViewModel = viewModel
    private var con: Context = context

    interface OnItemLongClickListener {
        fun onItemLongClicked(position: Int): Boolean
    }


    inner class TaskViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskTextView : TextView = itemView.findViewById(R.id.taskName)
        val taskDescTextView : TextView = itemView.findViewById(R.id.taskDescription)
        val taskStatus : ImageView = itemView.findViewById(R.id.taskStatus)
        val v : View = itemView
    }

    init {
        mInflater = LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView = mInflater.inflate(R.layout.one_item, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        if (mTasks != null) {
            val current = mTasks!![position]
            holder.taskTextView.text = current.name
            holder.taskDescTextView.text = current.description
            holder.taskStatus.setOnClickListener {
                itemPos = RecyclerView.NO_POSITION
                //mTaskViewModel.deleteTask(current)
                if(current.done){
                    holder.taskStatus.setImageResource(not_done)
                }
                else{
                    holder.taskStatus.setImageResource(newok)
                }
                current.done = !current.done
            }
            holder.v.setOnLongClickListener{
                val myTask = this.getTaskAtPosition(position)
                val dialogBuilder = AlertDialog.Builder(con)
                dialogBuilder.setMessage("Do you want to delete " + myTask.name + " ?")
                        // if the dialog is cancelable
                        .setCancelable(true)
                        // positive button text and action
                        .setPositiveButton("Proceed") { _, _ -> mTaskViewModel.deleteTask(myTask)
                        }
                        // negative button text and action
                        .setNegativeButton("Cancel") { _, _ -> //finish()
                        }
                val alert = dialogBuilder.create()
                alert.setTitle("Delete Task")
                alert.show()
                return@setOnLongClickListener true
            }

            //holder.view.setOnLongClickListener()
        } else {
            // Covers the case of data not being ready yet.
            holder.taskTextView.text = "No Task"
            /*holder.taskCheckBox.isChecked = false*/
        }
    }

    internal fun setTasks(tasks: List<Task>?) {
        mTasks = tasks
        notifyDataSetChanged()
    }

    // getItemCount() is called many times, and when it is first called,
    // mTasks has not been updated (means initially, it's null, and we can't return null).
    override fun getItemCount(): Int {
        return if (mTasks != null)
            mTasks!!.size
        else
            0
    }
    fun getTaskAtPosition(position : Int) : Task {
        return mTasks!![position]
    }

    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(mTasks, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(mTasks, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)
        return true
    }
}