package com.ravi.foodbook.ui.profile

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.ravi.foodbook.LoginActivity
import com.ravi.foodbook.MainActivity
import com.ravi.foodbook.R
import com.ravi.foodbook.databinding.FragmentHomeBinding
import com.ravi.foodbook.databinding.FragmentProfileBinding
import com.ravi.foodbook.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.item_add_folders.view.*

class ProfileFragment : Fragment() {
    private lateinit var profileViewModel: ProfileViewModel
    private var _binding: FragmentProfileBinding? = null
    var auth: FirebaseAuth? = null
    lateinit var postReference: DatabaseReference

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //todo view
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()


        val user = auth!!.currentUser
        user?.let {
            tvUserName.text = it.displayName
            tvUserDescription.text = "Im restaurant owner"
            val profileUrl = it.photoUrl
            Glide.with(ivUserProfileImage.context).load(profileUrl).into(ivUserProfileImage)

            tvUserLocation.text = "Mumbai"
        }

        val currentUserId = auth!!.currentUser?.uid
        postReference = FirebaseDatabase.getInstance().getReference("posts")
        if (currentUserId != null) {
            postReference.orderByChild("uid")
                .startAt(currentUserId).endAt(currentUserId + "uf8ff")
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            val countPost = snapshot.childrenCount
                            tvActivePosts.text = "${countPost.toString()} active posts"
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        tvActivePosts.text = "0 active posts"
                    }

                })
        }


        logOut.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Wait a sec...")
            builder.setMessage(
                "Siging out from the device?\n\nYour data on this device will be deleted, " +
                        "but you can always get them back once you sign in again."
            )
            builder.setPositiveButton("SIGN OUT") { dialog: DialogInterface?, which: Int ->
                auth!!.signOut()
                val intent = Intent(activity, LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
            builder.setNegativeButton("CANCEL") { dialogInterface, which ->

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