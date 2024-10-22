package com.example.hieu09

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.hieu09.ThoitietDatabaseHelper

class MainActivity : AppCompatActivity() {

    private lateinit var edtNgaythang: EditText
    private lateinit var edtNhietdo: EditText
    private lateinit var edtDoam: EditText
    private lateinit var radCo: RadioButton
    private lateinit var radKhong: RadioButton
    private lateinit var btnSubmit: Button
    private lateinit var btnOk: Button
    private lateinit var listViewThoitiet: ListView
    private lateinit var arrayAdapter: ArrayAdapter<String>
    private val weatherList = mutableListOf<String>()
    private lateinit var dbHelper: ThoitietDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ các view từ XML
        edtNgaythang = findViewById(R.id.edtNgaythang)
        edtNhietdo = findViewById(R.id.edtNhietdo)
        edtDoam = findViewById(R.id.edtDoam)
        radCo = findViewById(R.id.radCo)
        radKhong = findViewById(R.id.radKhong)
        btnSubmit = findViewById(R.id.btnSubmit)
        btnOk = findViewById(R.id.btnOk)
        listViewThoitiet = findViewById(R.id.ListviewThoitiet)

        // Thiết lập ArrayAdapter cho ListView
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, weatherList)
        listViewThoitiet.adapter = arrayAdapter

        // Khởi tạo cơ sở dữ liệu
        dbHelper = ThoitietDatabaseHelper(this)

        // Hiển thị dữ liệu đã lưu vào ListView
        loadThoiTietFromDatabase()

        // Xử lý sự kiện khi nhấn nút SUBMIT
        btnSubmit.setOnClickListener {
            val ngayThang = edtNgaythang.text.toString().trim()
            val nhietDo = edtNhietdo.text.toString().trim()
            val doAm = edtDoam.text.toString().trim()

            // Kiểm tra nếu người dùng chọn "Lưu thông tin"
            if (radCo.isChecked) {
                if (ngayThang.isNotEmpty() && nhietDo.isNotEmpty() && doAm.isNotEmpty()) {
                    // Thêm thông tin vào cơ sở dữ liệu
                    val isInserted = dbHelper.addWeather(ngayThang, nhietDo, doAm)
                    if (isInserted) {
                        Toast.makeText(this, "Đã lưu thông tin thời tiết!", Toast.LENGTH_SHORT).show()
                        loadThoiTietFromDatabase()
                    } else {
                        Toast.makeText(this, "Lỗi khi lưu dữ liệu!", Toast.LENGTH_SHORT).show()
                    }

                    // Xóa dữ liệu nhập sau khi thêm
                    edtNgaythang.text.clear()
                    edtNhietdo.text.clear()
                    edtDoam.text.clear()

                } else {
                    Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Xử lý sự kiện khi nhấn nút OK
        btnOk.setOnClickListener {
            if (weatherList.isNotEmpty()) {
                val latestWeatherInfo = weatherList.last()
                Toast.makeText(this, latestWeatherInfo, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Danh sách rỗng!", Toast.LENGTH_SHORT).show()
            }
        }

        // Xử lý sự kiện xóa mục khi nhấn giữ lâu trên ListView
        listViewThoitiet.setOnItemLongClickListener { parent, view, position, id ->
            val cursor = dbHelper.getAllThoitiet()
            cursor.moveToPosition(position)
            val weatherId = cursor.getInt(0) // Lấy ID của mục thời tiết

            val isDeleted = dbHelper.deleteThoi(weatherId)
            if (isDeleted) {
                Toast.makeText(this, "Đã xóa thông tin thời tiết!", Toast.LENGTH_SHORT).show()
                loadThoiTietFromDatabase()
            } else {
                Toast.makeText(this, "Lỗi khi xóa dữ liệu!", Toast.LENGTH_SHORT).show()
            }

            true
        }
    }

    // Hàm để tải dữ liệu từ cơ sở dữ liệu và hiển thị lên ListView
    private fun loadThoiTietFromDatabase() {
        weatherList.clear()
        val cursor = dbHelper.getAllThoitiet()

        if (cursor.count == 0) {
            Toast.makeText(this, "Không có dữ liệu!", Toast.LENGTH_SHORT).show()
        } else {
            while (cursor.moveToNext()) {
                val ngayThang = cursor.getString(1)
                val nhietDo = cursor.getString(2)
                val doAm = cursor.getString(3)
                val weatherInfo = "$ngayThang – $nhietDo°C – $doAm%"
                weatherList.add(weatherInfo)
            }
        }
        arrayAdapter.notifyDataSetChanged()
    }
}
