package com.example.fabiojojima.viewmodeltesting

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
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
            if (TextUtils.isEmpty(mEditTaskNameView!!.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                replyIntent.putExtra(EXTRA_REPLY, mEditTaskNameView!!.text.toString())
                replyIntent.putExtra(EXTRA_REPLY_DESC, mEditTaskDescView!!.text.toString())
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

    }
}
