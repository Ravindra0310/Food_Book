package com.ravi.foodbook.ui.user

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.ravi.foodbook.BottomNavActivity
import com.ravi.foodbook.MainActivity
import com.ravi.foodbook.R
import com.ravi.foodbook.databinding.FragmentChatBinding
import com.ravi.foodbook.ui.chat.ChatActivity
import com.ravi.foodbook.ui.chat.ChatFragment
import kotlinx.android.synthetic.main.activity_bottom_nav.*
import kotlinx.android.synthetic.main.activity_detailed_post.*
import kotlinx.android.synthetic.main.item_add_folders.view.*

class DetailedPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_post)


        Glide.with(detailivImage.context).load(intent.getStringExtra("userPic")).into(detailivImage)
        detailtvNameWhoIsUploading.text = intent.getStringExtra("userNameHome")
        detailtvAddress.text = intent.getStringExtra("location")

//            val time = foodModel.time?.split(" ")
//            val hr = time?.get(2)?.split(":")
        detailtvTime.text = intent.getStringExtra("time")
        tvTypeOfFood.text = intent.getStringExtra("foodType")

        if(intent.getStringExtra("").equals("Sell")){
            detailtvAmount.text = intent.getStringExtra("price")
            detailtvAmountText.visibility = View.VISIBLE
        }else{
            detailtvAmountText.text = "Free"
            detailtvAmount.visibility = View.INVISIBLE
        }

        detailtvContent.text = intent.getStringExtra("content")
        Glide.with(detailivFoodImage.context).load(intent.getStringExtra("foodPic")).into(detailivFoodImage)


        detailbtnCheckProfile.setOnClickListener {
            val xyz = Intent(this,ChatActivity::class.java)
            xyz.putExtra("userId",intent.getStringExtra("userIdHome"))
            xyz.putExtra("userName",intent.getStringExtra("userNameHome"))
            xyz.putExtra("photo",intent.getStringExtra("userPic"))

            startActivity(xyz)
        }

        detailfloating_action_button_post.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("${intent.getStringExtra("userNameHome")} has received your pickup request.")
            builder.setTitle("Done!!")
            builder.setPositiveButton("Ok") { dialog: DialogInterface?, which: Int ->
                val intent = Intent(this,BottomNavActivity::class.java)
                startActivity(intent)
            }
            // Create the AlertDialog
            val alertDialog: AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.setCancelable(true)
            alertDialog.show()
        }

    }
}