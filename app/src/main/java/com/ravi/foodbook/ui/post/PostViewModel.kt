package com.ravi.foodbook.ui.post

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.ravi.foodbook.model.dao.PostDao
import com.ravi.foodbook.model.data.PostData

class PostViewModel: ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance()
    private val postDatabaseReference = database.getReference("posts")

    fun getUserId(): String{
        return auth.currentUser?.uid!!
    }

    fun getUserName(): String {
        return auth.currentUser?.displayName!!
    }

    fun getUserPic(): Uri {
        return auth.currentUser?.photoUrl!!
    }

    fun addPost(data: PostData) {
        val newPostRef = postDatabaseReference.push()
        newPostRef.setValue(data)
    }

}