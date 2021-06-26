package com.ravi.foodbook.ui.home

import androidx.lifecycle.*
import com.ravi.foodbook.model.FoodModel

class HomeViewModel : ViewModel() {

    val list = MutableLiveData<ArrayList<FoodModel>>()
    var newList = arrayListOf<FoodModel>()

    fun add(foodModel: FoodModel) {
        newList.add(foodModel)
        list.value = newList
    }

    // USE THIS FUN FOR REMOVING DATA PURPOSE

//    fun remove(foodModel: FoodModel){
//        newList.remove(foodModel)
//        list.value = newList
//    }

}

class HomeViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel() as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }

}