package com.example.uknotes.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note( //This is the table name, the name of class=name of table
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val title:String,
    val not:String

)
