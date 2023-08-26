package com.example.mvvmcarrot.repository

import com.example.mvvmcarrot.data.ItemDao
import com.example.mvvmcarrot.model.Item
import kotlinx.coroutines.flow.Flow



class ItemRepository (private val itemDao: ItemDao) {
    val readAllItem : Flow<List<Item>> = itemDao.readAllItem()

    suspend fun addItem(item: Item) {
        itemDao.addItem(item)
    }

    suspend fun updateItem(item: Item) {
        itemDao.updateItem(item)
    }

    suspend fun deleteItem(item: Item) {
        itemDao.deleteItem(item)
    }

    fun readItem(id: Int) : Flow<Item> {
        return itemDao.readItem(id)
    }
}