package com.example.fabiojojima.viewmodeltesting

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_new_task.*

class CreateTask: AppCompatActivity() {
    var EXTRA_REPLY = "com.example.fjojima.viewmodeltesting.REPLY"
    var EXTRA_REPLY_DESC = "com.example.fjojima.viewmodeltesting.REPLY_DESC"

//    private lateinit var createVM: CreateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)

        createTask.setOnClickListener { _ ->
            val replyIntent = Intent()
            if (TextUtils.isEmpty(nameText!!.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                replyIntent.putExtra(EXTRA_REPLY, nameText!!.text.toString())
                replyIntent.putExtra(EXTRA_REPLY_DESC, descText!!.text.toString())
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

    }
/*    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)
        createVM

    }*/
}

interface NewTaskCallback {
    fun onTitleEmpty()
}
/*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)

        createTask.setOnClickListener { _ ->
            val replyIntent = Intent()
            if (TextUtils.isEmpty(nameText!!.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                replyIntent.putExtra(EXTRA_REPLY, nameText!!.text.toString())
                replyIntent.putExtra(EXTRA_REPLY_DESC, descText!!.text.toString())
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

    }

}*/
