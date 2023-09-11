package com.example.mvvmcarrot.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvmcarrot.model.Item
import com.example.mvvmcarrot.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch



class DetailViewModel(private val repository : ItemRepository) : ViewModel() {
    lateinit var item : LiveData<Item>

    fun readItem(itemId : Int) : Flow<Item> {
        return repository.readItem(itemId)
    }

    fun initState(itemId : Int) {
        item = repository.readItem(itemId).asLiveData()
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
