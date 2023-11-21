package com.example.a4fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.a4fragments.FragmentUserList.Companion.FRAGMENT_USER_LIST_TAG

class UserEditFragment: Fragment(R.layout.fragment_edit_user) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        val firstName = view.findViewById<EditText>(R.id.editTextFirstName)
        val lastName = view.findViewById<EditText>(R.id.editTextLastName)
        val phone = view.findViewById<EditText>(R.id.editTextPhoneNumber)

        val user = arguments?.getParcelable<User>(USER_DATA_EXTRA)
        if (user != null) {
            firstName.setText(user.firstName)
            lastName.setText(user.lastName)
            phone.setText(user.phoneNumber)

            view.findViewById<Button>(R.id.cancel_button).setOnClickListener {
                parentFragmentManager.popBackStack()

                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }

            view.findViewById<Button>(R.id.agree_button).setOnClickListener {
                val editedUser = user.copy(
                    firstName = firstName.text.toString(),
                    lastName = lastName.text.toString(),
                    phoneNumber = phone.text.toString()
                )
                parentFragmentManager.setFragmentResult(
                    "editUserRequest",
                    bundleOf("updatedUser" to editedUser)
                )
                parentFragmentManager.popBackStack()

                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
    }

    companion object {
        const val USER_LIST_EDIT_FRAGMENT = "USER_LIST_EDIT_FRAGMENT"
        private const val USER_DATA_EXTRA = "USER_DATA_EXTRA"

        fun newInstance(user: User) = UserEditFragment().apply {
            arguments = Bundle().apply {
                putParcelable(USER_DATA_EXTRA, user)
            }
        }
    }
}