package com.example.baitap3

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var radioMale: RadioButton
    private lateinit var radioFemale: RadioButton
    private lateinit var buttonAdd: Button
    private lateinit var buttonEdit: Button
    private lateinit var buttonSearch: Button
    private lateinit var listView: ListView

    private val contactList = arrayListOf<String>()
    private lateinit var arrayAdapter: ArrayAdapter<String>
    private var selectedItemIndex: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextName = findViewById(R.id.editTextName)
        editTextPhone = findViewById(R.id.editTextPhone)
        radioMale = findViewById(R.id.radioMale)
        radioFemale = findViewById(R.id.radioFemale)
        buttonAdd = findViewById(R.id.buttonAdd)
        buttonEdit = findViewById(R.id.buttonEdit)
        buttonSearch = findViewById(R.id.buttonSearch)
        listView = findViewById(R.id.listView)

        // Thêm một vài mục mặc định vào contactList
        contactList.add("Trần Xuân Hiếu  - 123 - Nam")
        contactList.add("Tran Thi Hiền - 1212 - Nữ")
        contactList.add("Nguyễn Duy Hưng -1211 - Nam")

        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, contactList)
        listView.adapter = arrayAdapter

        // Cập nhật giao diện ListView ngay sau khi thêm các mục vào danh sách
        arrayAdapter.notifyDataSetChanged()

        buttonAdd.setOnClickListener {
            val name = editTextName.text.toString()
            val phone = editTextPhone.text.toString()
            val gender = if (radioMale.isChecked) "Nam" else "Nữ"

            if (name.isNotEmpty() && phone.isNotEmpty()) {
                val contact = "$name - $phone - $gender"
                contactList.add(contact)
                arrayAdapter.notifyDataSetChanged()
                editTextName.text.clear()
                editTextPhone.text.clear()
                radioMale.isChecked = true
            } else {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show()
            }
        }
        listView.setOnItemClickListener { _, _, position, _ ->
            selectedItemIndex = position
            val contact = contactList[position]
            val parts = contact.split(" - ")

            editTextName.setText(parts[0])
            editTextPhone.setText(parts[1])
            if (parts[2] == "Nam") {
                radioMale.isChecked = true
            } else {
                radioFemale.isChecked = true
            }
            buttonEdit.isEnabled = true
        }

        buttonEdit.setOnClickListener {
            if (selectedItemIndex >= 0) {
                val name = editTextName.text.toString()
                val phone = editTextPhone.text.toString()
                val gender = if (radioMale.isChecked) "Nam" else "Nữ"

                if (name.isNotEmpty() && phone.isNotEmpty()) {
                    val contact = "$name - $phone - $gender"
                    contactList[selectedItemIndex] = contact
                    arrayAdapter.notifyDataSetChanged()


                    editTextName.text.clear()
                    editTextPhone.text.clear()
                    radioMale.isChecked = true

                    buttonEdit.isEnabled = false
                } else {
                    Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show()
                }
            }
        }
        buttonSearch.setOnClickListener {
            val phoneToSearch = editTextPhone.text.toString()
            if (phoneToSearch.isNotEmpty()) {
                val intent = Intent(this, MainActivity2::class.java)
                intent.putExtra("phone", phoneToSearch)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Nhập số điện thoại để tìm kiếm", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


