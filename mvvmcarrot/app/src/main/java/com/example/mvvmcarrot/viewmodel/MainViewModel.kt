package com.example.mvvmcarrot.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvmcarrot.data.ItemDatabase
import com.example.mvvmcarrot.model.Item
import com.example.mvvmcarrot.repository.ItemRepository
import kotlinx.coroutines.launch



class MainViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData : LiveData<List<Item>>
    private val repository: ItemRepository

    init {
        val itemDao = ItemDatabase.getDatabase(application)!!.itemDao()
        repository = ItemRepository(itemDao)
        readAllData = repository.readAllItem.asLiveData()

    }

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