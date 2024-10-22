package com.example.baikt

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "products.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "products"
        private const val COLUMN_ID = "id"
        private const val COLUMN_BRAND = "brand"
        private const val COLUMN_MODEL = "model"
        private const val COLUMN_ORIGIN = "origin"
        private const val COLUMN_WARRANTY = "warranty"
        private const val COLUMN_PAYMENT_METHOD = "payment_method"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_BRAND TEXT, " +
                "$COLUMN_MODEL TEXT, " +
                "$COLUMN_ORIGIN TEXT, " +
                "$COLUMN_WARRANTY TEXT, " +
                "$COLUMN_PAYMENT_METHOD TEXT)")
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addProduct(brand: String, model: String, origin: String, warranty: String, paymentMethod: String) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_BRAND, brand)
            put(COLUMN_MODEL, model)
            put(COLUMN_ORIGIN, origin)
            put(COLUMN_WARRANTY, warranty)
            put(COLUMN_PAYMENT_METHOD, paymentMethod)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllProducts(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }
}
