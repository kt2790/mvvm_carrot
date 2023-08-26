package com.example.mvvmcarrot.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.mvvmcarrot.model.Item
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addItem(item : Item)

    @Update
    suspend fun updateItem(item : Item)

    @Delete
    suspend fun deleteItem(item : Item)

    @Query("SELECT * FROM Item")
    fun readAllItem() : Flow<List<Item>>

    @Query("SELECT * FROM Item WHERE id = :id")
    fun readItem(id: Int) : Flow<Item>

}