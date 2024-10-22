package com.example.hieu4

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var edtName: EditText
    private lateinit var edtPhone: EditText
    private lateinit var rbMale: RadioButton
    private lateinit var rbFemale: RadioButton
    private lateinit var btnAdd: Button
    private lateinit var listView: ListView
    private lateinit var userList: ArrayList<String>
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ các view
        edtName = findViewById(R.id.edtten)
        edtPhone = findViewById(R.id.edtsdt)
        rbMale = findViewById(R.id.radnam)
        rbFemale = findViewById(R.id.radnu)
        btnAdd = findViewById(R.id.btnAdd)
        listView = findViewById(R.id.listView)

        userList = ArrayList()
        adapter = A(this, userList)
        listView.adapter = adapter

        btnAdd.setOnClickListener {
            addUser()
        }

        listView.setOnItemLongClickListener { parent, view, position, id ->
            val user = userList[position]
            showDialog(user)
            true
        }
    }

    private fun addUser() {
        val name = edtName.text.toString()
        val phone = edtPhone.text.toString()
        val gender = if (rbMale.isChecked) "Nam" else if (rbFemale.isChecked) "Nữ" else ""

        if (name.isEmpty() || phone.isEmpty() || gender.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            return
        }

        val avatar = if (gender == "Nam") R.drawable.male_icon else R.drawable.female_icon
        val user = User(name, phone, gender, avatar)
        userList.add(user)
        adapter.notifyDataSetChanged()

        edtName.text.clear()
        edtPhone.text.clear()
        rbMale.isChecked = false
        rbFemale.isChecked = false
    }

    private fun showDialog(user: User) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Thông tin người dùng")
        builder.setMessage("Bạn có chắc chắn muốn xóa ${user.name}?")
        builder.setPositiveButton("Có") { dialog, _ ->
            userList.remove(user)
            adapter.notifyDataSetChanged()
            dialog.dismiss()
        }
        builder.setNegativeButton("Không") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }
}