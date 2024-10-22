package com.example.baitap
import android.os.Bundle
import android.widget.*
import android.widget.ArrayAdapter
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Khai báo lớp sinh viên
    data class Student(val name: String, val studentId: String, val hometown: String, val service: String)

    private lateinit var listView: ListView
    private lateinit var editName: EditText
    private lateinit var editStudentId: EditText
    private lateinit var editHometown: EditText
    private lateinit var editService: EditText
    private lateinit var btnAddOrEdit: Button
    private lateinit var btnDelete: Button
    private lateinit var checkBoxSMS: CheckBox
    private lateinit var checkBoxInsurance1: CheckBox
    private lateinit var checkBoxInsurance2: CheckBox
    private lateinit var checkBoxTuition: CheckBox


    private lateinit var adapter: ArrayAdapter<String>
    private val students = mutableListOf<Student>()
    private var selectedPosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Liên kết với các thành phần giao diện
        listView = findViewById(R.id.listView)
        editName = findViewById(R.id.editName)
        editStudentId = findViewById(R.id.editStudentId)
        editHometown = findViewById(R.id.editHometown)
        editService = findViewById(R.id.btnEdit)
        checkBoxSMS = findViewById(R.id.checkBoxSMS)
        checkBoxInsurance1 = findViewById(R.id.checkBoxInsurance1)
        checkBoxInsurance2 = findViewById(R.id.checkBoxInsurance2)
        checkBoxTuition = findViewById(R.id.checkBoxTuition)

        btnAddOrEdit = findViewById(R.id.btnAdd)
        btnDelete = findViewById(R.id.btnDelete)

        // Thiết lập adapter cho ListView
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, students.map { it.name })
        listView.adapter = adapter

        // Sự kiện thêm/sửa sinh viên
        btnAddOrEdit.setOnClickListener {
            val name = editName.text.toString()
            val studentId = editStudentId.text.toString()
            val hometown = editHometown.text.toString()
            val service = editService.text.toString()

            if (name.isNotEmpty() && studentId.isNotEmpty() && hometown.isNotEmpty() && service.isNotEmpty()) {
                if (selectedPosition == -1) {
                    // Thêm mới sinh viên
                    val student = Student(name, studentId, hometown, service)
                    students.add(student)
                    Toast.makeText(this, "Đã thêm sinh viên", Toast.LENGTH_SHORT).show()
                } else {
                    // Sửa thông tin sinh viên
                    val updatedStudent = Student(name, studentId, hometown, service)
                    students[selectedPosition] = updatedStudent
                    Toast.makeText(this, "Đã cập nhật thông tin sinh viên", Toast.LENGTH_SHORT).show()
                    selectedPosition = -1 // Reset vị trí sau khi sửa xong
                }
                updateListView()
                clearFields()
            } else {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            }
        }

        // Khi chọn một mục trong ListView để sửa
        listView.setOnItemClickListener { _, _, position, _ ->
            val student = students[position]
            editName.setText(student.name)
            editStudentId.setText(student.studentId)
            editHometown.setText(student.hometown)
            editService.setText(student.service)
            selectedPosition = position
            btnAddOrEdit.text = "SỬA" // Thay đổi văn bản nút thành "SỬA"
        }

        // Sự kiện xóa sinh viên
        btnDelete.setOnClickListener {
            if (selectedPosition >= 0) {
                students.removeAt(selectedPosition)
                updateListView()
                clearFields()
                Toast.makeText(this, "Đã xóa sinh viên", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Vui lòng chọn sinh viên để xóa", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Cập nhật lại ListView
    private fun updateListView() {
        adapter.clear()
        adapter.addAll(students.map { it.name })
        adapter.notifyDataSetChanged()
    }

    // Xóa dữ liệu trong các trường nhập liệu và reset trạng thái
    private fun clearFields() {
        editName.text.clear()
        editStudentId.text.clear()
        editHometown.text.clear()
        editService.text.clear()
        selectedPosition = -1
        btnAddOrEdit.text = "THÊM" // Đặt lại văn bản nút thành "THÊM"
    }
}
