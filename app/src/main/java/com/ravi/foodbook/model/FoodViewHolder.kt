package com.ravi.foodbook.model

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ravi.foodbook.OnFoodItemClickListener
import kotlinx.android.synthetic.main.item_add_folders.view.*

class FoodViewHolder(
    itemView: View,
    private val onFoodItemClickListener: OnFoodItemClickListener
) :
    RecyclerView.ViewHolder(itemView) {

    fun setData(foodModel: FoodModel) {
        itemView.apply {
            Glide.with(ivImage.context).load(foodModel.userPic).into(ivImage)
            tvNameWhoIsUploading.text = foodModel.userName
            tvAddress.text = foodModel.location
            tvTime.text = foodModel.time
            tvTypeOfFood.text = foodModel.foodType
            tvAmount.text = foodModel.freshness
            tvContent.text = foodModel.content
            tvFreshness.text = foodModel.freshness
            Glide.with(ivFoodImage.context).load(foodModel.foodPic).into(ivFoodImage)

            layout_food_item.setOnClickListener {
                onFoodItemClickListener.onItemClick(foodModel)
            }
        }
    }
}