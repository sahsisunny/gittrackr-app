package com.sahsisunny.gittrackr.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.button.MaterialButton
import com.sahsisunny.gittrackr.R
import com.sahsisunny.gittrackr.screens.UserDetailsActivity
import com.sahsisunny.gittrackr.model.UsersItem
import de.hdodenhof.circleimageview.CircleImageView

class UserAdapter(private var con: Context, private var list: List<UsersItem>) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userImage: CircleImageView = itemView.findViewById(R.id.rv_user_image)
        var userName: TextView = itemView.findViewById(R.id.rv_user_name)
        var viewButton: MaterialButton = itemView.findViewById(R.id.rv_user_view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(con).inflate(R.layout.user_list_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = list[position]
        holder.userName.text = user.login
        Glide.with(con).load(user.avatar_url).into(holder.userImage)
//        holder.viewButton.setOnClickListener {
//            val intent = android.content.Intent(android.content.Intent.ACTION_VIEW)
//            intent.data = android.net.Uri.parse(user.html_url)
//            con.startActivity(intent)
//        }
        holder.viewButton.setOnClickListener {
            val intent = android.content.Intent(con, UserDetailsActivity::class.java)
            intent.putExtra("login", user.login)
            con.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}