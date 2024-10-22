package com.example.hieubai2

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var edtTenkahchhang: EditText
    private lateinit var edtSoluong: EditText
    private lateinit var radVND: RadioButton
    private lateinit var radUSD: RadioButton
    private lateinit var radEUR: RadioButton
    private lateinit var radvnd: RadioButton
    private lateinit var radusd: RadioButton
    private lateinit var radeur: RadioButton
    private lateinit var btnNhap: Button
    private lateinit var btnNhaplai: Button
    private lateinit var ListviewKhachhang: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private var KhachHanglist = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        edtTenkahchhang = findViewById(R.id.edtTenkhachhang)
        edtSoluong = findViewById(R.id.edtSoluong)
        radVND = findViewById(R.id.radVND)
        radUSD = findViewById(R.id.radUSD)
        radEUR = findViewById(R.id.radEUR)
        radvnd = findViewById(R.id.radvnd)
        radusd = findViewById(R.id.radusd)
        radeur = findViewById(R.id.radeur)
        btnNhap = findViewById(R.id.btnNhap)
        btnNhaplai = findViewById(R.id.btnNhaplai)
        ListviewKhachhang = findViewById(R.id.ListviewKhachhang)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, KhachHanglist)
        ListviewKhachhang.adapter = adapter
        ListviewKhachhang.choiceMode = ListView.CHOICE_MODE_MULTIPLE
        btnNhap.setOnClickListener {
            val tenKhachHang = edtTenkahchhang
            val SoLuong = edtSoluong
            var selectedText = ""

            if (radVND.isChecked) {
                selectedText = radVND.text.toString()
            } else if (radUSD.isChecked) {
                selectedText = radUSD.text.toString()
            } else if (radEUR.isChecked) {
                selectedText = radEUR.text.toString()
            }
            val monHoc = "$tenKhachHang-$SoLuong"

            KhachHanglist.add(monHoc)
            adapter.notifyDataSetChanged()

        }
    }
}