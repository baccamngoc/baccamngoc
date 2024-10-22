package com.example.hieu09

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

    class ThoitietDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

        companion object {
            private const val DATABASE_NAME = "thoitiet.db"
            private const val DATABASE_VERSION = 1
            private const val TABLE_NAME = "ThoiTiet"
            private const val COLUMN_ID = "id"
            private const val COLUMN_DATE = "ngayThang"
            private const val COLUMN_TEMPERATURE = "nhietDo"
            private const val COLUMN_HUMIDITY = "doAm"
        }

        override fun onCreate(db: SQLiteDatabase?) {
            // Tạo bảng cơ sở dữ liệu
            val createTable = ("CREATE TABLE $TABLE_NAME (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_DATE TEXT, " +
                    "$COLUMN_TEMPERATURE TEXT, " +
                    "$COLUMN_HUMIDITY TEXT)")
            db?.execSQL(createTable)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
            onCreate(db)
        }

        // Hàm thêm thông tin thời tiết vào cơ sở dữ liệu
        fun addWeather(ngayThang: String, nhietDo: String, doAm: String): Boolean {
            val db = this.writableDatabase
            val Values = ContentValues()
               Values.put(COLUMN_DATE, ngayThang)
               Values.put(COLUMN_TEMPERATURE, nhietDo)
               Values.put(COLUMN_HUMIDITY, doAm)

            val result = db.insert(TABLE_NAME, null, Values)
            db.close()
            return result != -1L
        }

        // Hàm lấy tất cả thông tin thời tiết từ cơ sở dữ liệu
        fun getAllThoitiet(): Cursor {
            val db = this.readableDatabase
            return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        }

        // Hàm xóa mục theo id
        fun deleteThoi(id: Int): Boolean {
            val db = this.writableDatabase
            val result = db.delete(TABLE_NAME, "$COLUMN_ID=?", arrayOf(id.toString()))
            db.close()
            return result > 0
        }
    }