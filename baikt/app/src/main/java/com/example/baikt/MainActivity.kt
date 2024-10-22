package com.example.baikt

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DatabaseHelper(this)

        val edtBrand = findViewById<EditText>(R.id.edtBrand)
        val edtModel = findViewById<EditText>(R.id.edtModel)
        val edtOrigin = findViewById<EditText>(R.id.edtOrigin)
        val chk1y = findViewById<CheckBox>(R.id.chk1y)
        val chk2y = findViewById<CheckBox>(R.id.chk2y)
        val chk3y = findViewById<CheckBox>(R.id.chk3y)
        val rbtnCash = findViewById<RadioButton>(R.id.rbtnCash)
        val rbtnTransfer = findViewById<RadioButton>(R.id.rbtnTransfer)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val btnReset = findViewById<Button>(R.id.btnReset)

        btnSubmit.setOnClickListener {
            val brand = edtBrand.text.toString()
            val model = edtModel.text.toString()
            val origin = edtOrigin.text.toString()
            val warranty = when {
                chk3y.isChecked -> "3 năm"
                chk2y.isChecked -> "2 năm"
                else -> "1 năm"
            }
            val paymentMethod = if (rbtnCash.isChecked) "Tiền mặt" else "Chuyển khoản"

            // Thêm dữ liệu vào cơ sở dữ liệu
            dbHelper.addProduct(brand, model, origin, warranty, paymentMethod)

            // Chuyển đến Activity 2 để hiển thị danh sách
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

        btnReset.setOnClickListener {
            // Reset tất cả các trường dữ liệu
            edtBrand.text.clear()
            edtModel.text.clear()
            edtOrigin.text.clear()
            chk1y.isChecked = false
            chk2y.isChecked = false
            chk3y.isChecked = false
            rbtnCash.isChecked = true
        }
    }
}
