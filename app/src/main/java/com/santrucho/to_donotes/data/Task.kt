package com.santrucho.to_donotes.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import java.util.*


@Entity(tableName = "tasks")
data class Task constructor(
    @ColumnInfo(name = "title") var title: String="",
    @ColumnInfo(name = "description")var description: String ="",
    @ColumnInfo(name = "completed")var isCompleted : Boolean = false,
    @ColumnInfo(name = "entryId")var idTask : String = UUID.randomUUID().toString()

) {

    val titleForList : String
        get() = title.ifEmpty { description }

    val isActive
        get() = !isCompleted

    val isEmpty
        get() = title.isEmpty() ||description.isEmpty()
}