 package com.example.uknotes.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import com.example.uknotes.R
import com.google.android.material.floatingactionbutton.FloatingActionButton



 class HomeFragment : Fragment() {
    //lateinit var buttonPlus: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_home, container, false)

        return view
    }

     override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         super.onViewCreated(view, savedInstanceState)
         val button = view.findViewById<FloatingActionButton>(R.id.button_add)
         button.setOnClickListener {
             val action=HomeFragmentDirections.actionAddNote()
             Navigation.findNavController(it).navigate(action)
         }
     }

}