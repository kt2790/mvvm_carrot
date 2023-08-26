package com.example.mvvmcarrot.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvmcarrot.data.ItemDatabase
import com.example.mvvmcarrot.model.Item
import com.example.mvvmcarrot.repository.ItemRepository
import kotlinx.coroutines.launch

class DetailViewModel(application : Application, itemId : Int) : ViewModel() {

    var readItem : LiveData<Item>
    private val repository: ItemRepository

    init {
        val itemDao = ItemDatabase.getDatabase(application)!!.itemDao()
        repository = ItemRepository(itemDao)
        readItem = repository.readItem(itemId).asLiveData()
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

    fun updateBookmark(item : Item) {
        if (item.bookmark) updateItem(item.copy(likeCnt = item.likeCnt - 1, bookmark = false)) else updateItem(item.copy(likeCnt = item.likeCnt + 1, bookmark = true))
    }

    fun deleteItem(item : Item) {
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }
}