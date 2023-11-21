package com.example.a4fragments

interface MovesListener {
    fun onMoveToBClickListener()

    fun onMoveToCClickListener(s: String)

    fun onMoveToDClickListener()

    fun onMoveToAClickListener()

    fun onBackToAClickListener()

    fun onBackToBClickListener()

    fun onUserListClickListener()
}