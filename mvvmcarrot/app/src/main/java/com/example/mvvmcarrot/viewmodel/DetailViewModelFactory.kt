package com.example.mvvmcarrot.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmcarrot.data.ItemDatabase
import com.example.mvvmcarrot.repository.ItemRepositoryImpl

class DetailViewModelFactory() : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            val itemDao = ItemDatabase.getDatabase()!!.itemDao()
            val repository = ItemRepositoryImpl.getRepository(itemDao)
            return DetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}