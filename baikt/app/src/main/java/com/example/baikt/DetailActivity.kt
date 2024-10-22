package com.example.baikt

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity2)

        val txtBrand = findViewById<TextView>(R.id.txtBrand)
        val txtModel = findViewById<TextView>(R.id.txtModel)
        val txtOrigin = findViewById<TextView>(R.id.txtOrigin)
        val txtWarranty = findViewById<TextView>(R.id.txtWarranty)
        val txtPaymentMethod = findViewById<TextView>(R.id.txtPaymentMethod)
        val btnBack = findViewById<Button>(R.id.btnBack)

        // Nhận dữ liệu từ Activity 1
        val brand = intent.getStringExtra("BRAND")
        val model = intent.getStringExtra("MODEL")
        val origin = intent.getStringExtra("ORIGIN")
        val warranty = intent.getStringExtra("WARRANTY")
        val paymentMethod = intent.getStringExtra("PAYMENT_METHOD")

        // Hiển thị dữ liệu lên giao diện
        txtBrand.text = brand
        txtModel.text = model
        txtOrigin.text = "NSX: $origin"
        txtWarranty.text = "BH: $warranty"
        txtPaymentMethod.text = "Thanh toán: $paymentMethod"

        btnBack.setOnClickListener {
            // Quay lại Activity trước
            finish()
        }
    }
}
