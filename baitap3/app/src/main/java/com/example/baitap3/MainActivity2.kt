package com.example.baitap3

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {

    private lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        textViewResult = findViewById(R.id.textViewResult)

        // Nhận dữ liệu từ MainActivity
        val contactInfo = intent.getStringExtra("contact_info")

        // Hiển thị kết quả nếu tìm thấy, nếu không thì báo không tìm thấy
        if (contactInfo != null) {
            textViewResult.text = contactInfo
        } else {
            textViewResult.text = "Không tìm thấy kết quả"
        }
    }
}
