package com.example.uknotes.ui

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.namespace.R
import com.example.uknotes.db.Note

class NotesAdapter (val notes:List<Note>): RecyclerView.Adapter<NotesAdapter.NoteViewHolder>(){

    class NoteViewHolder(val view:View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        return NoteViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.note_layout, parent, false)
        )
    }

    override fun getItemCount()= notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        holder.view.findViewById<TextView>(R.id.text_view_title).text=notes[position].title
        holder.view.findViewById<TextView>(R.id.text_view_note).text=notes[position].note
    }
}