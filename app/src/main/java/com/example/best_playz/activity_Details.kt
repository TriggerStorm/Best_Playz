package com.example.best_playz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import androidx.lifecycle.Observer
import com.example.best_playz.Model.BE_LBEntry

class activity_Details : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__details)


        val lV = findViewById<ListView>(R.id.lv_Leaderbord)
        val extras: Bundle = intent.extras!!
        val LBE = extras.getSerializable("LBE") as BE_LBEntry

        val updateGUIObserver = Observer<List<BE_LBEntry>> { LBE ->
            val asStrings = LBE.map { f -> "${f.id}, ${f.nickname}, ${f.score}, ${f.date}" }
            val adapter: ListAdapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                asStrings.toTypedArray()
            )
        }
    }
}