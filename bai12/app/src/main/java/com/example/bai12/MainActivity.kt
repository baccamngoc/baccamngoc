package com.example.bai12

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.*
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var Listview: ListView
    private lateinit var edtTen: EditText
    private lateinit var edtDientich: EditText
    private lateinit var edtGdp: EditText
    private lateinit var radChaua: RadioButton
    private lateinit var radChauau: RadioButton
    private lateinit var radChauuc: RadioButton
    private lateinit var radChaumy: RadioButton
    private lateinit var radChauphi: RadioButton
    private lateinit var butSubmit: Button
    private lateinit var butOk: Button
    private lateinit var adapter: ArrayAdapter<String>
    private val quocgialist = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Ánh xạ từ layout
        Listview = findViewById(R.id.Listview)
        edtTen = findViewById(R.id.edtTen)
        edtDientich = findViewById(R.id.edtDientich)
        edtGdp = findViewById(R.id.edtGdp) // Thêm ánh xạ cho edtGdp
        radChaua = findViewById(R.id.radChaua)
        radChauau = findViewById(R.id.radChauau)
        radChauuc = findViewById(R.id.radChauuc)
        radChaumy = findViewById(R.id.radChaumy)
        radChauphi = findViewById(R.id.radChauphi)
        butSubmit = findViewById(R.id.butSubmit) // Ánh xạ butSubmit
        butOk = findViewById(R.id.butOk) // Ánh xạ butOk

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, quocgialist)
        Listview.adapter = adapter

        // xu ki nut subijt

        butSubmit.setOnClickListener {
            val quocgia = edtTen.text.toString().trim()
            val dientich = edtDientich.text.toString().trim()
            val gdp = edtGdp.text.toString().trim()

            if (quocgia.isNotEmpty() && dientich.isNotEmpty() && gdp.isNotEmpty()) {
                val entry = "$quocgia / $dientich / $gdp"
                quocgialist.add(entry)
                adapter.notifyDataSetChanged()

            }

            // Cập nhật adapter để hiển thị dữ liệu mới lên ListView
            adapter.notifyDataSetChanged()

            // Nút "OK"
            butOk.setOnClickListener { // Sửa lại để đúng là nút butOk
                val country = edtTen.text.toString()
                if (country.isNotEmpty()) {
                    Toast.makeText(this, country, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Vui lòng nhập tên quốc gia", Toast.LENGTH_SHORT).show()
                }
            }

            // Xóa item khi nhấn giữ lâu
            Listview.setOnItemLongClickListener { _, _, position, _ ->
                quocgialist.removeAt(position)
                adapter.notifyDataSetChanged()
                true
            }
        }
    }
}
