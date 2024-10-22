package com.example.bai22

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etMaMon: EditText
    private lateinit var etTenMon: EditText
    private lateinit var etLyThuyet: EditText
    private lateinit var etThucHanh: EditText
    private lateinit var btnNhapMonHoc: Button
    private lateinit var lvMonHoc: ListView
    private lateinit var btnXoaMonHoc: ImageButton
    private val listMonHoc = arrayListOf<MonHoc>()
    private lateinit var adapter: MonHocAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ các view
        etMaMon = findViewById(R.id.etMaMon)
        etTenMon = findViewById(R.id.etTenMon)
        etLyThuyet = findViewById(R.id.etLyThuyet)
        etThucHanh = findViewById(R.id.etThucHanh)
        btnNhapMonHoc = findViewById(R.id.btnNhapMonHoc)
        lvMonHoc = findViewById(R.id.lvMonHoc)
        btnXoaMonHoc = findViewById(R.id.btnXoaMonHoc)

        // Thiết lập adapter tùy chỉnh cho ListView
        adapter = MonHocAdapter(this, listMonHoc)
        lvMonHoc.adapter = adapter

        // Xử lý sự kiện nhấn nút "NHẬP MÔN HỌC"
        btnNhapMonHoc.setOnClickListener {
            val maMon = etMaMon.text.toString().trim()
            val tenMon = etTenMon.text.toString().trim()
            val lyThuyet = etLyThuyet.text.toString().trim()
            val thucHanh = etThucHanh.text.toString().trim()

            // Kiểm tra dữ liệu đầu vào
            if (maMon.isEmpty() || tenMon.isEmpty() || lyThuyet.isEmpty() || thucHanh.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Nếu dữ liệu hợp lệ, thêm môn học vào danh sách
            val monHoc = MonHoc("$maMon - $tenMon - LT: $lyThuyet - TH: $thucHanh", false)
            listMonHoc.add(monHoc)
            adapter.notifyDataSetChanged()

            // Xóa nội dung sau khi thêm
            etMaMon.text.clear()
            etTenMon.text.clear()
            etLyThuyet.text.clear()
            etThucHanh.text.clear()
        }

        // Xử lý sự kiện nhấn giữ (long press) trên một item trong ListView
        lvMonHoc.setOnItemLongClickListener { _, _, position, _ ->
            val monHoc = listMonHoc[position]

            // Tạo intent để chuyển sang Activity2
            val intent = Intent(this, Activity2::class.java)
            intent.putExtra("TEN_MON_HOC", monHoc.tenMonHoc)

            // Chuyển sang Activity2
            startActivity(intent)

            true
        }

        // Xử lý sự kiện xóa môn học khi nhấn ImageButton "XÓA"
        btnXoaMonHoc.setOnClickListener {
            xoaMonHocDuocChon()
        }
    }

    // Hàm xóa các môn học được chọn
    private fun xoaMonHocDuocChon() {
        val iterator = listMonHoc.iterator()
        while (iterator.hasNext()) {
            val monHoc = iterator.next()
            if (monHoc.isChecked) {
                iterator.remove()  // Xóa môn học nếu được chọn
            }
        }
        adapter.notifyDataSetChanged()
    }

    // Data class để lưu thông tin môn học
    data class MonHoc(val tenMonHoc: String, var isChecked: Boolean)

    // Adapter tùy chỉnh cho ListView
    class MonHocAdapter(context: MainActivity, private val monHocList: ArrayList<MonHoc>) :
        ArrayAdapter<MonHoc>(context, 0, monHocList) {

        override fun getView(position: Int, convertView: android.view.View?, parent: android.view.ViewGroup): android.view.View {
            val view = convertView ?: android.view.LayoutInflater.from(context).inflate(R.layout.item_monhoc, parent, false)

            // Lấy dữ liệu cho từng item
            val monHoc = monHocList[position]

            // Ánh xạ các view
            val tvMonHoc = view.findViewById<TextView>(R.id.tvMonHoc)
            val cbMonHoc = view.findViewById<CheckBox>(R.id.cbMonHoc)

            // Thiết lập dữ liệu cho TextView và CheckBox
            tvMonHoc.text = monHoc.tenMonHoc
            cbMonHoc.isChecked = monHoc.isChecked

            // Xử lý sự kiện khi CheckBox được thay đổi
            cbMonHoc.setOnCheckedChangeListener { _, isChecked ->
                monHoc.isChecked = isChecked
            }

            return view
        }
    }
}