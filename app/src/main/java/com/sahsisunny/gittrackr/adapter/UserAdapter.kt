package com.sahsisunny.gittrackr.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.sahsisunny.gittrackr.R
import com.sahsisunny.gittrackr.model.UsersItem
import de.hdodenhof.circleimageview.CircleImageView

class UserAdapter(private var con: Context, private var list: List<UsersItem>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userImage: CircleImageView = itemView.findViewById(R.id.rv_user_image)
        var userName: TextView = itemView.findViewById(R.id.rv_user_name)
        var viewButton: MaterialButton = itemView.findViewById(R.id.rv_user_view)
        val userView: RelativeLayout = itemView.findViewById(R.id.rv_user_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(con).inflate(R.layout.user_list_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = list[position]
        holder.userName.text = user.login
        Glide.with(con).load(user.avatar_url).into(holder.userImage)

        holder.viewButton.setOnClickListener {
            val bundle =
                bundleOf("userName" to user.login)
            it.findNavController()
                .navigate(R.id.action_userListFragment_to_userDetailsFragment, bundle)
        }
        holder.userView.setOnClickListener {
            val bundle =
                bundleOf("userName" to user.login)
            it.findNavController()
                .navigate(R.id.action_userListFragment_to_userDetailsFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
