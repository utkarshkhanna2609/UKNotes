 package com.example.uknotes.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.namespace.R
import com.example.uknotes.db.NoteDatabase

import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch


 class HomeFragment : BaseFragment() {
    //lateinit var buttonPlus: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_home, container, false)

        return view
    }

     @SuppressLint("CutPasteId")
     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)
         view.findViewById<RecyclerView>(R.id.recycler_view_home).setHasFixedSize(true)
         view.findViewById<RecyclerView>(R.id.recycler_view_home).layoutManager=StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
         launch {
             context?.let{
                 val notes=NoteDatabase(it).getNotesDao().getAllNotes()
                 view.findViewById<RecyclerView>(R.id.recycler_view_home).adapter=NotesAdapter(notes)
             }
         }

         val button = view.findViewById<FloatingActionButton>(R.id.button_add)
         button.setOnClickListener {
             val action=HomeFragmentDirections.actionAddNote()
             Navigation.findNavController(it).navigate(action)
         }
     }

}