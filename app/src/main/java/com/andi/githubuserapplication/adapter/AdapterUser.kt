package com.andi.githubuserapplication.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.andi.githubuserapplication.R
import com.andi.githubuserapplication.model.User

class AdapterUser(private val users:ArrayList<User>,val listener:AdapterUser.OnAdapterListener): RecyclerView.Adapter<AdapterUser.ViewHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]
        holder.name.text = user.name
        holder.userName.text = user.username
        holder.company.text = user.company
        holder.img.setImageResource(user.avatar.toInt())

        holder.itemView.setOnClickListener {
            listener.itemClick(user)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
        val name:TextView = itemView.findViewById(R.id.tvName)
        val userName:TextView = itemView.findViewById(R.id.tvUsername)
        val company:TextView = itemView.findViewById(R.id.tvCompany)
        val img:ImageView = itemView.findViewById(R.id.imageView)
    }

    interface OnAdapterListener{
        fun itemClick(user:User)
    }
}
