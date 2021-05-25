package com.example.best_playz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.best_playz.Model.BE_LBEntry
import com.example.best_playz.Model.LBEntryDatabase
import com.example.best_playz.Model.LBEntryInDB
import org.json.JSONArray
import org.json.JSONObject

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

        mRep.insert(BE_LBEntry(0,"jimbo","250","29/04/2021","6.22"))
        mRep.insert(BE_LBEntry(0,"Timmy","150","28/04/2021","5.35"))
        mRep.insert(BE_LBEntry(0,"Kim","200","26/04/2021","3.30"))
        mRep.insert(BE_LBEntry(0,"Slayer2002","350","21/04/2021","4.20"))
    }

    private fun setupDataObserver() {
        // instans  repo
        val mRep = LBEntryInDB.get()
        //setting up observerble for BE_Friend and adding new adapter as an arrayadapter to fill the list view in the activety main
        val updateGUIObserver = Observer<List<BE_LBEntry>>{ LBE ->
            val asStrings = LBE.map { f -> "${f.id}, ${f.nickname}, ${f.score}, ${f.date} "}
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
     /*   val queue = Volley.newRequestQueue(this)
        val url = "https://best-playz-heroku-backend.herokuapp.com/"
        val mRep = LBEntryInDB.get()

        val jarray = JsonArrayRequest(Request.Method.GET,url,
                Response.Listener<JSONArray> { response: JSONArray? -> for () }
        )
        val jsonRequest =  JsonObjectRequest(Request.Method.GET,url,
        Response.Listener<JSONObject> {
            response ->
            mRep.insert(BE_LBEntry(response))

        }, Response.ErrorListener { "That didn't work!" })
                )

        val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->
                    // Display the first 500 characters of the response string.
                    textView.text = "Response is: ${response.substring(0, 500)}"
                },
                Response.ErrorListener { textView.text = "That didn't work!" })

// Add the request to the RequestQueue.
        queue.add(stringRequest)
*/
    }
}