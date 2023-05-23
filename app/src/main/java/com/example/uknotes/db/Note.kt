package com.example.uknotes.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note( //This is the table name, the name of class=name of table

    val title:String,
    val note:String
){
    @PrimaryKey(autoGenerate = true)
    var id:Int=0
}
