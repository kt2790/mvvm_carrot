package com.example.mvvmcarrot.repository

import com.example.mvvmcarrot.data.ItemDao
import com.example.mvvmcarrot.model.Item
import kotlinx.coroutines.flow.Flow



class ItemRepositoryImpl private constructor (private val itemDao: ItemDao) : ItemRepository{
    override suspend fun addItem(item: Item) {
        itemDao.addItem(item)
    }


    override suspend fun updateItem(item: Item) {
        itemDao.updateItem(item)
    }

    override suspend fun deleteItem(item: Item) {
        itemDao.deleteItem(item)
    }

    override suspend fun updateBookmark(item : Item) {
        if (item.bookmark) updateItem(item.copy(likeCnt = item.likeCnt - 1, bookmark = false)) else updateItem(item.copy(likeCnt = item.likeCnt + 1, bookmark = true))
    }

    override fun readAllItem(): Flow<List<Item>> {
        return itemDao.readAllItem()
    }

    override fun readItem(id: Int) : Flow<Item> {
        return itemDao.readItem(id)
    }

    companion object {
        private var instance : ItemRepositoryImpl? = null

        fun getRepository(itemDao : ItemDao) : ItemRepositoryImpl {
            return instance ?: synchronized(this) {
                instance ?: ItemRepositoryImpl(itemDao).also {
                    instance = it
                }
            }
        }
    }
}