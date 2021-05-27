package com.example.best_playz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.best_playz.Model.BE_HSList
import com.example.best_playz.Model.BE_HigeScore
import com.example.best_playz.Model.BE_LBEntry
import com.example.best_playz.Model.LBEntryInDB
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException

class activity_leaderbord : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leaderbord)
        LBEntryInDB.initialize(this)
       // insertTestData()
        setupDataObserver()

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;

    }

    private fun insertTestData() {
       val mRep = LBEntryInDB.get()

       /* mRep.insert(BE_LBEntry(0,"jimbo","250","29/04/2021","6.22"))
        mRep.insert(BE_LBEntry(0,"Timmy","150","28/04/2021","5.35"))
        mRep.insert(BE_LBEntry(0,"Kim","200","26/04/2021","3.30"))
        mRep.insert(BE_LBEntry(0,"Slayer2002","350","21/04/2021","4.20"))*/
    }

    private fun setupDataObserver() {
        // instans  repo
        val mRep = LBEntryInDB.get()
        //setting up observerble for BE_Friend and adding new adapter as an arrayadapter to fill the list view in the activety
        val updateGUIObserver = Observer<List<BE_LBEntry>>{ LBE ->
            val asStrings = LBE.map { f -> " ${f.nickname}, ${f.score}, ${f.date} "}
            val adapter: ListAdapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    asStrings.toTypedArray()
            )
            val lV = findViewById<ListView>(R.id.lv_Leaderbord)
            lV.adapter = adapter
            Log.d(TAG, "UpdateGUI Observer notified")
        }
        // setting up and observeble with the live data and make on click listner for friends on the Listview to hand be_friend to details.
        mRep.getAllLiveData().observe(this, updateGUIObserver)
        val lV = findViewById<ListView>(R.id.lv_Leaderbord)
        lV.onItemClickListener = AdapterView.OnItemClickListener { _, _, pos, _ -> onListItemClick(pos)}
    }

    private fun onListItemClick(pos: Int) {
        val mRep = LBEntryInDB.get()
        val intent = Intent(this, activity_Details::class.java)
        val LbEntry = mRep.getByPos(pos)
        intent.putExtra("LBE", LbEntry)
        startActivity(intent)
    }

    fun details(view: View) {}
    fun GetOnlineDB(view: View) {
        val mRep = LBEntryInDB.get()
        println("bn clicked")
        val url = "https://best-playz-heroku-backend.herokuapp.com/highscore"
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()
                println(body)
              val gbuld = GsonBuilder()
                val gson = gbuld.create()
                val leedArray: Array<BE_HigeScore> = gson.fromJson( body, Array<BE_HigeScore>::class.java )
               // val LBE: ArrayList<BE_LBEntry> = ArrayList<BE_LBEntry>()
                mRep.clear()
                for (Hs in leedArray)
                {
                    //LBE.add(BE_LBEntry(0,Hs.nickname,Hs.score.toString(),Hs.date,Hs.time.toString()))
                    mRep.insert(BE_LBEntry(0,Hs.nickname,Hs.score.toString(),Hs.date,Hs.time.toString(),Hs.id))
                }


            }

            override fun onFailure(call: Call, e: IOException) {
                println("Fail to execute request to db")
            }
        })


    }
}