package com.example.best_playz.Model

import android.content.Context
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import java.lang.IllegalStateException
import java.util.concurrent.Executors

class LBEntryInDB  {

    val TAG = "xyz"

    private val database: LBEntryDatabase

    private val lbentryDao : LBEntryDao

    private lateinit var cache: List<BE_LBEntry>

    private constructor(context: Context) {

        database = Room.databaseBuilder(context.applicationContext,
            LBEntryDatabase::class.java,
            "lbEntry-database").build()

        lbentryDao = database.lbEntryDao()

        val updateCacheObserver = Observer<List<BE_LBEntry>>{ persons ->
            cache = persons;
            Log.d(TAG, "Update Cache observer notified")
        }
        getAllLiveData().observe(context as LifecycleOwner, updateCacheObserver)
    }

    fun getAllLiveData(): LiveData<List<BE_LBEntry>> = lbentryDao.getAll()


    fun getById(id: Int): BE_LBEntry? {
        cache.forEach { p -> if (p.id == id) return p; }
        return null;
    }

    fun getByPos(pos: Int): BE_LBEntry? {
        if (pos < cache.size)
            return cache[pos]
        return null
    }


    private val executor = Executors.newSingleThreadExecutor()

    fun insert(p: BE_LBEntry) {
        executor.execute{ lbentryDao.insert(p) }
    }

    fun update(p: BE_LBEntry) {
        executor.execute { lbentryDao.update(p) }
    }

    fun delete(p: BE_LBEntry) {
        executor.execute { lbentryDao.delete(p) }
    }

    fun clear() {
        executor.execute { lbentryDao.deleteAll() }
    }


    companion object {
        private var Instance: LBEntryInDB? = null

        fun initialize(context: Context) {
            if (Instance == null)
                Instance = LBEntryInDB(context)
        }

        fun get(): LBEntryInDB {
            if (Instance != null) return Instance!!
            throw IllegalStateException("LBEntry repo not initialized")
        }
    }







}