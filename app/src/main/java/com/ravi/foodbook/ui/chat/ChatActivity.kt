package com.ravi.foodbook.ui.chat

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.codingwithme.firebasechat.model.Chat
import com.codingwithme.firebasechat.model.NotificationData
import com.codingwithme.firebasechat.model.PushNotification
import com.codingwithme.firebasechat.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.ravi.foodbook.R
import com.ravi.foodbook.R.drawable.*
import kotlinx.android.synthetic.main.activity_chat.*
import java.util.*
import kotlin.collections.HashMap

class ChatActivity : AppCompatActivity() {
    var firebaseUser = FirebaseAuth.getInstance().currentUser
    var reference: DatabaseReference? = null
    var chatList = ArrayList<Chat>()
    var topic = ""
    lateinit var chatAdapter:ChatMessageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        chatRecyclerView.layoutManager = LinearLayoutManager(this)
        chatAdapter = ChatMessageAdapter(this@ChatActivity, chatList)
        chatRecyclerView.adapter = chatAdapter

        var intent = getIntent()
        var userId = intent.getStringExtra("userId")
        var userName = intent.getStringExtra("userName")
        var userimage = intent.getStringExtra("photo")


        imgBack.setOnClickListener {
            onBackPressed()
        }


        reference = FirebaseDatabase.getInstance().getReference("Users").child(userId!!)




        reference!!.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {

                val user = snapshot.getValue(User::class.java)
                tvUserName.text = user!!.name
                if (user.image == "") {
                    imgProfile.setBackgroundResource(R.drawable.profile_image)
                } else {
                    Glide.with(this@ChatActivity).load(user.image).into(imgProfile)
                }
            }
        })

        btnSendMessage.setOnClickListener {
            var message: String = etMessage.text.toString()

            if (message.isEmpty()) {
                Toast.makeText(applicationContext, "message is empty", Toast.LENGTH_SHORT).show()
                etMessage.setText("")
            } else {
                sendMessage(firebaseUser!!.uid, userId, message)
                etMessage.setText("")
                topic = "/topics/$userId"
                PushNotification(
                    NotificationData( userName!!,message),
                    topic).also {
                  //  sendNotification(it)
                }

            }
        }

        readMessage(firebaseUser!!.uid, userId)
    }

    private fun sendMessage(senderId: String, receiverId: String, message: String) {
        var reference: DatabaseReference? = FirebaseDatabase.getInstance().getReference()

        var hashMap: HashMap<String, String> = HashMap()
        hashMap.put("senderId", senderId)
        hashMap.put("receiverId", receiverId)
        hashMap.put("message", message)

        reference!!.child("Chat").push().setValue(hashMap)

    }

    fun readMessage(senderId: String, receiverId: String) {
        val databaseReference: DatabaseReference =
            FirebaseDatabase.getInstance().getReference("Chat")

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                chatList.clear()
                for (dataSnapShot: DataSnapshot in snapshot.children) {
                    val chat = dataSnapShot.getValue(Chat::class.java)

                    if (chat!!.senderId.equals(senderId) && chat!!.receiverId.equals(receiverId) ||
                        chat!!.senderId.equals(receiverId) && chat!!.receiverId.equals(senderId)
                    ) {
                        chatList.add(chat)
                    }
                }
                chatAdapter.notifyDataSetChanged()

            }
        })
    }

//    private fun sendNotification(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {
//        try {
//            val response = RetrofitInstance.api.postNotification(notification)
//            if(response.isSuccessful) {
//                Log.d("TAG", "Response: ${Gson().toJson(response)}")
//            } else {
//                Log.e("TAG", response.errorBody()!!.string())
//            }
//        } catch(e: Exception) {
//            Log.e("TAG", e.toString())
//        }
//    }

}