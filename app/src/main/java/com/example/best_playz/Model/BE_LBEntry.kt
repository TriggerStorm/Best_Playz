package com.example.best_playz.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class BE_LBEntry (
            @PrimaryKey(autoGenerate = true)
            var id:Int,
            var nickname: String,
            var score: String,
            var date: String,
            var time: String
    ) : Serializable {


    }