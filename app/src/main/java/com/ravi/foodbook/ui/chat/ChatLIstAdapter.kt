package com.ravi.foodbook.ui.chat

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codingwithme.firebasechat.model.User
import com.ravi.foodbook.R
import de.hdodenhof.circleimageview.CircleImageView


class ChatLIstAdapter(private val context: Context, private val userList: ArrayList<User>, var onClickListner: OnClickListner) :
    RecyclerView.Adapter<ChatLIstAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layoutchat_list_iteam, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.txtUserName.text = user.userName
        Glide.with(context).load(user.profileImage).into(holder.imgUser)

        holder.layoutUser.setOnClickListener {
            var bundle=Bundle()
            bundle.putString("userId",user.userId)
            bundle.putString("userName",user.userName)
            bundle.putString("photo",user.profileImage)

            onClickListner.getdataToMessage(bundle,position)
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val txtUserName: TextView = view.findViewById(R.id.userName)
        val txtTemp: TextView = view.findViewById(R.id.temp)
        val imgUser: CircleImageView = view.findViewById(R.id.userImage)
        val layoutUser: LinearLayout = view.findViewById(R.id.layoutUser)
    }
}