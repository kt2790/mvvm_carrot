package com.example.mvvmcarrot

import android.app.Application
import com.example.mvvmcarrot.data.ItemDatabase
import com.example.mvvmcarrot.repository.ItemRepositoryImpl

class MyApplication : Application() {
    init {
        instance = this
    }

    companion object {
        lateinit var instance: Application
        private val database by lazy { ItemDatabase.getDatabase(instance) }
        val repository by lazy { ItemRepositoryImpl.getRepository(database.itemDao())}
    }
}