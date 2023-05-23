package com.example.uknotes.ui

import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.namespace.R

import com.example.uknotes.db.Note
import com.example.uknotes.db.NoteDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch


class AddNoteFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_add_note, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<FloatingActionButton>(R.id.button_save)
        button.setOnClickListener {
            val noteTitle=view.findViewById<EditText>(R.id.title).toString().trim()
            val noteBody=view.findViewById<EditText>(R.id.note).toString().trim()

            if(noteTitle.isEmpty()){
                view.findViewById<EditText>(R.id.title).error="Title Requried"
                view.findViewById<EditText>(R.id.title).requestFocus()
                return@setOnClickListener
            }
            if(noteBody.isEmpty()){
                view.findViewById<EditText>(R.id.note).error="Note Requried"
                view.findViewById<EditText>(R.id.note).requestFocus()
                return@setOnClickListener
            }
            launch{
                val note= Note(noteTitle,noteBody)
                context?.let{
                    NoteDatabase(it).getNotesDao().addNote(note)
                    it.toast("Note Saved")
                }
            }



        }
    }


}