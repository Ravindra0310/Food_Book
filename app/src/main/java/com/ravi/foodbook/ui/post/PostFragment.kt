package com.ravi.foodbook.ui.post


import android.app.AlertDialog
import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.jobedin.repository.LinkedInRepository
import com.google.firebase.auth.FirebaseAuth
import com.ravi.foodbook.BottomNavActivity
import com.ravi.foodbook.R
import com.ravi.foodbook.databinding.FragmentPostBinding
import com.ravi.foodbook.model.data.PostData
import kotlinx.android.synthetic.main.fragment_post.*


class PostFragment : Fragment() {
    lateinit var repository: LinkedInRepository
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
            repository= LinkedInRepository()
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        val root: View = binding.root

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()


        navController = findNavController()

//        uid = postViewModel.getUserId()
//        userName = postViewModel.getUserName()
//        userPic = postViewModel.getUserPic()
//
//        context?.let { Glide.with(it).load(userPic).into(postProfilePic) }
//        postProfileName.text = userName

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userImage = FirebaseAuth.getInstance().currentUser?.photoUrl.toString()
        val userName = FirebaseAuth.getInstance().currentUser?.displayName ?: "nan"
        val userUid = FirebaseAuth.getInstance().currentUser?.uid ?: "nan"

        val auth = FirebaseAuth.getInstance()

        val user = auth!!.currentUser
        user?.let {
            val profileUrl = it.photoUrl
            //userName = it.displayName!!
            postProfileName.text = "$userName,"
            Glide.with(postProfilePic1.context!!).load(profileUrl).into(postProfilePic1)

        }

        btnTakePicture.setOnClickListener{
                        navController.navigate(
                    R.id.action_navigation_post_to_cameraFragment
                )
        }

        postUserNameAndPic.setOnClickListener {
            navController.navigate(R.id.action_navigation_post_to_navigation_profile)
        }

        btnSell.setOnClickListener {
            val layoutAnimationController: LayoutAnimationController =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation)
            priceInputLayout?.layoutAnimation = layoutAnimationController
            priceInputLayout.visibility = View.VISIBLE
        }

        btnDonate.setOnClickListener {
            priceInputLayout.visibility = View.GONE
        }

        typeFresh.setOnClickListener {
            freshness = "Fresh"
        }

        typeWaste.setOnClickListener {
            freshness = "Waste"
        }

        typeCold.setOnClickListener {
            freshness = "Gone Cold"
        }

        btnSell.setOnClickListener {
            priceInputLayout.visibility = View.VISIBLE
            foodType = "Sell"
        }

        btnDonate.setOnClickListener {
            foodType = "Donate"
            priceInputLayout.visibility = View.GONE
        }

        floating_action_button_post.setOnClickListener {

            content = etDescription.text.toString()

            location = StringBuffer(etLocation1.text.toString()
                    + etLocation2.text.toString()
                    + etLocation3.text.toString()).toString()
            //freshness = "freshnessSpinner.text.toString()"
            time = java.util.Calendar.getInstance().time.toString()

            if(foodType.equals("Sell"))
                price = etPrice.text.toString()
            else
                price = "null"

            pictureUri= BottomNavActivity.tempFileExt!!

                val type = BottomNavActivity.tempFileExt;
                if (type == "jpg" || type == "bmp" || type == "jpeg" || type == "png") {
                    repository.uploadMedia(
                        BottomNavActivity.tempPicPath, type, PostData(
                            content = content,
                            foodType = foodType,
                            location = location,
                            freshness = freshness,
                            price = price,
                            time = time,
                            userName = userName,
                            foodPic = pictureUri,
                            userPic = userImage,
                            uid = userUid
                        )
                    )
                }else{
                    repository.addPost(
                        PostData(
                            content = content,
                            foodType = foodType,
                            location = location,
                            freshness = freshness,
                            price = price,
                            time = time,
                            userName = userName,
                            foodPic = "",
                            userPic = userImage,
                            uid = userUid
                        )
                    )
                }
//            val postData = PostData(
//                userName,
//                "null",
//                content,
//                "null",
//                foodType,
//                location,
//                freshness,
//                time,
//                price
//            )
//            postViewModel.addPost(postData)



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
        if(BottomNavActivity.tempPicPath!=null){
            imageviewPost.visibility=View.VISIBLE
            imageviewPost.setImageURI(BottomNavActivity.tempPicPath)
        }
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}