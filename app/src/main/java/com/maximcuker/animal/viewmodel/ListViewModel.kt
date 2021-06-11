package com.maximcuker.animal.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.maximcuker.animal.model.Animal

class ListViewModel(application: Application) : AndroidViewModel(application) {
    val animals by lazy { MutableLiveData<List<Animal>>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }

    fun refresh() {
        getAnimals()
    }

    private fun getAnimals() {
        val al1 = Animal("alligator")
        val al2 = Animal("cat")
        val al3 = Animal("dog")
        val al4 = Animal("elephant")
        val al5 = Animal("bee")
        val al6 = Animal("flamingo")

        val animalList = arrayListOf<Animal>(al1, al2, al3, al4, al5, al6)
        animals.value = animalList
        loadError.value = false
        loading.value = false
    }

}