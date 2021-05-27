package com.example.best_playz.Model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BE_LBEntry::class], version=2)
abstract class LBEntryDatabase  : RoomDatabase() {

    abstract fun lbEntryDao(): LBEntryDao
}