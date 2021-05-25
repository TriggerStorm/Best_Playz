package com.example.best_playz.Model

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface LBEntryDao {

    @Query("SELECT * from BE_LBEntry  order by score DESC")
    fun getAll(): LiveData<List<BE_LBEntry>>

    @Query("SELECT nickname from BE_LBEntry order by nickname")
    fun getAllNames(): LiveData<List<String>>

    @Query("SELECT * from BE_LBEntry where id = (:id)")
    fun getById(id: Int): LiveData<BE_LBEntry>

    @Insert
    fun insert(p: BE_LBEntry)

    @Update
    fun update(p: BE_LBEntry)

    @Delete
    fun delete(p: BE_LBEntry)

    @Query("DELETE from BE_LBEntry")
    fun deleteAll()
}