package com.example.quanlymonhoc


import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var edtMaMon: EditText
    private lateinit var edtTenMon: EditText
    private lateinit var edtLyThuyet: EditText
    private lateinit var edtThucHanh: EditText
    private lateinit var btnNhapMonHoc: Button
    private lateinit var listViewMonHoc: ListView
    private lateinit var btnXoa: ImageButton
    private lateinit var adapter: ArrayAdapter<String>
    private val monHocList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ các view
        edtMaMon = findViewById(R.id.edtMaMon)
        edtTenMon = findViewById(R.id.edtTenMon)
        edtLyThuyet = findViewById(R.id.edtLyThuyet)
        edtThucHanh = findViewById(R.id.edtThucHanh)
        btnNhapMonHoc = findViewById(R.id.btnNhapMonHoc)
        listViewMonHoc = findViewById(R.id.listViewMonHoc)
        btnXoa = findViewById(R.id.btnXoa)

        // Thiết lập adapter cho ListView
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, monHocList)
        listViewMonHoc.adapter = adapter
        listViewMonHoc.choiceMode = ListView.CHOICE_MODE_MULTIPLE
        // Xử lý khi nhấn "NHẬP MÔN HỌC"
        btnNhapMonHoc.setOnClickListener {
            val maMon = edtMaMon.text.toString()
            val tenMon = edtTenMon.text.toString()
            val lyThuyet = edtLyThuyet.text.toString()
            val thucHanh = edtThucHanh.text.toString()

            // Kiểm tra dữ liệu đầu vào
            if (maMon.isEmpty() || tenMon.isEmpty() || lyThuyet.isEmpty() || thucHanh.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            } else if (!tenMon.all { it.isUpperCase() }) {
                Toast.makeText(this, "Tên môn phải viết hoa", Toast.LENGTH_SHORT).show()
            } else  {
                val monHoc = "$maMon-$tenMon-LT: $lyThuyet-TH: $thucHanh"
                monHocList.add(monHoc)
                adapter.notifyDataSetChanged()

                // Xóa nội dung sau khi nhập
                edtMaMon.text.clear()
                edtTenMon.text.clear()
                edtLyThuyet.text.clear()
                edtThucHanh.text.clear()
            }
        }

        // Hiển thị Toast khi chọn checkbox trong ListView
        listViewMonHoc.setOnItemClickListener { _, _, position, _ ->
            val selectedMonHoc = monHocList[position]
            Toast.makeText(this, "Đã chọn: $selectedMonHoc", Toast.LENGTH_SHORT).show()
        }

        // Xóa các mục đã chọn khi nhấn nút xóa
        btnXoa.setOnClickListener {
            val checkedItems = listViewMonHoc.checkedItemPositions
            val itemCount = listViewMonHoc.count

            for (i in itemCount - 1 downTo 0) {
                if (checkedItems[i]) {
                    monHocList.removeAt(i)
                }
            }

            adapter.notifyDataSetChanged()
            listViewMonHoc.clearChoices()
        }
    }
}
