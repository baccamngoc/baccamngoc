package com.example.bai2

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var edtMaMon: EditText
    private lateinit var edtTenMon: EditText
    private lateinit var edtLyThuyet: EditText
    private lateinit var edtThucHanh: EditText
    private lateinit var btnNhapMonHoc: Button
    private lateinit var btnDelete: ImageButton
    private lateinit var listView: ListView
    private lateinit var adapter: ArrayAdapter<String>
    private val monHocList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ các View
        edtMaMon = findViewById(R.id.edtMaMon)
        edtTenMon = findViewById(R.id.edtTenMon)
        edtLyThuyet = findViewById(R.id.edtLyThuyet)
        edtThucHanh = findViewById(R.id.edtThucHanh)
        btnNhapMonHoc = findViewById(R.id.btnNhapMonHoc)
        btnDelete = findViewById(R.id.btnDelete)
        listView = findViewById(R.id.listView)

        // Thiết lập adapter cho ListView
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, monHocList)
        listView.adapter = adapter
        listView.choiceMode = ListView.CHOICE_MODE_MULTIPLE

        // Xử lý nút "NHẬP MÔN HỌC"
        btnNhapMonHoc.setOnClickListener {
            val maMon = edtMaMon.text.toString()
            val tenMon = edtTenMon.text.toString()
            val lyThuyet = edtLyThuyet.text.toString()
            val thucHanh = edtThucHanh.text.toString()

            if (validateInput(maMon, tenMon, lyThuyet, thucHanh)) {
                val newMonHoc = "$maMon-$tenMon-LT: $lyThuyet-TH: $thucHanh"
                monHocList.add(newMonHoc)
                adapter.notifyDataSetChanged()
            }
        }

        // Xử lý chọn CheckBox và hiển thị Toast
        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedMonHoc = monHocList[position]
            Toast.makeText(this, "Môn học: $selectedMonHoc", Toast.LENGTH_SHORT).show()
        }

        // Xử lý nút xóa (ImageButton)
        btnDelete.setOnClickListener {
            val positionsToRemove = ArrayList<Int>()
            for (i in 0 until listView.count) {
                if (listView.isItemChecked(i)) {
                    positionsToRemove.add(i)
                }
            }

            // Xóa các item đã chọn từ cuối về đầu
            for (i in positionsToRemove.size - 1 downTo 0) {
                monHocList.removeAt(positionsToRemove[i])
            }

            listView.clearChoices()  // Bỏ chọn các item
            adapter.notifyDataSetChanged()  // Cập nhật ListView
        }
    }

    // Hàm kiểm tra dữ liệu nhập
    private fun validateInput(maMon: String, tenMon: String, lyThuyet: String, thucHanh: String): Boolean {
        if (maMon.isEmpty() || tenMon.isEmpty() || lyThuyet.isEmpty() || thucHanh.isEmpty()) {
            Toast.makeText(this, "Không được để trống!", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!tenMon.all { it.isUpperCase() }) {
            Toast.makeText(this, "Tên môn phải viết hoa!", Toast.LENGTH_SHORT).show()
            return false
        }

        return true

    }
    
}

