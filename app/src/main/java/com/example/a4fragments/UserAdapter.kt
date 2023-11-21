package com.example.a4fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class UserAdapter(private val onUserClickListener: (User) -> Unit) :
    ListAdapter<User, UserAdapter.UserViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_user, parent, false)

        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
        holder.itemView.setOnClickListener{ onUserClickListener(user) }
    }

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val randomParam = "?random=${System.currentTimeMillis()}"

        fun bind(user: User) {
            Glide.with(itemView.context)
                .load(user.photo+randomParam)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(itemView.findViewById(R.id.photo))

            itemView.findViewById<TextView>(R.id.textFirstName).text = user.firstName
            itemView.findViewById<TextView>(R.id.textLastName).text = user.lastName
            itemView.findViewById<TextView>(R.id.textPhoneNumber).text = user.phoneNumber
        }
    }

    class UserDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
}

