package com.example.uknotes.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {
    @Insert
    suspend fun addNote(note:Note)

    @Query("SELECT * FROM note ORDER BY id Desc")
    suspend fun getAllNotes():List<Note>

    @Insert
    suspend fun addMultipleNotes(vararg note:Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}