package com.ravi.foodbook.model

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.ravi.foodbook.OnFoodItemClickListener
import com.ravi.foodbook.R

class FoodModelAdapter(
    private val foodModelList: List<FoodModel>,
    private val onFoodItemClickListener: OnFoodItemClickListener
) : RecyclerView.Adapter<FoodViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_add_folders, parent, false)
        return FoodViewHolder(view, onFoodItemClickListener)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val foodModel: FoodModel = foodModelList[position]
        holder.setData(foodModel)
    }

    override fun getItemCount(): Int {
        return foodModelList.size
    }

}

