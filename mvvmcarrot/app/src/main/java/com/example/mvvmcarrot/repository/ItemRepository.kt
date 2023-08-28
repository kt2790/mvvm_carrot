package com.example.mvvmcarrot.repository

import com.example.mvvmcarrot.model.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    suspend fun addItem(item: Item)

    suspend fun updateItem(item: Item)

    suspend fun deleteItem(item: Item)

    suspend fun updateBookmark(item : Item)

    fun readAllItem() : Flow<List<Item>>

    fun readItem(id: Int) : Flow<Item>
}