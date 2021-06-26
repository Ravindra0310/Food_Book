package com.ravi.foodbook.model

data class FoodModel(
    val userName: String?,
    val userPic: String?,
    val content: String?,
    val foodPic: String?,
    val foodType: String?,
    val location: String?,
    val freshness: String?,
    val time: String?,
    val amount: String?
) {
    val foodModel = FoodModel("", "", "", "", "", "", "", "", "")
}
