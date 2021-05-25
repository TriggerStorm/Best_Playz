package com.example.best_playz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import com.example.best_playz.Model.BE_LBEntry
import com.example.best_playz.Model.LBEntryInDB

val TAG = "xyz"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;

    }



    fun Leaderbord(view: View) {
        println("main bn clicked")
        val intent = Intent(this, activity_leaderbord::class.java)
        startActivity(intent)
    }
}