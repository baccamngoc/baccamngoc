package com.example.bai11

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    public lateinit var listView: ListView
    public lateinit var txenlish:TextView
    public lateinit var txvietnamese:TextView
    public lateinit var btnadd:Button
    public lateinit var btnseach:Button
    public lateinit var adapter: ArrayAdapter<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
 //anh xa tu cac layout
        listView =findViewById(R.id.txenglish)
        listView= findViewById(R.id.txvietnamese)
        listView= findViewById(R.id.btnadd)
        listView=findViewById(R.id.btnseach)
    }
}