package com.example.fabiojojima.viewmodeltesting

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText

class CreateTask: AppCompatActivity() {
    var EXTRA_REPLY = "com.example.fjojima.viewmodeltesting.REPLY"
    var EXTRA_REPLY_DESC = "com.example.fjojima.viewmodeltesting.REPLY_DESC"

    private var mEditTaskNameView: EditText? = null
    private var mEditTaskDescView: EditText? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)
        mEditTaskNameView = findViewById(R.id.nameText)
        mEditTaskDescView = findViewById(R.id.descText)
        var bttn: Button = this.findViewById(R.id.createTask)

        bttn.setOnClickListener { _ ->
            val replyIntent = Intent()
            if (TextUtils.isEmpty(mEditTaskNameView!!.text) || TextUtils.isEmpty(mEditTaskDescView!!.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                replyIntent.putExtra(EXTRA_REPLY, mEditTaskNameView!!.text.toString())
                replyIntent.putExtra(EXTRA_REPLY_DESC, mEditTaskDescView!!.text.toString())
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

    }

/*    fun createTask(view: View) {
        val editText = findViewById<EditText>(R.id.ALT)//need to modify
        val message = editText.text.toString()
*//*        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }*//*

        val replyIntent = Intent()
        if (TextUtils.isEmpty(mEditTaskNameView!!.editText!!.text.toString())) {
            setResult(Activity.RESULT_CANCELED, replyIntent)
        } else {
            val task = Task(mEditTaskNameView!!.editText!!.text.toString(),mEditTaskDescView!!.editText!!.text.toString(), false)
            replyIntent.putExtra(EXTRA_REPLY, mEditTaskDescView!!.editText!!.text.toString())
            replyIntent.putExtra(EXTRA_REPLY_DESC, mEditTaskNameView!!.editText!!.text.toString())
            setResult(Activity.RESULT_OK, replyIntent)
        }
        finish()
        //startActivity(intent)
    }*/
}