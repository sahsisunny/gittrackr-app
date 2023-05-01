package com.sahsisunny.gittrackr.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sahsisunny.gittrackr.databinding.ItemUserDetailsBinding
import com.sahsisunny.gittrackr.model.UserDetails

class UserDetailsAdapter(
    private val context: Context,
) : ListAdapter<UserDetails, UserDetailsAdapter.UserDetailsHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<UserDetails>() {
        override fun areItemsTheSame(oldItem: UserDetails, newItem: UserDetails): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: UserDetails, newItem: UserDetails): Boolean {
            return oldItem.id == newItem.id
        }
    }
    inner class UserDetailsHolder(private var binding: ItemUserDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userDetails: UserDetails) {
            Glide.with(context).load(userDetails.avatarUrl).into(binding.userDetailsImage)
            binding.userDetailsName.text = userDetails.name
            binding.userDetailsLogin.text = userDetails.login
            binding.userDetailsBio.text = userDetails.bio
            binding.userDetailsFollowers.text = userDetails.followers.toString()
            binding.userDetailsFollowing.text = userDetails.following.toString()
            binding.userDetailsGitButton.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(userDetails.htmlUrl))
                context.startActivity(intent)
            }
            val reposUrl = "https:/github.com/${userDetails.login}?tab=repositories"
            binding.userDetailsReposButton.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(reposUrl))
                context.startActivity(intent)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDetailsHolder {
        val binding =
            ItemUserDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserDetailsHolder(binding)
    }

    override fun onBindViewHolder(holder: UserDetailsHolder, position: Int) {
        val userDetails = getItem(position)
        holder.bind(userDetails)
    }
}
