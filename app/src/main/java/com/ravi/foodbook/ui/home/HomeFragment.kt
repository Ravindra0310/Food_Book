package com.ravi.foodbook.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.ravi.foodbook.OnFoodItemClickListener
import com.ravi.foodbook.R
import com.ravi.foodbook.databinding.FragmentHomeBinding
import com.ravi.foodbook.model.FoodModel
import com.ravi.foodbook.model.FoodModelAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), OnFoodItemClickListener {

    private lateinit var homeViewModel: HomeViewModel
    lateinit var foodModelAdapter: FoodModelAdapter
    lateinit var databaseReference: DatabaseReference
    private var foodModelList: ArrayList<FoodModel>? = null
    private var _binding: FragmentHomeBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //setting data to recyclerview from firebase
        setRecyclerData()

    }

    private fun setRecyclerData() {

        foodModelList = arrayListOf<FoodModel>()
        databaseReference = FirebaseDatabase.getInstance().reference
        recycler1.setHasFixedSize(true)
        recycler1.layoutManager = LinearLayoutManager(context)
        foodModelAdapter = FoodModelAdapter(foodModelList!!, this)
        recycler1.adapter = foodModelAdapter


        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (i in snapshot.children) {
                        val foodList = i.getValue(FoodModel::class.java)
                        foodModelList?.add(foodList!!)
                    }
                    foodModelAdapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(foodModel: FoodModel) {
        TODO("Not yet implemented")
    }
}