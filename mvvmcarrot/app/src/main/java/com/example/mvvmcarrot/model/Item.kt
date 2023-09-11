package com.example.mvvmcarrot.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item (
        @PrimaryKey(autoGenerate = true)
        var id : Int = 0,
        val img : String,
        val title : String,
        val content : String,
        val seller : String,
        val price : String,
        val address : String,
        val likeCnt : Int,
        val chatCnt : Int,
        val bookmark : Boolean
        )