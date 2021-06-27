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

//            val time = foodModel.time?.split(" ")
//            val hr = time?.get(2)?.split(":")
            tvTime.text = foodModel.time
            tvTypeOfFood.text = foodModel.freshness

            if(foodModel.foodType.equals("Sell")){
                tvAmount.text = foodModel.amount
                tvAmountText.visibility = View.VISIBLE
            }else{
                tvAmountText.text = "Free"
                tvAmount.visibility = View.INVISIBLE
            }

            tvContent.text = foodModel.content
            Glide.with(ivFoodImage.context).load(foodModel.foodPic).into(ivFoodImage)

            layout_food_item.setOnClickListener {
                onFoodItemClickListener.onItemClick(foodModel)
            }
        }
    }
}