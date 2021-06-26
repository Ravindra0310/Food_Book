package com.ravi.foodbook.ui.post


import android.app.AlertDialog
import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.ravi.foodbook.R
import com.ravi.foodbook.databinding.FragmentPostBinding
import com.ravi.foodbook.model.data.PostData
import com.ravi.foodbook.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_camera.*
import kotlinx.android.synthetic.main.fragment_post.*


class PostFragment : Fragment() {

    private lateinit var postViewModel: PostViewModel
    private var _binding: FragmentPostBinding? = null

    private final val REQUEST_CODE = 42

    lateinit var uid: String
    lateinit var userName: String
    lateinit var userPic: Uri
    lateinit var foodType: String
    lateinit var content: String
    lateinit var pictureUri: String
    lateinit var location: String
    lateinit var freshness: String
    lateinit var time: String
    lateinit var price: String

    lateinit var navController: NavController

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        postViewModel =
            ViewModelProvider(this).get(PostViewModel::class.java)

        _binding = FragmentPostBinding.inflate(inflater, container, false)
        val root: View = binding.root

        navController = findNavController()

        //uid = postViewModel.getUserId()
        //userName = postViewModel.getUserName()
       // userPic = postViewModel.getUserPic()

        //Glide.with(this).load(userPic).into(profilePic)

        uid = "3"
        userName = "RandomMan1"

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//            navController.navigate(
//                    R.id.action_navigation_post_to_cameraFragment
//                )

        userNameAndPic.setOnClickListener {
            navController.navigate(R.id.action_navigation_post_to_navigation_profile)
        }

        floating_action_button_post.setOnClickListener {

            content = etDescription.editText.toString()
            foodType = etFoodType.editText.toString()
            location = StringBuffer(etLocation1.editText.toString()
                    + etLocation2.editText.toString()
                    + etLocation3.editText.toString()).toString()
            freshness = freshnessSpinner.text.toString()
            time = "now"
            price = etPrice.editText.toString()

            val postData = PostData(
                userName,
                "null",
                content,
                "null",
                foodType,
                location,
                freshness,
                time,
                price
            )
            postViewModel.addPost(postData)

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Thank you for posting")
            builder.setMessage("We will connect you to a receiver soon")
            builder.setPositiveButton("OK", DialogInterface.OnClickListener())
            builder.show()

        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}