package com.example.jobedin.repository

import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import com.ravi.foodbook.BottomNavActivity
import com.ravi.foodbook.model.data.PostData



class LinkedInRepository() {
    private val currentUserUid = FirebaseAuth.getInstance().currentUser?.uid ?: "nan"
    private val database = Firebase.database
    private val postDatabaseReference = database.getReference("posts")
    var uploadTask: UploadTask? = null
    var firebaseStorage: FirebaseStorage = FirebaseStorage.getInstance()
    var storageReference = firebaseStorage.getReference("post_images")


    fun addPost(
        data: PostData
    ) {
        val newPostRef = postDatabaseReference.push()
        newPostRef.setValue(data)
        BottomNavActivity.tempPicPath = null
        BottomNavActivity.tempFileExt = null
    }

    fun uploadMedia(tempPicPath: Uri?, type: String?, postItem: PostData) {
        val reference = storageReference!!.child(
            System.currentTimeMillis().toString() + "." + type
        )

        uploadTask = reference.putFile(tempPicPath!!)
        var downloadUrl: String? = null
        val uriTask = uploadTask!!.continueWithTask { task ->
            if (!task.isSuccessful) {
                throw task.exception!!
            }
            reference.downloadUrl
        }.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val type = BottomNavActivity.tempFileExt;
                downloadUrl = task.result.toString()
                if (type == "jpg" || type == "bmp" || type == "jpeg" || type == "png") {
                    val item = PostData(
                        content = postItem.content,
                        foodType = postItem.foodType,
                        location=postItem.location,
                        freshness=postItem.freshness,
                        price=postItem.price,
                        time = postItem.time,
                        userName = postItem.userName,
                        foodPic = downloadUrl,
                        userPic = postItem.userPic,
                        uid= currentUserUid
                    )
                    addPost(item)
                } else {
                    addPost(postItem)
                }
            }
        }.addOnFailureListener {
            Log.d("TAG", "uploadMedia: Something went wrong + ${it.localizedMessage}")
        }
    }

    private val userDatabaseReference = Firebase.database.getReference("Users")




}