package com.ravi.foodbook

import com.ravi.foodbook.model.FoodModel

interface OnFoodItemClickListener {
    fun onItemClick(foodModel: FoodModel)
}