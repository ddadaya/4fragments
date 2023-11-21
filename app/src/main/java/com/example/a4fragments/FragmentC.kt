package com.example.a4fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class FragmentC: Fragment(R.layout.fragment_c) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.textView2).text = arguments?.getString(STRING_EXTRA)
        
        view.findViewById<Button>(R.id.fromCtoD).setOnClickListener{
            (requireActivity() as MainActivity).onMoveToDClickListener()
        }

        view.findViewById<Button>(R.id.fromCtoA).setOnClickListener {
            (requireActivity() as MainActivity).onBackToAClickListener()
        }
    }

    companion object {
        const val FRAGMENT_C_TAG = "FRAGMENT_C_TAG"
        private const val STRING_EXTRA = "STRING_EXTRA"

        fun newInstance(s: String) = FragmentC().apply {
            arguments = Bundle().apply {
                putString(STRING_EXTRA, s)
            }
        }
    }
}