package com.sahsisunny.gittrackr.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sahsisunny.gittrackr.R
import com.sahsisunny.gittrackr.model.UserDetails
import de.hdodenhof.circleimageview.CircleImageView

class UserDetailsAdapter(private val con: Context, private val userDetails: UserDetails) :
    RecyclerView.Adapter<UserDetailsAdapter.UserDetailsViewHolder>() {
    inner class UserDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userImage: CircleImageView = itemView.findViewById(R.id.user_image)
        var nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        var loginTextView: TextView = itemView.findViewById(R.id.loginTextView)
        var bioTextView: TextView = itemView.findViewById(R.id.bioTextView)
        var followersTextView: TextView = itemView.findViewById(R.id.followersTextView)
        var followingTextView: TextView = itemView.findViewById(R.id.followingTextView)
        var githubUrlButton: Button = itemView.findViewById(R.id.githubUrlButton)
        var reposUrlButton: Button = itemView.findViewById(R.id.reposUrlButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserDetailsViewHolder {
        val view = LayoutInflater.from(con).inflate(R.layout.user_details, parent, false)
        return UserDetailsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: UserDetailsViewHolder, position: Int) {
        Glide.with(con).load(userDetails.avatar_url).into(holder.userImage)
        holder.nameTextView.text = userDetails.name
        holder.loginTextView.text = userDetails.login
        holder.bioTextView.text = userDetails.bio
        val followers = "${userDetails.followers} Followers"
        val following = "${userDetails.following} Following"
        holder.followersTextView.text = followers
        holder.followingTextView.text = following
        holder.githubUrlButton.setOnClickListener {
            val intent = android.content.Intent(android.content.Intent.ACTION_VIEW)
            intent.data = android.net.Uri.parse(userDetails.html_url)
            con.startActivity(intent)
        }
        holder.reposUrlButton.setOnClickListener {
            val intent = android.content.Intent(android.content.Intent.ACTION_VIEW)
            val username = userDetails.login
            val repoURL = "https://github.com/$username?tab=repositories"
            intent.data = android.net.Uri.parse(repoURL)
            con.startActivity(intent)
        }
    }

}