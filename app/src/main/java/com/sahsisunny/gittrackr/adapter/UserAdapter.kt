package com.sahsisunny.gittrackr.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sahsisunny.gittrackr.databinding.ItemUserBinding
import com.sahsisunny.gittrackr.model.User
import com.sahsisunny.gittrackr.userinterface.activities.userDetails.UserDetailsActivity

class UserAdapter(
    private val context: Context,
) : ListAdapter<User, UserAdapter.UserHolder>(DiffCallback) {
    companion object DiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }
    }

    inner class UserHolder(var binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.userName.text = user.login
            Glide.with(context).load(user.avatar_url).into(binding.userImage)
            binding.userViewButton.setOnClickListener {
                val intent = Intent(context, UserDetailsActivity::class.java)
                intent.putExtra("login", user.login)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val binding =
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserHolder(binding)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
        holder.binding.root.setOnClickListener {
            val intent = Intent(context, UserDetailsActivity::class.java)
            intent.putExtra("login", user.login)
            context.startActivity(intent)
        }

    }
}
