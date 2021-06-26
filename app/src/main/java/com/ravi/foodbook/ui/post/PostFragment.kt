package com.ravi.foodbook.ui.post


import android.app.AlertDialog
import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.ravi.foodbook.MainActivity
import com.ravi.foodbook.R
import com.ravi.foodbook.databinding.FragmentPostBinding
import com.ravi.foodbook.model.data.PostData
import kotlinx.android.synthetic.main.activity_bottom_nav.*
import kotlinx.android.synthetic.main.fragment_post.*


class PostFragment : Fragment() {

    private lateinit var postViewModel: PostViewModel
    private var _binding: FragmentPostBinding? = null

    private final val REQUEST_CODE = 42

    private lateinit var uid: String
    private lateinit var userName: String
    private lateinit var userPic: Uri
    private lateinit var foodType: String
    private lateinit var content: String
    private lateinit var pictureUri: String
    private lateinit var location: String
    private lateinit var freshness: String
    private lateinit var time: String
    private lateinit var price: String

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

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()


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

        btnTakePicture.setOnClickListener{
                        navController.navigate(
                    R.id.action_navigation_post_to_cameraFragment
                )
        }

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

            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Thank you for posting")
            builder.setMessage("We will connect you to a receiver soon")
            builder.setPositiveButton("Ok") { dialog: DialogInterface?, which: Int ->
                navController.navigate(R.id.action_navigation_post_to_navigation_home)
            }
            // Create the AlertDialog
            val alertDialog: AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.setCancelable(true)
            alertDialog.show()

        }
    }


    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}