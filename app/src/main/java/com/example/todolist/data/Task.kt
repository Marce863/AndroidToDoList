package com.example.todolist.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.DateFormat
import kotlinx.parcelize.Parcelize

/**
 Each item in the recycler view will be represented by one task object which holds the data
 and it's state like if it's checked or not.

 This package contains all classes that hold data or retrieve data from data base/store.

 We need this because it implements methods we need: equals method to compare objects by their data
 Recycler view will compare objects to know if they changed.

 For this app we ask what kind of data will we need?
 name: the name of the To-do task
 important: if this task should be marked as important or not, default to false
 completed: if the task is completed, default to false as a new task shouldn't automatically be done
 created: the time stamp of when a task is created, default to the system time stamp when created

 In Android studio making something parcelable allows it to easily be able to share the data with
 other parts of the app.

 We want to make this class parcelable so we can send this object (To-do task) and pass it to the
 edit task fragment. Otherwise you'd have to send each property separately

 We want to store these task in our SQLite data base. So we can store a lot and query them fast
 Room will create a database table for our task with the Entity(tableName = ...) annotation
 We need a primary key so that each object in our SQLite database has to be identified through ID
 */
@Entity(tableName = "task_table")
@Parcelize
data class Task (
    val name: String,
    val important: Boolean = false,
    val completed: Boolean = false,
    val created: Long = System.currentTimeMillis(),
    @PrimaryKey(autoGenerate = true) val id: Int = 0
    ) : Parcelable {

    // This is in the body because it's dynamically calculated from the calculated created time.
    // Shouldn't be passed to the class and shouldn't be fixed and updated separately.
    // Should mirror the created time

    val createdDateFormatted: String
        // When ever we access this val we can return the formatted time stamp
        get() = DateFormat.getDateTimeInstance().format(created)
}