package com.example.bai22

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Activity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        // Nhận dữ liệu từ intent
        val tenMonHoc = intent.getStringExtra("TEN_MON_HOC")

        // Ánh xạ TextView và hiển thị tên môn học
        val tvMonHoc = findViewById<TextView>(R.id.tvMonHoc)
        tvMonHoc.text = tenMonHoc
    }
}
