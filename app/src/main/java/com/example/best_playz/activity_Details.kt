package com.example.best_playz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.best_playz.Model.BE_Comment
import com.example.best_playz.Model.BE_HigeScore
import com.example.best_playz.Model.BE_LBEntry
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class activity_Details : AppCompatActivity() {
    val CBE: ArrayList<BE_Comment> = ArrayList<BE_Comment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity__details)


        val lV = findViewById<ListView>(R.id.lv_comments)
        val extras: Bundle = intent.extras!!
        val LBE = extras.getSerializable("LBE") as BE_LBEntry
        val tvName = findViewById<TextView>(R.id.Tv_nickname)
        val tvScore = findViewById<TextView>(R.id.Tv_score)
        val tvTime = findViewById<TextView>(R.id.Tv_time)
        val tvDate = findViewById<TextView>(R.id.Tv_date)

        tvName.text = LBE.nickname
        tvScore.text = LBE.score
        tvTime.text = LBE.time
        tvDate.text = LBE.date
        getCommons()

        val asStrings = CBE.map { f -> " ${f.nickname}, ${f.text}, ${f.date}, " }
        val adapter: ListAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            asStrings.toTypedArray())

        lV.adapter = adapter

      /*val updateGUIObserver = Observer<List<BE_Comment>> { LBE ->
            val asStrings = LBE.map { f -> " ${f.nickname}, ${f.Text}, ${f.date}, " }
            val adapter: ListAdapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                asStrings.toTypedArray()
            )

        }*/

    }
    fun getCommons(){
        CBE.add(BE_Comment("id","bob","hsid","thisdate","youRock and bawls"))
        CBE.add(BE_Comment("id","lollb","hsid","thisdate","youRock "))
        CBE.add(BE_Comment("id","Wassa","hsid","thisdate"," bawls"))
      //  CBE.add(BE_Comment("0", Cs.nickname, Cs.HigeScoreId, Cs.date, Cs.Text),
       // CBE.add(BE_Comment("0", Cs.nickname, Cs.HigeScoreId, Cs.date, Cs.Text)
    }
/*
     fun getCommons(){
        val url = "https://best-playz-heroku-backend.herokuapp.com/Commots"+ LBE.comRef
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                println(body)
                val gbuld = GsonBuilder()
                val gson = gbuld.create()
                val CommonArray: Array<BE_Comment> =
                    gson.fromJson(body, Array<BE_Comment>::class.java)


                for (Cs in CommonArray) {
                   CBE.add(BE_Comment("0", Cs.nickname, Cs.HigeScoreId, Cs.date, Cs.Text))

                }
            }
        })
    }*/

}