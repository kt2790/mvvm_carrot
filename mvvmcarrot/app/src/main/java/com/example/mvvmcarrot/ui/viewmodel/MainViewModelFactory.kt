package com.example.mvvmcarrot.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmcarrot.MyApplication

class MainViewModelFactory : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MyApplication.repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}