package com.example.fabiojojima.viewmodeltesting

import android.content.DialogInterface
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment






class DelConfirmDialog : DialogFragment() {
    companion object {
        fun onCreateDialog(delConfirmDialog: DelConfirmDialog, savedInstanceState: Bundle): Dialog {
            val builder = AlertDialog.Builder(delConfirmDialog.activity)
            builder.setMessage("Confirm delete of this task?").setPositiveButton("Task deleted") { dialog, id ->
            }.setNegativeButton("Delete cancelled", { dialog, id ->})
            // Create the AlertDialog object and return it
            builder.setPositiveButton("Delete") { dialog, id ->
                // User clicked OK button
            }
            builder.setNegativeButton("Cancel") { dialog, id ->
                // User cancelled the dialog
            }
            return builder.create()
        }
    }
}