package com.example.mvvmcarrot.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvmcarrot.model.Item
import com.example.mvvmcarrot.repository.ItemRepository
import kotlinx.coroutines.launch



class MainViewModel(private val repository : ItemRepository) : ViewModel() {

    val readAllData : LiveData<List<Item>> = repository.readAllItem().asLiveData()

    fun addItem(item : Item) {
        viewModelScope.launch {
            repository.addItem(item)
        }
    }

    fun updateItem(item : Item) {
        viewModelScope.launch {
            repository.updateItem(item)
        }
    }

    fun deleteItem(item : Item) {
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }

}