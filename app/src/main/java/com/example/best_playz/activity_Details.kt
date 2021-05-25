package com.example.best_playz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.best_playz.Model.BE_LBEntry

class activity_Details : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__details)


        val lV = findViewById<ListView>(R.id.lv_Leaderbord)
        val extras: Bundle = intent.extras!!
        val LBE = extras.getSerializable("LBE") as BE_LBEntry
        val tvName = findViewById<TextView>(R.id.Tv_nickname)
        val tvScore = findViewById<TextView>(R.id.Tv_score)
        val tvTime = findViewById<TextView>(R.id.Tv_time)
        val tvDate = findViewById<TextView>(R.id.Tv_date)

      /*  val updateGUIObserver = Observer<List<BE_LBEntry>> { LBE ->
            val asStrings = LBE.map { f -> "${f.id}, ${f.nickname}, ${f.score}, ${f.date},${f.time}  " }
            val adapter: ListAdapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                asStrings.toTypedArray()
            )

        }*/
        tvName.text = LBE.nickname
        tvScore.text = LBE.score
        tvTime.text = LBE.time
        tvDate.text = LBE.date
    }
}