package com.ravi.foodbook.ui.post

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.ravi.foodbook.model.dao.PostDao
import com.ravi.foodbook.model.data.PostData
import kotlinx.android.synthetic.main.fragment_profile.*

class PostViewModel: ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance()
    private val postDatabaseReference = database.getReference("posts")
    private val user = auth.currentUser


    fun getUserId(): String{
        return auth.currentUser?.uid!!
    }

    fun getUserName(): String {
        return auth.currentUser?.displayName!!
    }

    fun getUserPic(): Uri? {
        var profileUrl: Uri? = null
        user?.let {
            profileUrl = it.photoUrl!!
        }
        return profileUrl
    }

    fun addPost(data: PostData) {
        val newPostRef = postDatabaseReference.push()
        newPostRef.setValue(data)
    }

}