package com.example.hieu

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var edtMamon:EditText
    private lateinit var edtTenmon:EditText
    private lateinit var edtLythuyet:EditText
    private lateinit var edtThuchanh:EditText
    private lateinit var btnNhapmonhoc:Button
    private lateinit var ListviewMonhoc:ListView
    private lateinit var btnXoa:ImageButton
    private lateinit var adapter: ArrayAdapter<String>
    private val monHoclist =ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //anh xa tu cac view

        edtMamon = findViewById(R.id.edtMamon)
        edtTenmon = findViewById(R.id.edtTenMon)
        edtLythuyet = findViewById(R.id.edtLythuyet)
        edtThuchanh = findViewById(R.id.edtThuchanh)
        btnNhapmonhoc = findViewById(R.id.btnNhapmonhoc)
        btnXoa = findViewById(R.id.btnXoa)
        ListviewMonhoc = findViewById(R.id.listviewMonhoc)
        // thiet lap adapter
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, monHoclist)
        ListviewMonhoc.adapter = adapter
        ListviewMonhoc.choiceMode = ListView.CHOICE_MODE_MULTIPLE

        //xu nut nahp mpn hoc
        btnNhapmonhoc.setOnClickListener {
            val maMon = edtMamon.text.toString()
            val tenMon = edtTenmon.text.toString()
            val Lythuyet = edtLythuyet.text.toString()
            val ThucHanh = edtThuchanh.text.toString()
            // kieem tra du lieu nahp va
            if (maMon.isEmpty() || tenMon.isEmpty() || Lythuyet.isEmpty() || ThucHanh.isEmpty()) {
                Toast.makeText(this, "vui long nhap day du thong tin ", Toast.LENGTH_LONG).show()
            } else if (!tenMon.all { it.isUpperCase() }) {
                Toast.makeText(this, "Ten Mon phai viet hoa ", Toast.LENGTH_LONG).show()
            } else {
                val monHoc = "$maMon-$tenMon-LT: $Lythuyet-TH: $ThucHanh"
                monHoclist.add(monHoc)
                adapter.notifyDataSetChanged()
                //xoa noij dung sau khi nhap
                edtMamon.text.clear()
                edtTenmon.text.clear()
                edtLythuyet.text.clear()
                edtThuchanh.text.clear()
            }
        }
        // Hiển thị Toast khi chọn checkbox trong ListView
        ListviewMonhoc.setOnItemClickListener { _, _, position, _ ->
            val selectedMonHoc = monHoclist[position]
            Toast.makeText(this, "Đã chọn: $selectedMonHoc", Toast.LENGTH_SHORT).show()

        }
// Xóa các mục đã chọn khi nhấn nút xóa
            btnXoa.setOnClickListener {
                val checkedItems = ListviewMonhoc.checkedItemPositions
                val itemCount = ListviewMonhoc.count

                for (i in itemCount - 1 downTo 0) {
                    if (checkedItems[i]) {
                        monHoclist.removeAt(i)
                    }
                }

                adapter.notifyDataSetChanged()
                ListviewMonhoc.clearChoices()
            }

        }
    }