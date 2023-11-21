package com.example.a4fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment

class FragmentA: Fragment(R.layout.fragment_a) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.fromAtoB).setOnClickListener{
            (requireActivity() as MainActivity).onMoveToBClickListener()
        }

        view.findViewById<Button>(R.id.secondTask).setOnClickListener {
            (requireActivity() as MainActivity).onUserListClickListener()
        }
    }

    companion object{
        const val FRAGMENT_A_TAG = "FRAGMENT_A_TAG"

        fun newInstance() = FragmentA()
    }
}