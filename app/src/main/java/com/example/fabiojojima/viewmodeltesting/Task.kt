package com.example.fabiojojima.viewmodeltesting

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "task_table")
class Task (@NonNull @PrimaryKey @ColumnInfo(name = "task") val name : String, @ColumnInfo(name = "description") var description: String, @ColumnInfo(name = "completed") var done : Boolean)