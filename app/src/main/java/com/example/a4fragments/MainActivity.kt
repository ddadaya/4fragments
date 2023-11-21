package com.example.a4fragments

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.example.a4fragments.FragmentA.Companion.FRAGMENT_A_TAG

class MainActivity : AppCompatActivity(),MovesListener { //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(supportFragmentManager.findFragmentByTag(FRAGMENT_A_TAG) == null){
            with(supportFragmentManager.beginTransaction()){
                replace(R.id.container, FragmentA.newInstance(), FragmentA.FRAGMENT_A_TAG)
                addToBackStack(FragmentA.FRAGMENT_A_TAG)
                commit()

            }
        }
    }

    companion object{
        fun newIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun onMoveToBClickListener() {
        with(supportFragmentManager.beginTransaction()) {
            replace(R.id.container,FragmentB.newInstance(),FragmentB.FRAGMENT_B_TAG)
            addToBackStack(FragmentB.FRAGMENT_B_TAG)
            commit()
        }
    }

    override fun onMoveToCClickListener(s: String) {
        with(supportFragmentManager.beginTransaction()) {
            replace(R.id.container,FragmentC.newInstance(s),FragmentC.FRAGMENT_C_TAG)
            addToBackStack(FragmentC.FRAGMENT_C_TAG)
            commit()
        }
    }

    override fun onMoveToDClickListener() {
        with(supportFragmentManager.beginTransaction()) {
            replace(R.id.container, FragmentD.newInstance(), FragmentD.FRAGMENT_D_TAG)
            addToBackStack(FragmentD.FRAGMENT_D_TAG)
            commit()
        }
    }

    override fun onMoveToAClickListener() {
        with(supportFragmentManager.beginTransaction()) {
            replace(R.id.container,FragmentA.newInstance(),FragmentA.FRAGMENT_A_TAG)
            addToBackStack(FragmentA.FRAGMENT_A_TAG)
            commit()
        }
    }

    override fun onBackToAClickListener() {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        with(supportFragmentManager.beginTransaction()) {
            replace(R.id.container,FragmentA.newInstance(),FragmentA.FRAGMENT_A_TAG)

            addToBackStack(FragmentA.FRAGMENT_A_TAG)
            commit()
        }
    }

    override fun onBackToBClickListener() {
        supportFragmentManager.popBackStack(FragmentB.FRAGMENT_B_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        with(supportFragmentManager.beginTransaction()) {
            replace(R.id.container,FragmentB.newInstance(),FragmentB.FRAGMENT_B_TAG)

            addToBackStack(FragmentB.FRAGMENT_B_TAG)
            commit()
        }
    }

    override fun onUserListClickListener() {
        with(supportFragmentManager.beginTransaction()) {
            replace(R.id.container, FragmentUserList.newInstance(), FragmentUserList.FRAGMENT_USER_LIST_TAG)
            addToBackStack(FragmentUserList.FRAGMENT_USER_LIST_TAG)
            commit()
        }
    }

    override fun onBackPressed() {
        if(supportFragmentManager.backStackEntryCount==1){
            Toast.makeText(this,"это единственный фрагмент в стеке",Toast.LENGTH_SHORT).show()
        } else {
            super.onBackPressed()
        }
    }
}