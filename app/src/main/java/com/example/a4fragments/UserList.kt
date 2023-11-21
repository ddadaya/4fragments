package com.example.a4fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random
import com.example.a4fragments.UserAdapter

class FragmentUserList : Fragment(R.layout.user_list) {

    private lateinit var recyclerView: RecyclerView

    private var users = mutableListOf<User>()
    private val userAdapter = UserAdapter { user -> showEditUserDialog(user) }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView) ?: return

        users = generateContacts()
        userAdapter.submitList(users)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = userAdapter

        childFragmentManager.setFragmentResultListener("editUserRequest", viewLifecycleOwner) { _, bundle ->
            val updatedUser = bundle.getParcelable<User>("updatedUser")

            if (updatedUser != null) {
                users = userAdapter.currentList.toMutableList()
                val index = users.indexOfFirst { it.id == updatedUser.id }
                if (index != - 1) {
                    users[index] = updatedUser
                    userAdapter.submitList(users)
                }
            }
        }
    }

    private fun generateContacts(): MutableList<User> {
        for (i in 1 .. 10) {
            users.add(User(
                i,
                "Имя$i",
                "Фамилия$i",
                "8-${Random.nextInt(900, 999)}-${Random.nextInt(100, 999)}-${Random.nextInt(10,99)}-${Random.nextInt(10, 99)}"
                )
            )
        }
        return users
    }

    private fun showEditUserDialog(user: User) {
        with(childFragmentManager.beginTransaction()) {
            replace(R.id.container2, UserEditFragment.newInstance(user), UserEditFragment.USER_LIST_EDIT_FRAGMENT)
            addToBackStack(UserEditFragment.USER_LIST_EDIT_FRAGMENT)
            commit()
        }
    }

    companion object {
        const val FRAGMENT_USER_LIST_TAG = "FRAGMENT_USER_LIST_TAG"

        fun newInstance() = FragmentUserList()
    }
}