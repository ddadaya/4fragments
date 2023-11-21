package com.example.a4fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment

class FragmentB: Fragment(R.layout.fragment_b) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.fromBtoA).setOnClickListener{
            (requireActivity() as MainActivity).onMoveToAClickListener()
        }

        view.findViewById<Button>(R.id.fromBtoC).setOnClickListener {
            val message = "Hello Fragment C"
            (requireActivity() as MainActivity).onMoveToCClickListener(message)
        }
    }

    companion object{
        const val FRAGMENT_B_TAG = "FRAGMENT_B_TAG"

        fun newInstance() = FragmentB()
    }
}