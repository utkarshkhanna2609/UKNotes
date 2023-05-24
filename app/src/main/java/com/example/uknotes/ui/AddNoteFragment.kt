package com.example.uknotes.ui

import android.app.AlertDialog
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
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
    private var  note:Note?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment

        val view=inflater.inflate(R.layout.fragment_add_note, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        arguments?.let{
            note=AddNoteFragmentArgs.fromBundle(it).note
            view.findViewById<EditText>(R.id.title).setText(note?.title)
            view.findViewById<EditText>(R.id.note).setText(note?.note)
        }

        val button = view.findViewById<FloatingActionButton>(R.id.button_save)
        button.setOnClickListener {
            val noteTitle=view.findViewById<EditText>(R.id.title).text.toString().trim()
            val noteBody=view.findViewById<EditText>(R.id.note).text.toString().trim()

            if(noteTitle.isEmpty()){
                view.findViewById<EditText>(R.id.title).error="Title Requried"
                view.findViewById<EditText>(R.id.title).requestFocus()
                context?.toast("It's Not looking good bruv💀Please enter title")
                return@setOnClickListener
            }
            if(noteBody.isEmpty()){
                view.findViewById<EditText>(R.id.note).error="Note Requried"
                view.findViewById<EditText>(R.id.note).requestFocus()
                return@setOnClickListener
            }
            launch{

                context?.let{
                    val mNote= Note(noteTitle,noteBody)
                    if(note==null){
                        NoteDatabase(it).getNotesDao().addNote(mNote)
                        it.toast("Note Saved!")
                    }else{
                        mNote.id=note!!.id
                        NoteDatabase(it).getNotesDao().updateNote(mNote)
                        it.toast("Note Updated!")
                    }
                    val action=AddNoteFragmentDirections.actionSaveNote()
                    Navigation.findNavController(view).navigate(action)
                }
            }



        }
    }
    private fun deleteNote(){
        AlertDialog.Builder(context).apply{
            setTitle("Are you sure bruv?")
            setMessage("You cant undo this action Bruv!")
            setPositiveButton("YES"){_,_->
                launch {
                    NoteDatabase(context).getNotesDao().deleteNote(note!!)
                    val action=AddNoteFragmentDirections.actionSaveNote()
                    Navigation.findNavController(requireView()).navigate(action)
                }
            }
            setNegativeButton("NO"){_,_->

            }
        }.create().show()
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu,menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete->if(note!=null){
                deleteNote()
            }else{
                context?.toast("It's Not looking good bruv💀")
            }
        }
        return super.onOptionsItemSelected(item)
    }


}