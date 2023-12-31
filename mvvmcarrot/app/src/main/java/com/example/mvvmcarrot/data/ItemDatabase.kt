package com.example.mvvmcarrot.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmcarrot.model.Item

@Database(entities = [Item::class], version = 8, exportSchema = false)
abstract class ItemDatabase : RoomDatabase() {

    abstract fun itemDao() : ItemDao

    companion object {

        @Volatile
        private var instance : ItemDatabase? = null

        fun getDatabase(context: Context) : ItemDatabase {
            return instance ?: synchronized(ItemDatabase::class) {
                instance ?: Room.databaseBuilder(
                    context,
                    ItemDatabase::class.java,
                    "item_database"
                ).build().also {
                    instance = it
                }
            }
        }
    }

}