package com.example.bai2
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.acivity_detail)

        // Nhận dữ liệu từ Intent
        val courseInfo = intent.getStringExtra("COURSE_INFO")

        // Hiển thị dữ liệu
        val textViewCourseDetail: TextView = findViewById(R.id.textViewCourseDetail)
        textViewCourseDetail.text = courseInfo
    }
}